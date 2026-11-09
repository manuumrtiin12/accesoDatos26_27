package Bloques2ArquitecturaCompleta.EjerciciosArquitectura.Simulacro;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {

		// 1. Repositorio JSON y servicio
		IRepositorioVideojuegos repo = new RepositorioVideojuegosJson("data/videojuegos.json");
		ServicioVideojuegos servicio = new ServicioVideojuegos(repo);

		// 2. Tres videojuegos válidos
		System.out.println("--- 1. Alta de videojuegos ---");
		servicio.registrarVideojuego("VG-001", "The Witcher 3", "RPG", 29.99, Arrays.asList("PC", "PS5", "Switch"));
		servicio.registrarVideojuego("VG-002", "Hollow Knight", "Aventura", 14.50, Arrays.asList("PC", "Switch"));
		servicio.registrarVideojuego("VG-003", "Elden Ring", "RPG", 59.99, Arrays.asList("PC", "PS5"));

		// 3. ID duplicado y datos no válidos
		System.out.println("\n--- 2. Casos erróneos ---");
		boolean duplicado = servicio.registrarVideojuego("VG-001", "Otro juego", "Acción", 10.0, Arrays.asList("PC"));
		System.out.println("¿Registrado el duplicado?: " + duplicado);
		boolean invalido = servicio.registrarVideojuego("VG-004", "", "Acción", -5.0, Arrays.asList("PC"));
		System.out.println("¿Registrado el inválido?: " + invalido);

		// 4. Mostrar todos
		System.out.println("\n--- 3. Todos los videojuegos ---");
		for (Videojuego v : servicio.obtenerTodos()) {
			System.out.println(v);
		}

		// 5. Exportar un género a CSV
		System.out.println("\n--- 4. Exportar RPG a CSV ---");
		boolean exito = servicio.exportarPorGeneroACSV("RPG", "data/videojuegos_rpg.csv");
		System.out.println("¿Exportación exitosa?: " + exito);

		// 6. Eliminar y lista final
		System.out.println("\n--- 5. Eliminar VG-002 ---");
		servicio.eliminarVideojuego("VG-002");

		System.out.println("\n--- 6. Lista final ---");
		for (Videojuego v : servicio.obtenerTodos()) {
			System.out.println(v);
		}
	}
}
