package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Medicamento")
@Builder
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)// Agregado callSuper=true para incluir ID de Base
public class Medicamento extends Base{
    private String nombre;
    private String droga;
    private int pesoEnGramos;
    @Builder.Default //Sin @Builder.Default: El Builder ignora la inicialización = new ArrayList<>(). El campo consultas
    // se inicializa a null al crear el objeto, lo cual causaría un error de NullPointerException en cuanto intentes
    // agregar un elemento.
    @ManyToMany
    @ToString.Exclude
    List<Paciente> pacientes = new ArrayList<>();
}
