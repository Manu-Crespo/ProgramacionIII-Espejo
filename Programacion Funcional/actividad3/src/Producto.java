import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Producto {
    private String nombre;
    private String categoria;
    private Double precio;
    private int stock;
}
