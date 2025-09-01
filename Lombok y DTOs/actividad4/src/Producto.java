import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Producto {
    private String codigo;
    private String nombre;
    private String proveedor;
    private double precio;
}
