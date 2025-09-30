package org.example;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.example.Paciente;
import org.example.Base;
import org.example.Medicamento;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Lee la configuración de la unidad de persistencia (<persistence-unit>).
        //Establece la conexión a la base de datos y la gestión de recursos de forma eficiente.
        //Proporciona el metodo createEntityManager() para crear instancias de EntityManager.

        //Representa un "contexto de persistencia", que es un conjunto de entidades que un EntityManager está gestionando en un momento dado.
        //Realiza las operaciones CRUD (Crear, Leer, Actualizar, Borrar) sobre las entidades.
        //Sincroniza el estado de las entidades con la base de datos.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidad");
        EntityManager em = emf.createEntityManager();

        //  Persiste al menos:
        //●	3 pacientes

        //metodo clave que marca el inicio de una transacción en JPA.
        em.getTransaction().begin();
        //instanciando pacientes
        Paciente paciente1 = Paciente.builder()
                .nombre("Maria Elena")
                .apellido("Fuseneco")
                .edad(55)
                .DNI(125125125)
                .obraSocial("SWISS MEDICAL")
                .fechaDeNacimiento(LocalDate.of(1950, 05, 22))
                .sexo('F')
                .build();
        Paciente paciente2 = Paciente.builder()
                .nombre("Gustavo")
                .apellido("Costas")
                .edad(15)
                .DNI(65656565)
                .obraSocial("SWISS MEDICAL")
                .fechaDeNacimiento(LocalDate.of(2010, 11, 13))
                .sexo('M')
                .build();
        Paciente paciente3 = Paciente.builder()
                .nombre("Pepe")
                .apellido("Argento")
                .edad(65)
                .DNI(789789789)
                .obraSocial("OSECAC")
                .fechaDeNacimiento(LocalDate.of(1945, 01, 02))
                .sexo('M')
                .build();

        //●	2 médicos.
        Medico medico1 = Medico.builder()
                .nombre("Mariano")
                .apellido("De la Fuente")
                .edad(59)
                .especialidad("Genetista")
                .matricula("M-3221")
                .build();
        Medico medico2 = Medico.builder()
                .nombre("Lisandro")
                .apellido("Martinez")
                .edad(32)
                .especialidad("Pediatria")
                .matricula("M-4581")
                .build();

        //●	4 consultas (con fechas distintas y diagnósticos variados).
        Consulta consulta1 = Consulta.builder()
                .fecha(LocalDate.of(2025, 11, 05))
                .diagnostico("Control general")
                .build();
        Consulta consulta2 = Consulta.builder()
                .fecha(LocalDate.of(2025, 12, 12))
                .diagnostico("Diabetes")
                .build();
        Consulta consulta3 = Consulta.builder()
                .fecha(LocalDate.of(2026, 01, 25))
                .diagnostico("Hipertiroidismo")
                .build();
        Consulta consulta4 = Consulta.builder()
                .fecha(LocalDate.of(2026, 02, 15))
                .diagnostico("Depresion")
                .build();

        //●	2 historiales clínicos.
        HistoriaClinica historiaC1 = HistoriaClinica.builder()
                .descripcion("Leve arritmia")
                .build();
        HistoriaClinica historiaC2 = HistoriaClinica.builder()
                .descripcion("Alergico a paracetamol")
                .build();
        HistoriaClinica historiaC3 = HistoriaClinica.builder()
                .descripcion("Escoliosis")
                .build();

        //●	3 medicamentos.
        Medicamento medicamento1 = Medicamento.builder()
                .nombre("Axual")
                .droga("Pregabalina")
                .pesoEnGramos(150)
                .build();
        Medicamento medicamento2 = Medicamento.builder()
                .nombre("Aurene")
                .droga("Oxcarbazepina")
                .pesoEnGramos(600)
                .build();
        Medicamento medicamento3 = Medicamento.builder()
                .nombre("Distalene")
                .droga("Anastrozol")
                .pesoEnGramos(1)
                .build();

        //Su función principal es hacer que una instancia de una entidad pase a ser gestionada por el contexto de persistencia, lo que la prepara para
        // ser guardada en la base de datos.
        em.persist(paciente1);
        em.persist(paciente2);
        em.persist(paciente3);
        em.persist(medico1);
        em.persist(medico2);
        em.persist(consulta1);
        em.persist(consulta2);
        em.persist(consulta3);
        em.persist(consulta4);
        em.persist(historiaC1);
        em.persist(historiaC2);
        em.persist(historiaC3);
        em.persist(medicamento1);
        em.persist(medicamento2);
        em.persist(medicamento3);
        //finaliza una transacción en JPA. Su función es crucial porque es el momento en que todos los cambios que has "preparado" se aplican realmente
        // a la base de datos de manera atómica.
        em.getTransaction().commit();

        //3.Listar todos los pacientes mayores de 30 años

        em.getTransaction().begin();

        List<Paciente> pacientes = em.createQuery("SELECT p FROM Paciente p WHERE edad > 30", Paciente.class).getResultList();
        System.out.println("\nPACIENTES MAYORES DE 30 AÑOS:");
        pacientes.forEach(p -> System.out.println("\n" + "ID: " + p.getId() + " " + p));

        em.getTransaction().commit();

        //4.Obtener todas las consultas realizadas por un médico específico
        em.getTransaction().begin();

        medico1.getConsultas().add(consulta1);
        medico1.getConsultas().add(consulta2);
        medico2.getConsultas().add(consulta3);
        medico2.getConsultas().add(consulta4);

        Medico medicoConsulta = em.createQuery("SELECT m FROM Medico m WHERE matricula = 'M-3221'", Medico.class).getSingleResult();
        System.out.println("\nCONSULTAS REALIZADAS POR UN MEDICO:");
        System.out.println("\nID: " + medicoConsulta.getId() + "\nNombre: " + medicoConsulta.getNombre() + " " + medicoConsulta.getApellido() + "\nMatricula: " + medicoConsulta.getMatricula() +
                "\nConsultas: " + medicoConsulta.getConsultas());

        em.getTransaction().commit();

        //5.Mostrar todos los medicamentos asociados a un paciente.

        em.getTransaction().begin();

        paciente1.getMedicamentos().add(medicamento2);
        paciente1.getMedicamentos().add(medicamento3);

        Paciente consultaPaciente = em.createQuery("SELECT p FROM Paciente p WHERE DNI = 125125125", Paciente.class).getSingleResult();
        System.out.println("\nMEDICAMENTOS DE UN PACIENTE:");
        System.out.println("\nNombre: " + consultaPaciente.getNombre() + " " + consultaPaciente.getApellido() + "\nDNI: " + consultaPaciente.getDNI() +
                "\nMedicamentos: " + consultaPaciente.getMedicamentos());

        em.getTransaction().commit();

        //6.Listar las consultas con su diagnóstico y el nombre del paciente.

        em.getTransaction().begin();

        consulta1.setPaciente(paciente1);
        consulta2.setPaciente(paciente2);
        consulta3.setPaciente(paciente3);
        consulta4.setPaciente(paciente1);

        List<Consulta> consultas = em.createQuery("SELECT c FROM Consulta c", Consulta.class).getResultList();
        System.out.println("\nCONSULTAS CON DIAGNOSTICO Y NOMBRE:");
        consultas.forEach(c -> System.out.println("\nID: " + c.getId() + "\nNombre: " + c.getPaciente().getNombre() + " " + c.getPaciente().getApellido() +
                "\nDiagnostico: " + c.getDiagnostico()));

        em.getTransaction().commit();

        //7.Calcular el promedio de edad de los pacientes.
        em.getTransaction().begin();

        List<Paciente> pacientesEdad = em.createQuery("SELECT p FROM Paciente p", Paciente.class).getResultList();
        double promedio = pacientesEdad.stream()
                .mapToInt(Paciente::getEdad)
                .average()
                .orElse(0.0);

        System.out.println("\nPromedio de edad de los pacientes es: " + promedio);

        em.getTransaction().commit();

        //8.Listar todos los pacientes que tienen una obra social específica.
        em.getTransaction().begin();

        List<Paciente> pacientesEOS = em.createQuery("SELECT p FROM Paciente p WHERE obraSocial = 'SWISS MEDICAL'", Paciente.class).getResultList();
        System.out.println("\nPACIENTES CON OBRA SOCIAL ESPECIFICA:");
        pacientesEOS.forEach(p -> System.out.println("\nID: " + p.getId() + "\nNombre: " + p.getNombre() + " " + p.getApellido() +
                "\nObra Social: " + p.getObraSocial()));

        em.getTransaction().commit();

        //9.Mostrar los médicos y la cantidad de consultas que atendieron.
        em.getTransaction().begin();

        List<Medico> medicosConsultas = em.createQuery("SELECT m FROM Medico m", Medico.class).getResultList();
        System.out.println("\nCANTIDAD DE CONSULTAS POR MEDICO: ");
        for (Medico m : medicosConsultas) {
            int cantidad = m.getConsultas().size();
            System.out.println("\nID: " + m.getId() + "\nNombre: " + m.getNombre() + " " + m.getApellido() +
                    "\nMatricula: " + m.getMatricula() + "\nCantidad Consultas: " + cantidad);
        }

        em.getTransaction().commit();

        //10.Obtener todos los pacientes junto con la descripción de su historia clínica.
        em.getTransaction().begin();

        paciente1.setHistoriaClinica(historiaC1);
        paciente2.setHistoriaClinica(historiaC2);


        List<Paciente> pacienteHistorial = em.createQuery("SELECT p FROM Paciente p", Paciente.class).getResultList();
        System.out.println("\nPACIENTE CON HISTORIAL CLINICO: ");
        pacienteHistorial.forEach(p -> {
            System.out.println("\n"+p);
            HistoriaClinica hc = p.getHistoriaClinica();
            if (hc != null){
                System.out.println(p.getHistoriaClinica());
            }else {
                System.out.println("No tiene historial Clinico");
            }
        });

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
