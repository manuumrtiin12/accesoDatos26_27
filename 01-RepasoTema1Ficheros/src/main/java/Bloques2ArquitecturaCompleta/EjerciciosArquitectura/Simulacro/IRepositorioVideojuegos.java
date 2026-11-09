package Bloques2ArquitecturaCompleta.EjerciciosArquitectura.Simulacro;

import java.util.List;

public interface IRepositorioVideojuegos {

	List<Videojuego> findAll() throws RepositorioException;

	Videojuego findById(String id) throws RepositorioException;

	boolean existsById(String id) throws RepositorioException;

	void save(Videojuego videojuego) throws RepositorioException;

	boolean deleteById(String id) throws RepositorioException;
}
