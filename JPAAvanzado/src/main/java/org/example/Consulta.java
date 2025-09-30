package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "Consulta")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)// Agregado callSuper=true para incluir ID de Base
public class Consulta extends Base{
    private LocalDate fecha;
    private String diagnostico;
    @ManyToOne
    @JoinColumn(name = "medico_id") // Nombra la columna FK
    // Sin @JoinColumn: Hibernate generaría un nombre por defecto para la clave foránea, que podría ser algo genérico o largo como medico_id o medico_fk.
    // Con @JoinColumn(name = "medico_id"): Le estás diciendo explícitamente a Hibernate: "Crea una columna en la tabla
    // Consulta y llámala exactamente medico_id. Esta columna contendrá el ID del médico al que se refiere la consulta."
    @ToString.Exclude
    private Medico medico;
    @ManyToOne
    @JoinColumn(name = "paciente_id") // Nombra la columna FK
    @ToString.Exclude
    private Paciente paciente;
}
