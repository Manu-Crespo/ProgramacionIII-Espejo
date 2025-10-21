package com.example.Parcial2.Repositories;

import com.example.Parcial2.Entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    List<Estudiante> findAllByEliminadoFalse();
    Optional<Estudiante> findByIdAndEliminadoFalse(Long id);
}