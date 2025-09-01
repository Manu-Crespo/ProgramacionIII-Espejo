import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Alumno a = new Alumno("Lionel Messi", 10.0);
        Alumno a1 = new Alumno("Edinson Cavani", 1.5);
        Alumno a2 = new Alumno("Gaston Hernandez", 8.5);

        List <Alumno> listadoAlumnos = new ArrayList<>();

        listadoAlumnos.add(a);
        listadoAlumnos.add(a1);
        listadoAlumnos.add(a2);

        for (Alumno alumno : listadoAlumnos){
            System.out.println(alumno);
        }
    }
}