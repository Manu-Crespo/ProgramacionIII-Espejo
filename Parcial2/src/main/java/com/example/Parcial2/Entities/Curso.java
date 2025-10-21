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
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    @Builder.Default
    private boolean eliminado = false;

    @ManyToOne
    @JoinColumn(name = "profesor_id") // Columna de la clave foránea
    private Profesor profesor;

    // Relación ManyToMany con Estudiante
    @ManyToMany
    @JoinTable(
            name = "curso_estudiante", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "estudiante_id")
    )
    private Set<Estudiante> estudiantes;
}
