import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> cadenas = new ArrayList<>();
        cadenas.add("Nombre");
        cadenas.add("Apellido");
        cadenas.add("DNI");
        
        List<Integer> enteros = new ArrayList<>();
        enteros.add(12);
        enteros.add(34);
        enteros.add(56);

        imprimirLista(cadenas);
        imprimirLista(enteros);

    }

    public static <T> void imprimirLista(List<T> lista) {
        for (T l : lista) {
            System.out.print(l + " ");
        }
        System.out.println();
    }
}