import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Producto> listadoProductos = new HashSet<>();

        Producto p1 = new Producto(01, "Laptop");
        Producto p2 = new Producto(02, "Monitor");
        Producto p3 = new Producto(01, "Laptop");

        listadoProductos.add(p1);
        listadoProductos.add(p2);
        listadoProductos.add(p3);

        System.out.println(listadoProductos);
    }
}