import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Producto p1 = Producto.builder()
                .codigo(01)
                .nombre("Laptop")
                .precio(500.50)
                .autor("Somsang")
                .build();

        Producto p2 = Producto.builder()
                .codigo(02)
                .nombre("Monitor")
                .precio(400.25)
                .autor("ELEGE")
                .build();

        Producto p3 = Producto.builder()
                .codigo(03)
                .nombre("Consola")
                .precio(600.0)
                .autor("SONIA")
                .build();

        ProductoDTO pDTO1 = new ProductoDTO(p1.getCodigo(), p1.getNombre(), p1.getPrecio());
        ProductoDTO pDTO2 = new ProductoDTO(p2.getCodigo(), p2.getNombre(), p2.getPrecio());
        ProductoDTO pDTO3 = new ProductoDTO(p3.getCodigo(), p3.getNombre(), p3.getPrecio());

        List<ProductoDTO> listadoProductosDTO = new ArrayList<>();
        listadoProductosDTO.add(pDTO1);
        listadoProductosDTO.add(pDTO2);
        listadoProductosDTO.add(pDTO3);

        System.out.println(listadoProductosDTO);
    }
}