import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        // Lista de productos
        List<Producto> productos = Arrays.asList(
                new Producto("Monitor", "Electrónica", 250.0, 10),
                new Producto("Mouse", "Electrónica", 50.50, 30),
                new Producto("Silla", "Muebles", 150.25, 5),
                new Producto("Lámpara", "Hogar", 80.75, 12),
                new Producto("Teclado", "Electrónica", 120.0, 20),
                new Producto("Mesa", "Muebles", 300.0, 2)
        );

        // Productos con precio mayor a 100, ordenados por precio descendente
        productos.stream()
                .filter(prod -> prod.getPrecio() > 100)
                .sorted(Comparator.comparingDouble(Producto::getPrecio).reversed())
                .forEach(System.out::println);

        // Agrupar productos por categoría y calcular el stock total.
        System.out.println();
        productos.stream()
                .collect(Collectors.groupingBy(Producto::getCategoria))
                .forEach((categoria, stock) -> System.out.println(categoria + ": " + stock));

        // Generar un String separando con “;” cada producto que contenga nombre y precio
        System.out.println();
        String reporteCSV = productos.stream()
                .map(prod -> prod.getNombre() + ";" + prod.getPrecio())
                .collect(Collectors.joining("\n"));
        System.out.println(reporteCSV);

        // Calcular el precio promedio general y por categoría
        System.out.println();
        productos.stream()
                .mapToDouble(Producto::getPrecio)
                .average()
                .ifPresent(promedio -> System.out.println("Promedio general: $" + String.format("%.2f", promedio)));

        System.out.println();
        productos.stream()
                .collect(Collectors.groupingBy(Producto::getCategoria,
                        Collectors.averagingDouble(Producto::getPrecio)))
                .forEach((categoria, promedio) -> System.out.println("Promedio precio " + categoria + ": $" + String.format("%.2f", promedio)));
    }
}