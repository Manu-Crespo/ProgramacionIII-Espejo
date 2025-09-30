package org.example;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Paciente")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true) // Agregado callSuper=true para incluir ID de Base
public class Paciente extends Base{
    private String nombre;
    private String apellido;
    private int edad;
    private int DNI;
    private String obraSocial;
    private LocalDate fechaDeNacimiento;
    private char sexo;
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default//Sin @Builder.Default: El Builder ignora la inicialización = new ArrayList<>(). El campo consultas
    // se inicializa a null al crear el objeto, lo cual causaría un error de NullPointerException en cuanto intentes
    // agregar un elemento.
    @ToString.Exclude
    private List<Consulta> consultas = new ArrayList<>();
    @Builder.Default
    @ManyToMany(mappedBy = "pacientes")
    @ToString.Exclude
    private List<Medicamento> medicamentos = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    @JoinColumn(name = "historia_clinica_id") //Sin @JoinColumn: Hibernate generaría un nombre por defecto
    // para la clave foránea, que podría ser algo genérico o largo como medico_id o medico_fk.
    // Con @JoinColumn(name = "historia_clinica_id"): Le estás diciendo explícitamente a Hibernate: "Crea una columna en la tabla
    // Paciente y llámala exactamente historia_clinica_id. Esta columna contendrá el ID del médico al que se refiere la consulta."
    private HistoriaClinica historiaClinica;
}
