import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoDTO {
    private int codigo;
    private String nombre;
    private Double precio;
}
