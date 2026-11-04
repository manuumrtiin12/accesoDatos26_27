package EjemploFicheros_00.EjemploGson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

public class Empleado {
	
	private static final Logger logger = LogManager.getLogger(Empleado.class);


    private String identificador;
    private String nombreApellido;
    private int edad;
    private String empresa;
    
    

    @Override
	public String toString() {
		return "Empleado [identificador=" + identificador + ", nombreApellido=" + nombreApellido + ", edad=" + edad
				+ ", empresa=" + empresa + "]" ;
	}
    
    

    
    public Empleado(String identificador, String nombreApellido, int edad, String empresa) {
		super();
		this.identificador = identificador;
		this.nombreApellido = nombreApellido;
		this.edad = edad;
		this.empresa = empresa;
	}




	void leerJson() throws IOException {
    	
    	Gson gson = new Gson();

        FileReader reader = new FileReader(
            "C:\\Users\\mmarf\\Desktop\\DAM2\\acceso_datos\\accesoDatos26_27\\01-Tema1Ficheros\\src\\main\\resources\\Empleados.json"
        );

        Empleado[] empleadosArray  = gson.fromJson(reader, Empleado[].class);
        List<Empleado> empleados = Arrays.asList(empleadosArray);

        reader.close();

        for(int i = 0 ; i < empleadosArray.length ; i++) {
        	
        	logger.debug(empleadosArray[i]);
        }
    } 
    	
    void escribeEmpleado(Empleado empleado, String ruta)
    {// Convertir el objeto a JSON
    	 Gson gson = new Gson();
    	 String json = gson.toJson(empleado);
    	 FileWriter fichero = null;
    	 try {
    		fichero = new FileWriter(ruta, true);
    		fichero.write(json);
    	} catch (IOException e) {
    		e.printStackTrace();
    	} finally {
    		if (fichero != null) {
    			try {
    				fichero.close();
    			} catch (IOException e) {
    				System.out.println("Error al escribir empleado");
    			}			}		}	   }


    
    
	public static void main(String[] args) throws IOException {
		
		Empleado empleado = new Empleado(null, null, 0, null);
		Empleado empleado1 = new Empleado("empleado0", "Pepito Vargas", 54, "Su empresa");
		
		empleado.leerJson();
		empleado.escribeEmpleado(empleado1, "C:\\Users\\mmarf\\Desktop\\DAM2\\acceso_datos\\accesoDatos26_27\\01-Tema1Ficheros\\src\\main\\resources\\Empleados.json");
		
	}
}

        


    