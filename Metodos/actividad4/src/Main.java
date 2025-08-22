import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<Producto> listaProductos = new ArrayList<>();


        Producto p1 = new Producto("A1", "Laptop", 1200.50);
        Producto p2 = new Producto("B1", "Mouse", 25.00);
        Producto p3 = new Producto("A1", "Teclado", 50.00);

        listaProductos.add(p1);
        listaProductos.add(p2);

        if (!listaProductos.contains(p3)) {
            listaProductos.add(p3);
        } else {
            System.out.println("El producto con código '" + p3.getCodigo() + "' ya existe en la lista.");
        }

        System.out.println("Lista: " + listaProductos);
    }


}