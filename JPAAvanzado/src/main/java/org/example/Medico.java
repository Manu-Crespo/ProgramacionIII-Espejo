package org.example;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Medico")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)// Agregado callSuper=true para incluir ID de Base
public class Medico extends Base{
    private String nombre;
    private String apellido;
    private int edad;
    private String especialidad;
    private String matricula;
    @Builder.Default//Sin @Builder.Default: El Builder ignora la inicialización = new ArrayList<>(). El campo consultas
    // se inicializa a null al crear el objeto, lo cual causaría un error de NullPointerException en cuanto intentes
    // agregar un elemento.
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true) // Eliminar el hijo si se desvincula del padre
    @ToString.Exclude
    private List<Consulta> consultas = new ArrayList<>();
}
