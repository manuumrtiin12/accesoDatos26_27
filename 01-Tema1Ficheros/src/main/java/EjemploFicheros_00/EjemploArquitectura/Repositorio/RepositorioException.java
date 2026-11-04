package EjemploFicheros_00.EjemploArquitectura.Repositorio;

public class RepositorioException extends Exception {


	public RepositorioException(String message) {
		super(message);
	}

	public RepositorioException(String message, Throwable cause) {
		super(message, cause);
	}
}