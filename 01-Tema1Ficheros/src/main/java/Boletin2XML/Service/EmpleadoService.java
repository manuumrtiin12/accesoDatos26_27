package UNIDAD_1.Boletin2_XML.Ej1.Service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import UNIDAD_1.Boletin2_XML.Ej1.Exceptions.EmpleadoException;
import UNIDAD_1.Boletin2_XML.Ej1.Models.ComparatorEmpleados;
import UNIDAD_1.Boletin2_XML.Ej1.Models.Empleado;
import UNIDAD_1.Boletin2_XML.Ej1.Repository.EmpleadoRepository;
import UNIDAD_1.Boletin2_XML.Ej1.Repository.IEmpleadoRepository;

public class EmpleadoService implements IEmpleadoService {
	
	private static final Logger logger = LogManager.getLogger(EmpleadoService.class);
	
	private Set<Empleado> empleados;
	IEmpleadoRepository repositorio;
	
	public EmpleadoService(String ruta) {
		super();
		this.repositorio = new EmpleadoRepository(ruta);
	}

	@Override
	public Set<Empleado> findAll() {
		try {
			this.empleados = repositorio.findAll();
		} catch (EmpleadoException e) {
			logger.error(e.getMessage());
		}
		return this.empleados;
	}

	@Override
	public void save(String nombreFichero, Set<Empleado> empleados) {
		try {
			repositorio.save(nombreFichero, empleados);
		} catch (EmpleadoException e) {
			logger.error(e.getMessage());
		}
	}
	
	@Override
	public Set<Empleado> ordenarPorSalario(Set<Empleado> empleados) {
		Set<Empleado> empleadosOrdenado = new TreeSet<>(new ComparatorEmpleados());
		empleadosOrdenado.addAll(empleados);
		return empleadosOrdenado;
	}
	
}
