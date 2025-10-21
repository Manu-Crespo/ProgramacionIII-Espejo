package com.example.Parcial2.Controllers;

import com.example.Parcial2.Entities.DTOs.AsignacionEstudiantes;
import com.example.Parcial2.Entities.DTOs.CursoCreate;
import com.example.Parcial2.Entities.DTOs.CursoDto;
import com.example.Parcial2.Entities.DTOs.CursoEdit;
import com.example.Parcial2.Services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    CursoService cursoService;

    @PostMapping("/crear")
    public ResponseEntity<?> crear (@RequestBody CursoCreate c){
        try {
            return ResponseEntity.ok().body(cursoService.crear(c));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrio un error " +e.getMessage());
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody CursoEdit cursoEdit){
        try{
            return ResponseEntity.ok().body(cursoService.actualizar(id, cursoEdit));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrio un error " +e.getMessage());
        }
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        try{
            cursoService.eliminar(id);
            return ResponseEntity.ok().body("Entidad eliminada");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrio un error " +e.getMessage());
        }
    }

    @GetMapping("/buscaTodos")
    public ResponseEntity<?> buscaTodos(){
        try {
            return ResponseEntity.ok().body(cursoService.buscaTodos());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrio un error " +e.getMessage());
        }
    }

    @GetMapping("/buscarId/{id}")
    public ResponseEntity<?> buscaId(@PathVariable Long id){
        try{
            return ResponseEntity.ok(cursoService.buscaId(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrio un error " +e.getMessage());
        }
    }

    @PutMapping("/{cursoId}/estudiantes")
    public ResponseEntity<?> asignarEstudiantes(@PathVariable Long cursoId, @RequestBody AsignacionEstudiantes dto) {
        try {
            // La llamada al servicio debe usar el nombre de la nueva clase DTO
            CursoDto cursoActualizado = cursoService.asignarEstudiantes(cursoId, dto);
            return ResponseEntity.ok().body(cursoActualizado);
        } catch (RuntimeException e) {
            // Captura Curso o Estudiante no encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al asignar estudiantes: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno: " + e.getMessage());
        }
    }

    @GetMapping("/cursos/{estudianteId}")
    public ResponseEntity<?> getCursosByEstudiante(@PathVariable Long estudianteId) {
        try {
            // La llamada al servicio debe ser la función de BUSCAR cursos, no ASIGNAR
            List<CursoDto> cursos = cursoService.buscaCursosPorEstudiante(estudianteId);
            return ResponseEntity.ok().body(cursos);
        } catch (RuntimeException e) {
            // Captura Estudiante no encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Ocurrió un error interno: " + e.getMessage());
        }
    }
}
