package prepexamConjunta.prepexam.repositorio;

import java.util.List;

import prepexamConjunta.prepexam.Modelo.Empleado;



public interface IRepositorioEmpleado {
	public List<Empleado> findall() throws RepositorioException;
	public Empleado findbyEdad(int edad); //esto deberia ser id pero soy anormal la logica es la misma, manue xupala
	public void delete(int edad);
	public boolean existbyEdad(int edad);
	public void save(Empleado emp) throws RepositorioException;
	public void escribirJSON(String RutaJson) throws RepositorioException;
	
}
