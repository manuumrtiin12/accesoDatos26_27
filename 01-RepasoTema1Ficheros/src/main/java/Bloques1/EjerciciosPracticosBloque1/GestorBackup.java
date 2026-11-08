package Bloques1.EjerciciosPracticosBloque1;

import java.io.File;

public class GestorBackup {

	private File origen;
	private File destino;

	public void ejecutarSincronizacion() throws RutaNoValidaException {

		if (!origen.exists() || !origen.isDirectory()) {
			throw new RutaNoValidaException("La ruta no existe o no es un directorio");
		}

		if (!destino.exists()) {
			destino.mkdir();
		}

		File[] contenidoOrigen = origen.listFiles();

		if (contenidoOrigen != null) {

			for (File archivo : contenidoOrigen) {

				String nombre = archivo.getName();

				if (archivo.isFile() && (nombre.endsWith(".log") || nombre.endsWith(".tmp"))) {

					File archivoDestino = new File(destino, nombre);

					if (archivoDestino.exists()) {

						int punto = nombre.lastIndexOf('.');
						String nombreOld = nombre.substring(0, punto) + "_old" + nombre.substring(punto);

						archivoDestino.renameTo(new File(destino, nombreOld));
					}

					archivo.renameTo(archivoDestino);
				}
			}
		}
	}

	public GestorBackup(File origen, File destino) {
		super();
		this.origen = origen;
		this.destino = destino;
	}

	@Override
	public String toString() {
		return "GestorBackup [origen=" + origen + ", destino=" + destino + "]";
	}

	public File getOrigen() {
		return origen;
	}

	public void setOrigen(File origen) {
		this.origen = origen;
	}

	public File getDestino() {
		return destino;
	}

	public void setDestino(File destino) {
		this.destino = destino;
	}

}