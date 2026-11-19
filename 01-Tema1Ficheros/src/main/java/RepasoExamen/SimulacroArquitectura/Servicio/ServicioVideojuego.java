package SimulacroArquitectura.Servicio;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import SimulacroArquitectura.Exception.VideojuegoException;
import SimulacroArquitectura.Modelo.Videojuego;
import SimulacroArquitectura.Repositorio.IRepositorioVideojuegos;
import SimulacroArquitectura.Repositorio.VideojuegoRepositorio;
import SimulacroArquitectura.Modelo.comparador;

public class ServicioVideojuego implements IVideojuegoServicio{
	
	private static final Logger logger = LogManager.getLogger(ServicioVideojuego.class);
	
	private List<Videojuego> videojuegos;
	IRepositorioVideojuegos repoVideojuego;
	
	

	public ServicioVideojuego(String ruta) {
		super();
		
		this.repoVideojuego = new VideojuegoRepositorio(ruta);
	}

	@Override
	public List<Videojuego> obtenerCatalogo(String rutaJson){
		
		try {
			videojuegos = repoVideojuego.findAll();
			
		} catch (VideojuegoException e) {
			logger.error(e.getMessage());
		}
		
		return videojuegos;
	}

	@Override
	public List<Videojuego> filtrarDisponibles(List<Videojuego> lista) {
		
		List<Videojuego> videojuegosDisponibles = new ArrayList<Videojuego>();
		
		for (Videojuego videojuego : lista) {
			
			if(videojuego.isDisponible() == true) {
				
				videojuegosDisponibles.add(videojuego);
			}
		}
		
		return videojuegosDisponibles;
	}

	@Override
	public void aplicarDescuento(List<Videojuego> lista, double porcentaje) {
		
		List<Videojuego> videojuegosNueva = new ArrayList<Videojuego>();
		
		double descuento = 0;
		
		for (Videojuego videojuego : lista) {
			
			if(videojuego.isEnOferta()) {
				
				descuento = (videojuego.getPrecio() * porcentaje) / 100;
				
				videojuego.setPrecio(videojuego.getPrecio() - descuento);
				
				videojuegosNueva.add(videojuego);
				
			}
		}
		
		try {
			repoVideojuego.escribirCsv("src/main/resources/descuentos" + porcentaje + ".csv", videojuegosNueva);
			
		} catch (VideojuegoException e) {
			logger.error(e.getMessage());
		}
				
	}

	@Override
	public List<Videojuego> ordenarPorPrecio(List<Videojuego> lista) {
		
		List<Videojuego> ordenada = videojuegos;
		
		ordenada.sort(new comparador());
	
		return ordenada;
	}


}
