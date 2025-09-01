public class Main {
    public static void main(String[] args) {
        Persona p1 = Persona.builder()
                .edad(25)
                .nombre("Ramiro")
                .build();

        Persona p2 = Persona.builder().build();

        System.out.println(p1 + "\n" + p2);

    }
}