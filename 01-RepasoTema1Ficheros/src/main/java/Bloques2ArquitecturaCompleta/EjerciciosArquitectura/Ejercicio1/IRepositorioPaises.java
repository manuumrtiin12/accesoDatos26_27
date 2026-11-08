package Bloques2ArquitecturaCompleta.EjerciciosArquitectura.Ejercicio1;

import java.util.List;

public interface IRepositorioPaises {
    List<Pais> findAll() throws RepositorioException;
    Pais findById(String nombre) throws RepositorioException;
    boolean existsById(String nombre) throws RepositorioException;
    void save(Pais pais) throws RepositorioException;
    boolean deleteById(String nombre) throws RepositorioException;
}