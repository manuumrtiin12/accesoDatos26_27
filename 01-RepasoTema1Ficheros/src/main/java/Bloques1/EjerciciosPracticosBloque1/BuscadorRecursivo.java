package Bloques1.EjerciciosPracticosBloque1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BuscadorRecursivo {

	List<File> buscarPorFiltro(File directorioRaiz, String patron, long tamanoMinimoBytes) {

		File[] contenidoDirectorio = directorioRaiz.listFiles();
		String patronMayus = patron.toUpperCase();
		List<File> archivos = new ArrayList<File>();

		if (contenidoDirectorio != null) {

			for (File archivo : contenidoDirectorio) {

				if (archivo.isDirectory()) {

					archivos.addAll(buscarPorFiltro(archivo, patron, tamanoMinimoBytes));

				} else if (archivo.getName().toUpperCase().contains(patronMayus)
						&& archivo.length() >= tamanoMinimoBytes) {

					archivos.add(archivo);
				}
			}
		}

		return archivos;
	}
}