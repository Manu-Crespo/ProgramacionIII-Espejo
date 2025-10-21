package com.example.Parcial2.Entities.DTOs;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CursoDto {
    private long id;
    private String nombre;
    private ProfesorDto profesor;
    private Set<EstudianteDto> estudiantes;
}