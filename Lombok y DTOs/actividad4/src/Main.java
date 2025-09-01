import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Producto p1 = Producto.builder()
                .codigo("01")
                .nombre("Laptop")
                .proveedor("Somsang")
                .precio(500.50)
                .build();

        Producto p2 = Producto.builder()
                .codigo("02")
                .nombre("Monitor")
                .proveedor("ELEGE")
                .precio(400.25)
                .build();

        Producto p3 = Producto.builder()
                .codigo("03")
                .nombre("Consola")
                .proveedor("SONIA")
                .precio(600.0)
                .build();

        ProductoRecord pRecord1 = new ProductoRecord(p1.getCodigo(), p1.getNombre(),p1.getPrecio());
        ProductoRecord pRecord2 = new ProductoRecord(p2.getCodigo(), p2.getNombre(),p2.getPrecio());
        ProductoRecord pRecord3 = new ProductoRecord(p3.getCodigo(), p3.getNombre(),p3.getPrecio());

        List<ProductoRecord> listadoProductos = new ArrayList<>();
        listadoProductos.add(pRecord1);
        listadoProductos.add(pRecord2);
        listadoProductos.add(pRecord3);

        System.out.println(listadoProductos);
    }
}