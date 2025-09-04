import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Alumno {
    private String nombre;
    private Double nota;
    private String curso;
}
