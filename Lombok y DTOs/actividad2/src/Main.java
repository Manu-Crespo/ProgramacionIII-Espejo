public class Main {
    public static void main(String[] args) {
        Usuario u1 = Usuario.builder()
                .id(1)
                .nombre("Marcos")
                .email("marcos@gamil.com")
                .build();

        Usuario u2 = Usuario.builder()
                .id(2)
                .nombre("Roberto")
                .email("roberto@gamil.com")
                .build();

        System.out.println(u1 + "\n" + u2);
    }
}