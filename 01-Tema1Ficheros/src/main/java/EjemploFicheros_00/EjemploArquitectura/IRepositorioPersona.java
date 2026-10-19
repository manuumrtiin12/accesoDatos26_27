package EjemploFicheros_00.EjemploArquitectura;

import java.util.List;

public interface IRepositorioPersona {
	
   List<Persona> findAll() throws RepositorioException;
   
   //Hace lectura de fichero si lista personas es nula
   
   Persona findById(String nombre) throws RepositorioException;
   
   //Puede devolver null si no hay una persona con eses nombre
   
   boolean existsById(String nombre) throws RepositorioException;
   
   int count() throws RepositorioException;
   
   void save(Persona persona) throws RepositorioException      
   
   // alta o modificación Escribe fichero
   
   boolean deleteById(String nombre) throws RepositorioException;
   
   // Escribe en fichero
}
