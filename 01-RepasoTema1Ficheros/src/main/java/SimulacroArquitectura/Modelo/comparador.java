package SimulacroArquitectura.Modelo;

import java.util.Comparator;

public class comparador implements Comparator<Videojuego> {

	@Override
	public int compare(Videojuego o1, Videojuego o2) {
		
		int devolver = 0;
		
		if(o1.getPrecio() > o2.getPrecio()) {
			
			devolver = 1;
		}
		
		else if (o1.getPrecio() < o2.getPrecio()) {
			
			devolver = -1;
		}
		
		return devolver;
	}
	
	
	
}
