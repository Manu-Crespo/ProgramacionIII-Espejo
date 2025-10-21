package com.example.Parcial2.Entities.DTOs;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CursoCreate {
    private String nombre;
    private Long profesorId;
}