package EjemploFicheros_00.EjemploArquitectura.Repositorio;

import java.util.List;

import EjemploFicheros_00.EjemploArquitectura.Modelo.Persona;

public interface IRepositorioPersona {

   List<Persona> findAll() throws RepositorioException;

   Persona findById(String nombre) throws RepositorioException;

   boolean existsById(String nombre) throws RepositorioException;

   int count() throws RepositorioException;

// void save(Persona persona) throws RepositorioException;
   
// boolean deleteById(String nombre) throws RepositorioException;
   
   List<Persona> getPersonas(); 

}