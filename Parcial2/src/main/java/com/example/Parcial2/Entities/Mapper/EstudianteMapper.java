package com.example.Parcial2.Entities.Mapper;

import com.example.Parcial2.Entities.DTOs.EstudianteCreate;
import com.example.Parcial2.Entities.DTOs.EstudianteDto;
import com.example.Parcial2.Entities.Estudiante;

public class EstudianteMapper {
    public static EstudianteDto toDTO(Estudiante e){
        if (e == null || e.isEliminado()) return null;
        return EstudianteDto.builder()
                .id(e.getId())
                .nombre(e.getNombre())
                .matricula(e.getMatricula())
                .build();
    }

    public static Estudiante toEntity(EstudianteCreate ec){
        if (ec == null) return null;
        return Estudiante.builder()
                .nombre(ec.getNombre())
                .matricula(ec.getMatricula())
                .build();
    }
}
