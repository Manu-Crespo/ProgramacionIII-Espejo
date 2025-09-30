package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "Historia_Clinica")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)// Agregado callSuper=true para incluir ID de Base
public class HistoriaClinica extends Base{
    private String descripcion;
    @OneToOne(mappedBy = "historiaClinica")// Es la forma de decirle a JPA que el mapeo de la columna de la base de datos
    // ya está definido en la otra clase, manteniendo la coherencia de tu esquema relacional.
    @ToString.Exclude
    private Paciente paciente;
}
