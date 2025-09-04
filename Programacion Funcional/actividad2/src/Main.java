import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        // Listado de alumnos
        List<Alumno> alumnos = Arrays.asList(
                new Alumno("Roberto", 9.5, "Matematica"),
                new Alumno("Franco", 7.0, "Matematica"),
                new Alumno("Mariano", 6.0, "Fisica"),
                new Alumno("Juan", 2.5, "Fisica")
        );

        // Alumnos con nota >= 7 y nombre en mayuscula
        alumnos.stream()
                .filter(alumno -> alumno.getNota() >= 7)
                .map(alumno -> alumno.getNombre().toUpperCase())
                .sorted()
                .forEach(System.out::println);

        // Promedio de notas
        OptionalDouble promedio = alumnos.stream()
                .mapToDouble(Alumno::getNota)
                .average();

        System.out.println("\nPromedio: " + promedio);

        // Agrupar alumnos por Curso
        alumnos.stream()
                .collect(Collectors.groupingBy( // agrupamos por categoría
                        Alumno::getCurso))
                .forEach((curso, alumno) -> System.out.println("\n" + curso + ": " + alumno));

        // 3 mejores promedios
        System.out.println("\nPromedios: ");
        alumnos.stream()
                .map(Alumno::getNota)
                .limit(3)
                .forEach(System.out::println);


    }
}