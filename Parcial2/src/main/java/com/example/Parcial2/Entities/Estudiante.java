package com.example.Parcial2.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String matricula;

    @ManyToMany(mappedBy = "estudiantes") // MappedBy al campo 'estudiantes' en la entidad Curso
    private Set<Curso> cursos;

    @Builder.Default
    private boolean eliminado = false;
}
