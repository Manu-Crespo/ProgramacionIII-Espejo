import java.util.List;

public class Curso {
    private String nombre;
    private List<Estudiante> listaDeEstudiantes;
    private String profesor;

    public Curso(String nombre, List<Estudiante> listaDeEstudiantes, String profesor) {
        this.nombre = nombre;
        this.listaDeEstudiantes = listaDeEstudiantes;
        this.profesor = profesor;
    }

    public Curso() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Estudiante> getListaDeEstudiantes() {
        return listaDeEstudiantes;
    }

    public void setListaDeEstudiantes(List<Estudiante> listaDeEstudiantes) {
        this.listaDeEstudiantes = listaDeEstudiantes;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "Curso='" + nombre + '\'' +
                ", Profesor='" + profesor + '\'' +
                ", Cantidad de alumnos=" + listaDeEstudiantes.size() +
                ", \nAlumnos ='" + listaDeEstudiantes + '\'' +
                "}\n";
    }
}

