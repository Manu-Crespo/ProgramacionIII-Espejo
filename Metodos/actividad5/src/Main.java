import java.util.HashSet;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Set<Producto> listaProductos = new HashSet<>();


        Producto p1 = new Producto("A1", "Laptop", 1200.50);
        Producto p2 = new Producto("B1", "Mouse", 25.00);
        Producto p3 = new Producto("A1", "Teclado", 50.00);

        System.out.println("Agregando producto: " + listaProductos.add(p1));
        System.out.println("Agregando producto: " + listaProductos.add(p2));
        System.out.println("Agregando producto: " + listaProductos.add(p3));

        System.out.println("Lista: " + listaProductos);
    }


}