package UNIDAD_1.Boletin2_XML.Ej1.Service;

import java.util.List;
import java.util.Set;

import UNIDAD_1.Boletin2_XML.Ej1.Models.Empleado;

public interface IEmpleadoService {
	public Set<Empleado> findAll();
	public void save(String nombreFichero, Set<Empleado> empleados);
	public Set<Empleado> ordenarPorSalario(Set<Empleado> empleados);
}
