import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Estudiante e1 = new Estudiante("Juan",20, "Ingenieria en Sistemas");
        Estudiante e2 = new Estudiante("Maria",22, "Diseño Grafico");
        Estudiante e3 = new Estudiante("Pedro",21, "Medicina");

        List<Estudiante> listadoEstudiantes = new ArrayList<>();

        listadoEstudiantes.add(e1);
        listadoEstudiantes.add(e2);
        listadoEstudiantes.add(e3);

        System.out.println(listadoEstudiantes);
    }
}