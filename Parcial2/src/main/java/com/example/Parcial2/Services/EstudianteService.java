package com.example.Parcial2.Services;

import com.example.Parcial2.Entities.DTOs.EstudianteCreate;
import com.example.Parcial2.Entities.DTOs.EstudianteDto;
import com.example.Parcial2.Entities.DTOs.EstudianteEdit;

import java.util.List;

public interface EstudianteService {
    EstudianteDto crear(EstudianteCreate c);
    EstudianteDto actualizar(Long id, EstudianteEdit p);
    EstudianteDto buscaId(Long id);
    List<EstudianteDto> buscaTodos();
    void eliminar(Long id);
}
