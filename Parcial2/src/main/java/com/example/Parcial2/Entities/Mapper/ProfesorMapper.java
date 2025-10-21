package com.example.Parcial2.Entities.Mapper;

import com.example.Parcial2.Entities.DTOs.ProfesorCreate;
import com.example.Parcial2.Entities.DTOs.ProfesorDto;
import com.example.Parcial2.Entities.Profesor;

public class ProfesorMapper {
    public static ProfesorDto toDTO(Profesor p){
        if (p == null || p.isEliminado()) return null;
        return ProfesorDto.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .email(p.getEmail())
                .build();
    }

    public static Profesor toEntity(ProfesorCreate pc){
        if (pc == null) return null;
        return Profesor.builder()
                .nombre(pc.getNombre())
                .email(pc.getEmail())
                .build();
    }
}
