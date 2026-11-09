package Bloques2ArquitecturaCompleta.EjerciciosArquitectura.Ejemplos;

import java.util.List;

public interface IRepositorioEstudiantes {

	List<Estudiante> findAll() throws RepositorioException;

	Estudiante findById(String nombre) throws RepositorioException;

	boolean existsById(String nombre) throws RepositorioException;

	void save(Estudiante estudiante) throws RepositorioException;

	boolean deleteById(String nombre) throws RepositorioException;
}
