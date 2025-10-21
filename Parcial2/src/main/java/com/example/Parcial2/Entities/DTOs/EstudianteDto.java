package com.example.Parcial2.Entities.DTOs;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class EstudianteDto {
    private long id;
    private String nombre;
    private String matricula;
}
