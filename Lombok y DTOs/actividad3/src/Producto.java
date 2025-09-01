import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Producto {
    private int codigo;
    private String nombre;
    private Double precio;
    private String autor;
}
