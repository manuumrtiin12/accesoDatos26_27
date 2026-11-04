package EjemploFicheros_00.EjemploArquitectura;

import java.io.FileNotFoundException;
import java.util.List;

public interface IRepositorioPersona {
	
   List<Persona> findAll() throws RepositorioException, FileNotFoundException;
   
   
   
<<<<<<< HEAD
   //Puede devolver null si no hay una persona con eses nombre
   
   boolean existsById(String nombre) throws RepositorioException;
   
   int count() throws RepositorioException;
   
   //void save(Persona persona) throws RepositorioException      
   
   // alta o modificación Escribe fichero
   
   boolean deleteById(String nombre) throws RepositorioException;
   
   // Escribe en fichero
=======
//   //Hace lectura de fichero si lista personas es nula
  
     Persona findById(String nombre) throws RepositorioException, FileNotFoundException;
//   
//   //Puede devolver null si no hay una persona con eses nombre
//   
//   boolean existsById(String nombre) throws RepositorioException;
//   
//   int count() throws RepositorioException;
//   
//   void save(Persona persona) throws RepositorioException;      
//   
//   // alta o modificación Escribe fichero
//   
//   boolean deleteById(String nombre) throws RepositorioException;
//   
//   // Escribe en fichero
>>>>>>> d050df8a59802266bfc570adc3ac5dabc3e86ddd
}
