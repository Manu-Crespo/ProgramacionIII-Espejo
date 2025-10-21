package com.example.Parcial2.Services;


import com.example.Parcial2.Entities.Curso;
import com.example.Parcial2.Entities.DTOs.ProfesorCreate;
import com.example.Parcial2.Entities.DTOs.ProfesorDto;
import com.example.Parcial2.Entities.DTOs.ProfesorEdit;
import com.example.Parcial2.Entities.Mapper.ProfesorMapper;
import com.example.Parcial2.Entities.Profesor;
import com.example.Parcial2.Repositories.ProfesorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProfesorServiceImp implements ProfesorService{
    @Autowired
    private ProfesorRepository profesorRepository;


    @Override
    public ProfesorDto crear(ProfesorCreate p) {
        Profesor profesor = ProfesorMapper.toEntity(p);

        // 2. Guardar la entidad en la base de datos
        Profesor profesorGuardado = profesorRepository.save(profesor);

        // 3. Convertir la entidad guardada a DTO de respuesta
        return ProfesorMapper.toDTO(profesorGuardado);
    }

    @Override
    public ProfesorDto actualizar(Long id, ProfesorEdit pe) {
        // 1. Buscar el profesor por ID y que NO esté eliminado
        Optional<Profesor> profesorOptional = profesorRepository.findById(id)
                .filter(profesor -> !profesor.isEliminado());

        if (profesorOptional.isPresent()) {
            Profesor profesorExistente = profesorOptional.get();

            // 2. Aplicar los cambios del DTO de edición.
            if (pe.getNombre() != null && !pe.getNombre().trim().isEmpty()) {
                profesorExistente.setNombre(pe.getNombre());
            }

            if (pe.getEmail() != null && !pe.getEmail().trim().isEmpty()) {
                profesorExistente.setEmail(pe.getEmail());
            }
            // 3. Guardar la entidad actualizada
            Profesor profesorActualizado = profesorRepository.save(profesorExistente);

            // 4. Convertir a DTO y retornar
            return ProfesorMapper.toDTO(profesorActualizado);
        }
        throw new RuntimeException("Profesor no encontrado o ya eliminado con ID: " + id);
    }

    @Override
    public ProfesorDto buscaId(Long id) {
        return profesorRepository.findById(id)
                .filter(profesor -> !profesor.isEliminado())
                .map(ProfesorMapper::toDTO)
                .orElse(null);
    }

    @Override
    public List<ProfesorDto> buscaTodos() {
        List<Profesor> profesores = profesorRepository.findAllByEliminadoFalse();

        // Mapear la lista de entidades a una lista de DTOs
        return profesores.stream()
                .map(ProfesorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        // 1. Busca el profesor y lanza una excepción si no existe o ya está eliminado.
        Profesor profesor = profesorRepository.findById(id)
                .filter(c -> !c.isEliminado())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado o ya eliminado con ID: " + id));

        // 2. Realizar borrado lógico
        profesor.setEliminado(true);
        profesorRepository.save(profesor);
    }
}
