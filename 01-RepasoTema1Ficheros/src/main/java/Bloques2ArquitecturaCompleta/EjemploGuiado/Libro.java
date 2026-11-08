package Bloques2ArquitecturaCompleta.EjemploGuiado;

public class Libro {

	private String isbn;
	private String titulo;
	private String editorial;
	private int numeroEdiciones;

	public Libro(String isbn, String titulo, String editorial, int numeroEdiciones) {
		// Validaciones en constructor -> lanzan IllegalArgumentException si los datos fallan
		if (isbn == null || isbn.trim().isEmpty()) {
			throw new IllegalArgumentException("El ISBN no puede estar vacío.");
		}
		if (titulo == null || titulo.trim().isEmpty()) {
			throw new IllegalArgumentException("El título es obligatorio.");
		}
		if (editorial == null || editorial.trim().isEmpty()) {
			throw new IllegalArgumentException("La editorial es obligatoria.");
		}
		this.isbn = isbn.trim();
		this.titulo = titulo.trim();
		this.editorial = editorial.trim();
		this.numeroEdiciones = numeroEdiciones;
	}

	public String getIsbn() { return isbn; }
	public String getTitulo() { return titulo; }
	public String getEditorial() { return editorial; }
	public int getNumeroEdiciones() { return numeroEdiciones; }

	@Override
	public String toString() {
		return String.format("Libro[ISBN=%s, Título=%s, Editorial=%s, Ediciones=%d]",
				isbn, titulo, editorial, numeroEdiciones);
	}
}
