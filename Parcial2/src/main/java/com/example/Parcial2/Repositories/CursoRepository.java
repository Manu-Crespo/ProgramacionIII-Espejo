package com.example.Parcial2.Repositories;

import com.example.Parcial2.Entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findAllByEliminadoFalse();
}
