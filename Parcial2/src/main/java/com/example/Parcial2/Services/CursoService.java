package com.example.Parcial2.Services;

import com.example.Parcial2.Entities.DTOs.AsignacionEstudiantes;
import com.example.Parcial2.Entities.DTOs.CursoCreate;
import com.example.Parcial2.Entities.DTOs.CursoEdit;
import com.example.Parcial2.Entities.DTOs.CursoDto;
import java.util.List;

public interface CursoService {
    CursoDto crear(CursoCreate c);
    CursoDto actualizar(Long id, CursoEdit p);
    CursoDto buscaId(Long id);
    List<CursoDto> buscaTodos();
    void eliminar(Long id);


    CursoDto asignarEstudiantes(Long cursoId, AsignacionEstudiantes dto);
    List<CursoDto> buscaCursosPorEstudiante(Long estudianteId);
}
