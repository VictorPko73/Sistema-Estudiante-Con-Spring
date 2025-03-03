package gm.estudiantes;

import gm.estudiantes.modelo.Estudiante;
import gm.estudiantes.servicio.EstudianteServicio;
import org.hibernate.query.sqm.spi.DelegatingSqmSelectionQueryImplementor;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;


@SpringBootApplication
//ComandLineRunner para ejecutar por consola
public class EstudiantesApplication implements CommandLineRunner {

	@Autowired
	private EstudianteServicio estudianteServicio;

	private static final Logger logger = LoggerFactory.getLogger(EstudiantesApplication.class);

	// Definir una variable de salto de linea que se compatible con todos los sistemas operativos

	String nl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("INICIANDO APLICACION...");
		//Levanta la fabrica de Spring
		SpringApplication.run(EstudiantesApplication.class, args);
		logger.info("Aplicacion finalizada ");

	}


	@Override
	public void run(String... args) throws Exception {
		logger.info("Ejecuntando metodo run de Spring" + nl);
		Scanner entrada = new Scanner(System.in);
		var salir = false;



		while (!salir) {
			try {
				mostrarMenu();
				salir = ejecutarOpciones(entrada);
				logger.info(nl);

			} catch (Exception e) {
				System.out.println("Ocurrio un error: " + e.getMessage());
			}

		}//fin del while
	}
	public void mostrarMenu(){
		logger.info(nl);
		logger.info(""" 
		*** SISTEMAS DE ESTUDIANTES ***;
		1. Listar Estudiantes
		2. Buscar Estudiantes
		3. Agregar Estudiante
		4. Modificar Estudiante
		5. Eliminar Estudiante;
		6. Salir ;
		Elige una opcion: """);
	}
	private boolean ejecutarOpciones (Scanner entrada){

		var opcion = Integer.parseInt(entrada.nextLine());
		var salir = false;

		switch (opcion){
			case 1 -> {
				logger.info(nl + "Los estudiantes que se encuentran en la base de datos son los siguiente: " + nl);
				List<Estudiante> estudiantes = estudianteServicio.listarEstudiantes();
				estudiantes.forEach((estudiante -> logger.info(estudiante.toString()+ nl)));


			}
			case 2 -> {

				logger.info("Introduzca el id del estudiante a buscar: ");
				var idEstudiante = Integer.parseInt(entrada.nextLine());
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if(estudiante != null)
					logger.info("Estudiante encontrado: " + estudiante + nl);
				else
					logger.info("Estudiante no encontrado con Id: " + idEstudiante + nl);

			}
			case 3 -> {
				logger.info("Añadir estudiante: " + nl);
				logger.info("Nombre: ");
				var nombre = entrada.nextLine();
				logger.info("Apellido: ");
				var apellido = entrada.nextLine();
				logger.info("Telefono: ");
				var telefono = entrada.nextLine();
				logger.info("Email: ");
				var email = entrada.nextLine();

				var nuevoEstudiante = new Estudiante();
				nuevoEstudiante.setNombre(nombre);
				nuevoEstudiante.setApellido(apellido);
				nuevoEstudiante.setTelefono(telefono);
				nuevoEstudiante.setEmail(email);
				estudianteServicio.guardarEstudiante(nuevoEstudiante);
				logger.info("Estudiante añadido: " + nuevoEstudiante + nl);
			}
			case 4 -> {
				logger.info("Modificar Estudiante" + nl);
				logger.info("Introduzca el Id del estudiante que desea modificar: ");
				var idEstudiante = Integer.parseInt(entrada.nextLine());
				//Buscamos el Id del estudiante
				Estudiante buscarEstudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if(buscarEstudiante != null) {
					logger.info("Nombre: ");
					var nombre = entrada.nextLine();
					logger.info("Apellido: ");
					var apellido = entrada.nextLine();
					logger.info("Telefono: ");
					var telefono = entrada.nextLine();
					logger.info("Email: ");
					var email = entrada.nextLine();
					buscarEstudiante.setNombre(nombre);
					buscarEstudiante.setApellido(apellido);
					buscarEstudiante.setTelefono(telefono);
					buscarEstudiante.setEmail(email);
					estudianteServicio.guardarEstudiante(buscarEstudiante);
					logger.info("Estudiante modificado: " + buscarEstudiante);
				}else
					logger.info("Estudiante no encontrado con Id: " + idEstudiante);
			}
			case 5 -> {

				logger.info("Eliminar Estudiante:" + nl);
				logger.info("Introduzca el Id del estudiante a Eliminar");
				var idEstudiante = Integer.parseInt(entrada.nextLine());
				//Buscamos el estudiante a elminar
				var eliminarEstudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);

				if (eliminarEstudiante != null){
					estudianteServicio.eliminarEstudiante(eliminarEstudiante);
					logger.info("Estudiante eliminado: " + eliminarEstudiante + nl);
				}
				else
					logger.info("Estudiante No encontrado con Id: " + idEstudiante);


			}
			case 6 -> {
				logger.info("Hasta Pronto!");
				salir= true;

			}
			default -> logger.info("Opcion no reconocida");

		}
		return salir;


	}
}

