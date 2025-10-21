package com.example.Parcial2.Controllers;

import com.example.Parcial2.Entities.DTOs.EstudianteCreate;
import com.example.Parcial2.Entities.DTOs.EstudianteEdit;
import com.example.Parcial2.Services.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*") // Permite peticiones desde cualquier origen
@RequestMapping("/estudiante") // Mapeo base para todos los endpoints de estudiante
public class EstudianteController {

    @Autowired
    EstudianteService estudianteService;

    // POST /estudiante/crear
    @PostMapping("/crear")
    public ResponseEntity<?> crear (@RequestBody EstudianteCreate ec){
        try {
            return ResponseEntity.ok().body(estudianteService.crear(ec));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrió un error: " +e.getMessage());
        }
    }

    // PUT /estudiante/actualizar/{id}
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody EstudianteEdit estudianteEdit){
        try{
            return ResponseEntity.ok().body(estudianteService.actualizar(id, estudianteEdit));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrió un error: " +e.getMessage());
        }
    }

    // DELETE /estudiante/borrar/{id} (Borrado Lógico)
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        try{
            estudianteService.eliminar(id);
            return ResponseEntity.ok().body("Estudiante eliminado lógicamente.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrió un error: " +e.getMessage());
        }
    }

    // GET /estudiante/buscaTodos
    @GetMapping("/buscaTodos")
    public ResponseEntity<?> buscaTodos(){
        try {
            return ResponseEntity.ok().body(estudianteService.buscaTodos());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrió un error: " +e.getMessage());
        }
    }

    // GET /estudiante/buscarId/{id}
    @GetMapping("/buscarId/{id}")
    public ResponseEntity<?> buscaId(@PathVariable Long id){
        try{
            return ResponseEntity.ok(estudianteService.buscaId(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrió un error: " +e.getMessage());
        }
    }
}