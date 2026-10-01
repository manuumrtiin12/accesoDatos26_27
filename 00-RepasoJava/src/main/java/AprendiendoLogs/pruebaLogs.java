package AprendiendoLogs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class pruebaLogs {
	
	private static final Logger logger = LogManager.getLogger(pruebaLogs.class);
	
	public static void main(String[] args) {
		
		logger.debug("Empieza el Main");
		logger.error("Ocurre Excepcion");
	}

}
