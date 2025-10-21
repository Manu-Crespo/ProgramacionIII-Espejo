package com.example.Parcial2.Controllers;

import com.example.Parcial2.Entities.DTOs.ProfesorCreate;
import com.example.Parcial2.Entities.DTOs.ProfesorEdit;
import com.example.Parcial2.Services.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*") // Permite peticiones desde cualquier origen
@RequestMapping("/profesor") // Mapeo base para todos los endpoints de profesor
public class ProfesorController {

    @Autowired
    ProfesorService profesorService;

    // POST /profesor/crear
    @PostMapping("/crear")
    public ResponseEntity<?> crear (@RequestBody ProfesorCreate pc){
        try {
            return ResponseEntity.ok().body(profesorService.crear(pc));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrió un error: " +e.getMessage());
        }
    }

    // PUT /profesor/actualizar/{id}
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody ProfesorEdit profesorEdit){
        try{
            return ResponseEntity.ok().body(profesorService.actualizar(id, profesorEdit));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrió un error: " +e.getMessage());
        }
    }

    // DELETE /profesor/borrar/{id} (Borrado Lógico)
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        try{
            profesorService.eliminar(id);
            return ResponseEntity.ok().body("Profesor eliminado lógicamente.");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrió un error: " +e.getMessage());
        }
    }

    // GET /profesor/buscaTodos
    @GetMapping("/buscaTodos")
    public ResponseEntity<?> buscaTodos(){
        try {
            return ResponseEntity.ok().body(profesorService.buscaTodos());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrió un error: " +e.getMessage());
        }
    }

    // GET /profesor/buscarId/{id}
    @GetMapping("/buscarId/{id}")
    public ResponseEntity<?> buscaId(@PathVariable Long id){
        try{
            return ResponseEntity.ok(profesorService.buscaId(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrió un error: " +e.getMessage());
        }
    }
}