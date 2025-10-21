package com.example.Parcial2.Entities.DTOs;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CursoEdit {
    private String nombre;
    private Long nuevoProfesorId;
}
