package RepasoExamen.prepexamConjunta.prepexam.Service;

import java.util.List;

import prepexamConjunta.prepexam.*;
import prepexamConjunta.prepexam.Modelo.Empleado;
import prepexamConjunta.prepexam.repositorio.IRepositorioEmpleado;
import prepexamConjunta.prepexam.repositorio.RepositorioEmpleado;
import prepexamConjunta.prepexam.repositorio.RepositorioException;


public class ServicioEmpleado implements IServicioEmpleado{
	private IServicioEmpleado repo;
	private List<Empleado> iempleados; 

	public ServicioEmpleado(String Ruta) {
		super();
		repo = new RepositorioEmpleado(Ruta);
	}		

	@Override
	public List<Empleado> findall() {
		try {
			iempleados = repo.findall();
		} catch (RepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return iempleados;
	}

	@Override
	public Empleado findbyEdad(int edad) {
		Empleado emp = repo.findbyEdad(edad);
		return emp;
	}

	@Override
	public void delete(int edad) { // esto se puede hacer con bolean para que devuelva true o false (tendrias que cambiarlo en el IServicioEmpleado para que sea boolean)
		repo.delete(edad);
	}

	@Override
	public boolean existbyEdad(int edad) {
		return repo.existbyEdad(edad);
	}

	@Override
	public void save(Empleado emp) {
		try {
			repo.save(emp);
		} catch (RepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void escribirJSON(String RutaJson) {
		try {
			repo.escribirJSON(RutaJson);
		} catch (RepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
