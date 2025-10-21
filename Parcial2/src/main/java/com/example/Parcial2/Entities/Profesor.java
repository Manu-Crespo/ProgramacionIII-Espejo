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
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String email;

    @OneToMany(mappedBy = "profesor") // MappedBy al campo 'profesor'
    private Set<Curso> cursos;

    @Builder.Default
    private boolean eliminado = false;
}
