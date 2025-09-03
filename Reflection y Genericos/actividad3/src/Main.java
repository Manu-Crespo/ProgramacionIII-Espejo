import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Caja cajaString = new Caja<>("Lorem ipsum");
        Caja cajaInteger = new Caja<>(123456);

        System.out.println(cajaString + "\n" + cajaInteger);

        List lista = new ArrayList<>();
        lista.add("Lorem ipsum");
        lista.add(123456);

        System.out.println(lista);

    }
}