package Bloques2ArquitecturaCompleta.EjemploGuiado;

public class Main {

	public static void main(String[] args) {
		// 1. Instanciar el Repositorio (JSON)
		IRepositorioLibros repo = new RepositorioLibrosJson("libros.json");

		// 2. Instanciar el Servicio con el Repositorio
		ServicioLibros servicio = new ServicioLibros(repo);

		System.out.println("--- 1. Dar de alta libros ---");
		servicio.registrarLibro("9788497593793", "El nombre del viento", "Plaza & Janés", 1);
		servicio.registrarLibro("9788445002070", "El imperio final", "Nova", 2);

		System.out.println("\n--- 2. Listar libros ---");
		for (Libro l : servicio.listarTodos()) {
			System.out.println(l);
		}

		System.out.println("\n--- 3. Exportar a CSV ---");
		boolean exito = servicio.exportarLibrosACSV("libros_exportados.csv");
		System.out.println("¿Exportación a CSV exitosa?: " + exito);

		System.out.println("\n--- 4. Borrar un libro ---");
		servicio.eliminarLibro("9788497593793");

		System.out.println("\n--- 5. Lista final ---");
		for (Libro l : servicio.listarTodos()) {
			System.out.println(l);
		}
	}
}
