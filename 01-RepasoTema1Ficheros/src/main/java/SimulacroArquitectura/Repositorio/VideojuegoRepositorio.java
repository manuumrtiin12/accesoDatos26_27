package SimulacroArquitectura.Repositorio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import SimulacroArquitectura.Exception.VideojuegoException;
import SimulacroArquitectura.Modelo.Videojuego;

public class VideojuegoRepositorio implements IRepositorioVideojuegos {

	private File archivo;
	private List<Videojuego> videojuegos;

	@Override
	public List<Videojuego> findAll() throws VideojuegoException {

		cargarSiHaceFalta();

		return videojuegos;
	}

	@Override
	public Videojuego findById(int id) throws VideojuegoException {

		Videojuego vj = null;

		for (Videojuego videojuego : videojuegos) {

			if (videojuego.getId() == id) {

				vj = videojuego;

			}
		}

		if (vj != null) {

			return vj;
		}

		else {
			throw new VideojuegoException("No se a podido encontrar");
		}

	}

	@Override
	public boolean existById(int id) {

		boolean devuelve = true;

		for (Videojuego videojuego : videojuegos) {

			if (videojuego.getId() == id) {

				devuelve = false;

			}
		}

		return devuelve;

	}

	@Override
	public void delete(int id) throws VideojuegoException {

		Videojuego vdojuego = findById(id);

		videojuegos.remove(vdojuego);

	}

	@Override
	public void save(Videojuego videojuegoAniadir) throws VideojuegoException {

		cargarSiHaceFalta();

		List<Videojuego> videojuegosCopy = this.videojuegos;

		if (videojuegosCopy.contains(videojuegoAniadir)) {

			throw new VideojuegoException("El videojuego ya existe en el archivo");
		}

		else {

			videojuegosCopy.add(videojuegoAniadir);

			escribirJson(videojuegosCopy);

			this.videojuegos = videojuegosCopy;

		}

	}

	private List<Videojuego> leerJson() throws VideojuegoException {

		List<Videojuego> videojuegos = new ArrayList<Videojuego>();

		if (!this.archivo.exists()) {

			throw new VideojuegoException("El archivo no existe");
		}

		if (!this.archivo.isFile() || !this.archivo.canRead()) {

			throw new VideojuegoException("No se puede leer el archivo");
		}

		FileReader fr = null;

		try {

			fr = new FileReader(this.archivo);
			Gson gson = new Gson();

			Videojuego[] videojuego = gson.fromJson(fr, Videojuego[].class);

			for (Videojuego v : videojuego) {

				videojuegos.add(v);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return videojuegos;

	}

	public void escribirCsv(String ruta, List<Videojuego> videojuegos) throws VideojuegoException {

		File file = new File(ruta);
		PrintWriter pw = null;
		
		try {
			FileWriter fw = new FileWriter(file);
			pw = new PrintWriter(fw);
			
			pw.println("id;titulo;precio;anioLanzamiento;plataforma;genero;multijugador;disponible;enOferta");
			
			for (Videojuego v : videojuegos) {
				
				pw.println(v.getId() + ";" + v.getTitulo() + ";" + v.getPrecio() + ";" + v.getAnioLanzamiento() + ";" + v.getPlataforma() + ";" + v.getGenero() + ";" + v.isMultijugador() + ";" + v.isDisponible());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new VideojuegoException("No se a podido escribir el csv");
			
		} finally {
			
			if(pw != null) {
				
				pw.close();
			}
			
			else {throw new VideojuegoException("No se a podido cerrar, no se ha escrito na");}
				
		}
	}

	private void escribirJson(List<Videojuego> videojuegos) throws VideojuegoException {

		Gson gs = new GsonBuilder().setPrettyPrinting().create();

		String json = gs.toJson(videojuegos);

		FileWriter fw = null;

		try {
			fw = new FileWriter(this.archivo);
			fw.write(json);

		} catch (IOException e) {
			throw new VideojuegoException("No se a podido escribir");

		} finally {

			if (fw != null) {

				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					throw new VideojuegoException("No se a podido cerrar");
				}
			}

		}

	}

	private void cargarSiHaceFalta() throws VideojuegoException {

		if (videojuegos.isEmpty() || videojuegos == null) {

			try {
				videojuegos = leerJson();

			} catch (VideojuegoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else {

			throw new VideojuegoException("Ya esta caragda la lista");
		}
	}

	public VideojuegoRepositorio(String ruta) {
		super();
		this.archivo = new File(ruta);
		this.videojuegos = new ArrayList<Videojuego>();
	}

}
