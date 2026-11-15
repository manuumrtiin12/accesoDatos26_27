package SimulacroArquitectura.Repositorio;

import java.util.List;

import SimulacroArquitectura.Exception.VideojuegoException;
import SimulacroArquitectura.Modelo.Videojuego;

public interface IRepositorioVideojuegos {

	public List<Videojuego> findAll() throws VideojuegoException;
	
	public Videojuego findById(int id) throws VideojuegoException;
	
	public boolean existById(int id);
	
	public void delete(int id) throws VideojuegoException;
	
	public void save(Videojuego videojuegoAniadir) throws VideojuegoException;
	
	public void escribirCsv(String ruta, List<Videojuego> videojuegos) throws VideojuegoException;
	
}
