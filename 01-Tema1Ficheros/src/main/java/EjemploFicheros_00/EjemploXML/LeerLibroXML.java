package EjemploFicheros_00.EjemploXML;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class LeerLibroXML {

	private static final Logger logger = LogManager.getLogger(LeerLibroXML.class);
	
	public static void main(String[] args) {
		
		UtilesDOMxml util = new UtilesDOMxml();
		
		Document doc = util.getDocumentFromXML("libro.xml");
		
		Element raiz = doc.getDocumentElement();
		Libro libro = util.fromElement2Libro(raiz);
		
		logger.info(libro);
		
		
	}
}
