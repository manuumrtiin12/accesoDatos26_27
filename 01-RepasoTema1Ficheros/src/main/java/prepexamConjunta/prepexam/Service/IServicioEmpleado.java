package prepexamConjunta.prepexam.Service;

import java.util.List;

import prepexamConjunta.prepexam.*;
import prepexamConjunta.prepexam.Modelo.Empleado;


public interface IServicioEmpleado {
	public List<Empleado> findall();
	public Empleado findbyEdad(int edad); //esto deberia ser id pero soy anormal la logica es la misma, manue xupala
	public void delete(int edad);
	public boolean existbyEdad(int edad);
	public void save(Empleado emp);
	public void escribirJSON(String RutaJson);

}
