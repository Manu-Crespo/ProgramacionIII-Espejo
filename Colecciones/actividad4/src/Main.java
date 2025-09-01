import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Curso> listadoCursos = new HashMap<>();
        Curso c1 = new Curso("Matematica", "Dominguez");
        Curso c2 = new Curso("Lengua", "Martinez");
        Curso c3 = new Curso("Geografia", "Rodriguez");

        listadoCursos.put("01", c1);
        listadoCursos.put("02", c2);
        listadoCursos.put("03", c3);

        System.out.println(listadoCursos.get("01"));

        System.out.println(listadoCursos.entrySet());
    }
}