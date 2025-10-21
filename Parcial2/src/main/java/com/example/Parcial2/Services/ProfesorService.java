package com.example.Parcial2.Services;

import com.example.Parcial2.Entities.DTOs.ProfesorCreate;
import com.example.Parcial2.Entities.DTOs.ProfesorDto;
import com.example.Parcial2.Entities.DTOs.ProfesorEdit;

import java.util.List;

public interface ProfesorService {
    ProfesorDto crear(ProfesorCreate c);
    ProfesorDto actualizar(Long id, ProfesorEdit p);
    ProfesorDto buscaId(Long id);
    List<ProfesorDto> buscaTodos();
    void eliminar(Long id);
}
