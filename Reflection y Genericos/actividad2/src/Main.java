import java.lang.reflect.*;
public class Main {
    public static void main(String[] args) {
        try {
            Class<?> clase =Class.forName("Persona");
            System.out.println("Clase: "+clase.getName());

            Constructor<?>[] constructores = clase.getConstructors();
            for (Constructor<?> c : constructores) {
                System.out.println("Constructor: " + c);
            }

            Constructor<Persona> constructor = Persona.class.getConstructor(String.class, int.class);
            Persona p = constructor.newInstance("Jorge", 25);
            Method toString = clase.getDeclaredMethod("toString");
            System.out.println(toString.invoke(p));

            Field nombreField = clase.getDeclaredField("nombre");
            nombreField.setAccessible(true);
            System.out.println("Constructor: "+ nombreField);
            nombreField.set(p, "Mariano");
            System.out.println("Nombre: "+nombreField.get(p));
            System.out.println(toString.invoke(p));

            Method secreto = clase.getDeclaredMethod("saludar");
            secreto.setAccessible(true);
            secreto.invoke(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}