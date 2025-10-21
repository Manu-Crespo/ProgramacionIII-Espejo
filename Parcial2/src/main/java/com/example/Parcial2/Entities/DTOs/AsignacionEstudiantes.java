package com.example.Parcial2.Entities.DTOs;

import lombok.*;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AsignacionEstudiantes {
    private List<Long> estudiantesIds;
}
