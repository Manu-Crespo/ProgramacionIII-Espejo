package com.example.Parcial2.Entities.Mapper;

import com.example.Parcial2.Entities.Curso;
import com.example.Parcial2.Entities.DTOs.CursoCreate;
import com.example.Parcial2.Entities.DTOs.CursoDto;
import com.example.Parcial2.Entities.DTOs.EstudianteDto;
import com.example.Parcial2.Entities.Mapper.ProfesorMapper;
import com.example.Parcial2.Entities.Mapper.EstudianteMapper;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CursoMapper {
    public static CursoDto toDTO(Curso c){
        if (c == null || c.isEliminado()) return null;

        Set<EstudianteDto> estudiantesDto = c.getEstudiantes() != null ?
                c.getEstudiantes().stream()
                        .map(EstudianteMapper::toDTO)
                        .collect(Collectors.toSet())
                : Set.of();
        // El DTO del curso solo devuelve su información y la del profesor.
        return CursoDto.builder()
                .id(c.getId())
                .nombre(c.getNombre())
                .profesor(ProfesorMapper.toDTO(c.getProfesor()))
                .estudiantes(estudiantesDto)// Mapea el profesor asociado
                .build();
    }

    // Se actualiza para que NO mapee el profesor, ya que el servicio lo buscará.
    public static Curso toEntity(CursoCreate cc){
        if (cc == null) return null;
        return Curso.builder()
                .nombre(cc.getNombre())
                .build();
    }

    // Metodo para convertir un Curso con Estudiantes a una lista de DTOs de Estudiantes
    public static List<EstudianteDto> toEstudianteDtoList(Curso c) {
        if (c == null || c.getEstudiantes() == null) return List.of();

        return c.getEstudiantes().stream()
                .map(EstudianteMapper::toDTO)
                .collect(Collectors.toList());
    }
}
