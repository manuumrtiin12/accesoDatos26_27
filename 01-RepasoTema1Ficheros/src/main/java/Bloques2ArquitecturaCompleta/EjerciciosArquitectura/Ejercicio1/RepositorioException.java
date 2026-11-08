package Bloques2ArquitecturaCompleta.EjerciciosArquitectura.Ejercicio1;

public class RepositorioException extends Exception {

	public RepositorioException(String mensaje) {
		super(mensaje);
	}

	public RepositorioException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
}
