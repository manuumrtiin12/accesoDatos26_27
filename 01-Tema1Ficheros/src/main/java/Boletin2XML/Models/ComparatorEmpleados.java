package UNIDAD_1.Boletin2_XML.Ej1.Models;

import java.util.Comparator;

public class ComparatorEmpleados implements Comparator<Empleado>{
	@Override
	public int compare(Empleado o1, Empleado o2) {
		if (o1.getPuesto().getSalario() > o2.getPuesto().getSalario()) {
			return 1;
		}
		else if (o1.getPuesto().getSalario() < o2.getPuesto().getSalario()) {
			return -1;
		}
		return 0;
	}

}
