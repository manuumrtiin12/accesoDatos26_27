package UNIDAD_1.Boletin2_XML.Ej1.Repository;

import java.util.List;
import java.util.Set;

import UNIDAD_1.Boletin2_XML.Ej1.Exceptions.EmpleadoException;
import UNIDAD_1.Boletin2_XML.Ej1.Models.Empleado;

public interface IEmpleadoRepository {
	public Set<Empleado> findAll() throws EmpleadoException;
	public void save(String nombreFichero, Set<Empleado> empleados) throws EmpleadoException;
}
