package Bloques2ArquitecturaCompleta.EjemploGuiado;

import java.util.List;

public interface IRepositorioLibros {

	List<Libro> findAll() throws RepositorioException;

	Libro findById(String isbn) throws RepositorioException;

	boolean existsById(String isbn) throws RepositorioException;

	void save(Libro libro) throws RepositorioException;

	boolean deleteById(String isbn) throws RepositorioException;
}
