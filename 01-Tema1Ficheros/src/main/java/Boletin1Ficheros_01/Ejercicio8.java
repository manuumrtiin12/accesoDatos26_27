package Boletin1Ficheros_01;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ejercicio8 implements Comparator<File> {

    private static final Logger logger = LogManager.getLogger(Ejercicio8.class);
    
   
    @Override
	public int compare(File o1, File o2) {
		return (int) (o1.length() - o2.length());
	}
    
    File directorio = new File("C:\\Users\\mmarf\\Desktop\\PruebaFicheros");
    
    File[] archivos = directorio.listFiles();
    
    
    public static void main(String[] args) {

    	Arrays.sort(archivos, new Ejercicio8());
    	
    






	
}