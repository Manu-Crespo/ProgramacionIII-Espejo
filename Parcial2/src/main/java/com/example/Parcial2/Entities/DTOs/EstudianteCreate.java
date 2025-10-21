package com.example.Parcial2.Entities.DTOs;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class EstudianteCreate {
    private String nombre;
    private String matricula;
}
