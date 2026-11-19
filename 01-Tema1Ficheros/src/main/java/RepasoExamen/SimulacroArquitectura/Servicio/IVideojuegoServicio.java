package SimulacroArquitectura.Servicio;

import java.util.List;

import SimulacroArquitectura.Exception.VideojuegoException;
import SimulacroArquitectura.Modelo.Videojuego;

public interface IVideojuegoServicio {
	
	List<Videojuego> obtenerCatalogo(String rutaJson);
	List<Videojuego> filtrarDisponibles(List<Videojuego> lista);
	void aplicarDescuento(List<Videojuego> lista, double porcentaje);
	List<Videojuego> ordenarPorPrecio(List<Videojuego> lista);
//	void exportarCatalogo(String rutaJson, String rutaCsv, double descuento);

}
