package SimulacroArquitectura.Controlador;

import java.util.List;

import SimulacroArquitectura.Exception.VideojuegoException;
import SimulacroArquitectura.Modelo.Videojuego;
import SimulacroArquitectura.Repositorio.VideojuegoRepositorio;
import SimulacroArquitectura.Servicio.IVideojuegoServicio;
import SimulacroArquitectura.Servicio.ServicioVideojuego;

public class ControladorVideojuego {

	public static void main(String[] args) {
		
		IVideojuegoServicio ivj = new ServicioVideojuego("src/main/resources/videojuegos.json");
		
		List<Videojuego> todos = ivj.obtenerCatalogo("src/main/resources/videojuegos.json");
		
		System.out.println(ivj.ordenarPorPrecio(todos));
		
		ivj.aplicarDescuento(todos, 20);
		
	}

}
