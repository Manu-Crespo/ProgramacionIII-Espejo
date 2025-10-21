package com.example.Parcial2.Services;

import com.example.Parcial2.Entities.DTOs.AsignacionEstudiantes;
import com.example.Parcial2.Entities.Curso;
import com.example.Parcial2.Entities.DTOs.CursoCreate;
import com.example.Parcial2.Entities.DTOs.CursoEdit;
import com.example.Parcial2.Entities.DTOs.CursoDto;
import com.example.Parcial2.Entities.Estudiante;
import com.example.Parcial2.Entities.Mapper.CursoMapper;
import com.example.Parcial2.Entities.Profesor;
import com.example.Parcial2.Repositories.CursoRepository;
import com.example.Parcial2.Repositories.EstudianteRepository;
import com.example.Parcial2.Repositories.ProfesorRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CursoServiceImp implements CursoService{
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public CursoDto crear(CursoCreate c) {
        // 1. Buscar el profesor y validar que exista y no esté eliminado
        Profesor profesor = profesorRepository.findById(c.getProfesorId())
                .filter(p -> !p.isEliminado())
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado con ID: " + c.getProfesorId()));

        // 2. Convertir el DTO a entidad
        Curso curso = CursoMapper.toEntity(c);
        curso.setProfesor(profesor);

        // 3. Guardar la entidad en la base de datos
        Curso cursoGuardado = cursoRepository.save(curso);
        return CursoMapper.toDTO(cursoGuardado);
    }


    @Override
    public CursoDto actualizar(Long id, CursoEdit ce) {
        // 1. Buscar el curso existente
        Curso cursoExistente = cursoRepository.findById(id)
                .filter(curso -> !curso.isEliminado())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado o eliminado con ID: " + id));

        // a) Actualizar el nombre
        if (ce.getNombre() != null && !ce.getNombre().trim().isEmpty()) {
            cursoExistente.setNombre(ce.getNombre());
        }

        if (ce.getNuevoProfesorId() != null) {
            // Buscar el nuevo profesor (debe existir y no estar eliminado)
            Profesor nuevoProfesor = profesorRepository.findById(ce.getNuevoProfesorId())
                    .filter(profesor -> !profesor.isEliminado())
                    .orElseThrow(() -> new RuntimeException("El nuevo profesor con ID: " + ce.getNuevoProfesorId() + " no fue encontrado o está eliminado."));

            // Asignar el nuevo profesor al curso
            cursoExistente.setProfesor(nuevoProfesor);
        }

        // 3. Guardar la entidad actualizada
        Curso cursoActualizado = cursoRepository.save(cursoExistente);

        // 4. Convertir a DTO y retornar
        return CursoMapper.toDTO(cursoActualizado);
    }

    @Override
    public CursoDto buscaId(Long id) {
        Optional<Curso> cursoOptional = cursoRepository.findById(id);

        if (cursoOptional.isPresent()) {
            Curso curso = cursoOptional.get();
            // 2. Verificar que el curso no esté eliminado
            if (!curso.isEliminado()) {
                Hibernate.initialize(curso.getEstudiantes());
                return CursoMapper.toDTO(curso);
            }
        }
        return null;
    }

    @Override
    public List<CursoDto> buscaTodos() {
        List<Curso> cursos = cursoRepository.findAllByEliminadoFalse();

        for (Curso curso : cursos) {
            Hibernate.initialize(curso.getEstudiantes());
        }

        return cursos.stream()
                .map(CursoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        // 1. Busca el curso y lanza una excepción si no existe o ya está eliminado.
        Curso curso = cursoRepository.findById(id)
                .filter(c -> !c.isEliminado())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado o ya eliminado con ID: " + id));

        // 2. Realizar borrado lógico
        curso.setEliminado(true);
        cursoRepository.save(curso);
    }

    @Override
    public CursoDto asignarEstudiantes(Long cursoId, AsignacionEstudiantes dto) {
        // 1. Buscar el curso y validar que exista y no esté eliminado
        Curso curso = cursoRepository.findById(cursoId)
                .filter(c -> !c.isEliminado())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + cursoId));

        // 2. Obtener la lista de estudiantes válidos (no eliminados)
        List<Estudiante> estudiantesNuevos = estudianteRepository.findAllById(dto.getEstudiantesIds()).stream()
                .filter(e -> !e.isEliminado())
                .collect(Collectors.toList());

        if (estudiantesNuevos.isEmpty() && !dto.getEstudiantesIds().isEmpty()) {
            throw new RuntimeException("Ningún estudiante válido para asignar.");
        }

        // 3. Agregar los estudiantes al conjunto del curso
        if (curso.getEstudiantes() == null) {
            curso.setEstudiantes(new HashSet<>());
        }
        curso.getEstudiantes().addAll(estudiantesNuevos);

        // 4. Guardar y retornar
        Curso cursoActualizado = cursoRepository.save(curso);
        return CursoMapper.toDTO(cursoActualizado);
    }

    @Override
    public List<CursoDto> buscaCursosPorEstudiante(Long estudianteId) {
        // 1. Buscar el estudiante por ID y que NO esté eliminado
        Estudiante estudiante = estudianteRepository.findByIdAndEliminadoFalse(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + estudianteId));

        // 2. Acceder a la colección de cursos del estudiante.
        return estudiante.getCursos().stream()
                .filter(c -> !c.isEliminado()) // Filtrar cursos que hayan sido eliminados lógicamente
                .map(CursoMapper::toDTO)
                .collect(Collectors.toList());
    }

}
