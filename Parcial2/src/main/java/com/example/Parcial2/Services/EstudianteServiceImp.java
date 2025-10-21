package com.example.Parcial2.Services;

import com.example.Parcial2.Entities.Curso;
import com.example.Parcial2.Entities.DTOs.EstudianteCreate;
import com.example.Parcial2.Entities.DTOs.EstudianteDto;
import com.example.Parcial2.Entities.DTOs.EstudianteEdit;
import com.example.Parcial2.Entities.Estudiante;
import com.example.Parcial2.Entities.Mapper.EstudianteMapper;
import com.example.Parcial2.Repositories.EstudianteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EstudianteServiceImp implements EstudianteService{
    @Autowired
    private EstudianteRepository estudianteRepository;


    @Override
    public EstudianteDto crear(EstudianteCreate e) {
        Estudiante estudiante = EstudianteMapper.toEntity(e);

        // 2. Guardar la entidad en la base de datos
        Estudiante estudianteGuardado = estudianteRepository.save(estudiante);

        // 3. Convertir la entidad guardada a DTO de respuesta
        return EstudianteMapper.toDTO(estudianteGuardado);
    }


    @Override
    public EstudianteDto actualizar(Long id, EstudianteEdit ed) {
        // 1. Buscar el estudiante por ID y que NO esté eliminado
        Optional<Estudiante> estudianteOptional = estudianteRepository.findById(id)
                .filter(estudiante -> !estudiante.isEliminado());

        if (estudianteOptional.isPresent()) {
            Estudiante estudianteExistente = estudianteOptional.get();

            // 2. Aplicar los cambios del DTO de edición.
            if (ed.getNombre() != null && !ed.getNombre().trim().isEmpty()) {
                estudianteExistente.setNombre(ed.getNombre());
            }

            if (ed.getMatricula() != null && !ed.getMatricula().trim().isEmpty()) {
                estudianteExistente.setMatricula(ed.getMatricula());
            }

            // 3. Guardar la entidad actualizada
            Estudiante estudianteActualizado = estudianteRepository.save(estudianteExistente);

            // 4. Convertir a DTO y retornar
            return EstudianteMapper.toDTO(estudianteActualizado);
        }

        throw new RuntimeException("Estudiante no encontrado o ya eliminado con ID: " + id);
    }

    @Override
    public EstudianteDto buscaId(Long id) {
        return estudianteRepository.findById(id)
                .filter(estudiante -> !estudiante.isEliminado())
                .map(EstudianteMapper::toDTO)
                .orElse(null);
    }

    @Override
    public List<EstudianteDto> buscaTodos() {
        List<Estudiante> estudiantes = estudianteRepository.findAllByEliminadoFalse();

        // Mapear la lista de entidades a una lista de DTOs
        return estudiantes.stream()
                .map(EstudianteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        // 1. Busca el estudiante y lanza una excepción si no existe o ya está eliminado.
        Estudiante estudiante = estudianteRepository.findById(id)
                .filter(c -> !c.isEliminado())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado o ya eliminado con ID: " + id));

        // 2. Realizar borrado lógico
        estudiante.setEliminado(true);
        estudianteRepository.save(estudiante);
    }
}
