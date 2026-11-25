package UNIDAD_1.Boletin2_XML.Ej1.Repository;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import UNIDAD_1.Boletin2_XML.Ej1.Exceptions.EmpleadoException;
import UNIDAD_1.Boletin2_XML.Ej1.Models.Empleado;
import UNIDAD_1.Boletin2_XML.Ej1.Models.Puesto;

public class UtilesXMLDom {
	private static final String RUTA_RESOURCES = "src/main/resources/";
	private static final Logger logger = LogManager.getLogger(UtilesXMLDom.class);
	private File archivo;

	public UtilesXMLDom(String ruta) {
		super();
		this.archivo = new File(RUTA_RESOURCES, ruta);
	}

	/*
	 * TODO ESTO ES PARA 
	 * LEER UN FICHERO XML
	 * */
	
	private Document getDocumentFromXML() throws EmpleadoException{
		// 1. Crear el analizador DOM
		Document doc = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
			builder = factory.newDocumentBuilder();
			try {
				doc = builder.parse(archivo);
			} catch (SAXException e) {
				throw new EmpleadoException("Se ha lanzado SAXException");
			} catch (IOException e) {
				throw new EmpleadoException("Se ha lanzado IOException");
			}
		} catch (ParserConfigurationException e) {
			throw new EmpleadoException("Se ha lanzado ParserConfigurationException");
		}
        return doc;
	}
	
	private Puesto fromElement2Puesto(Element raiz) throws EmpleadoException {
		// Atributos
		String id = raiz.getAttribute("id"); // Opcional
		String departamento = raiz.getAttribute("departamento"); // Opcional
		// Elementos
		Element nombreElemento = (Element) raiz.getElementsByTagName("nombrePuesto").item(0);
		if (nombreElemento == null) {
			throw new EmpleadoException("NombrePuesto nulo");
		}
		String nombre = nombreElemento.getTextContent();
		Element salarioElemento = (Element) raiz.getElementsByTagName("salario").item(0);
		if (salarioElemento == null) {
			throw new EmpleadoException("Salario nulo");
		}
		Double salario = Double.parseDouble(salarioElemento.getTextContent());
		Puesto puesto;
		puesto = new Puesto(nombre, salario);
		if (id != null) {
			puesto.setId(id);
		}
		if (departamento != null) {
			puesto.setDepartamento(departamento);
		}
		return puesto;
	}
	
	private Empleado fromElement2Empleado(Element raiz) throws EmpleadoException {
		Empleado empleado = null;
		// Atributos
		String id = raiz.getAttribute("id"); // Opcional
		// Elementos
		Element nombreApElemento = (Element) raiz.getElementsByTagName("nombreApellidos").item(0);
		try {
			if (nombreApElemento == null) {
				throw new EmpleadoException("Nombre nulo");
			}
			String nombre = nombreApElemento.getTextContent();
			Element edadElemento = (Element) raiz.getElementsByTagName("edad").item(0); // Opcional
			Integer edad = null;
			Element empresaElemento = (Element) raiz.getElementsByTagName("empresa").item(0);
			if (empresaElemento == null) {
				throw new EmpleadoException("Empresa nulo");
			}
			String empresa = empresaElemento.getTextContent();
			Element puesto = (Element) raiz.getElementsByTagName("puesto").item(0);
			if (puesto == null) {
				throw new EmpleadoException("Puesto es nulo");
			}
			Puesto p = fromElement2Puesto(puesto);
			if (edadElemento != null) {
				edad = Integer.parseInt(edadElemento.getTextContent());
			}
			empleado = new Empleado(nombre, empresa, p);
			if (id != null) {
				empleado.setId(id);
			}
			if (edad != null) {
				empleado.setEdad(edad);
			}
		} catch (EmpleadoException e) {
			logger.error(e.getMessage());
		}
		return empleado;
	}
	
	private Set<Empleado> leerEmpleadosDesdeXML(Element raiz) throws EmpleadoException {
		NodeList listaNodos = raiz.getElementsByTagName("empleado");
		Set<Empleado> empleados = new HashSet<Empleado>();
		for (int i = 0; i < listaNodos.getLength(); i++) {
			Element elementoEmpleado = (Element) listaNodos.item(i);
			Empleado empleado = fromElement2Empleado(elementoEmpleado);
			if (empleado != null) {
				empleados.add(empleado);
			}
		}
		return empleados;
	}
	
	public Set<Empleado> lecturaXML() throws EmpleadoException {
		Document doc = getDocumentFromXML();
		Element raiz = doc.getDocumentElement();
		Set<Empleado> empleados = leerEmpleadosDesdeXML(raiz);
		return empleados;
	}
	
	/*
	 * TODO ESTO ES PARA 
	 * ESCRIBIR UN FICHERO XML
	 * */
	
	private Document construyoObjetoDocumento(String nombreRaiz) throws ParserConfigurationException {
		Document documento = null;
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factoria.newDocumentBuilder();
		DOMImplementation implementacion = builder.getDOMImplementation();
		documento = implementacion.createDocument(null, nombreRaiz, null);
		// Primer parámetro uri: si null no está validado por ninguna ruta
		// segundo parámetro: nombre fichero
		// tercer parámetro: document type Por defecto null  Permite asociar DTD
		return documento;
	}
	
	private Element creaElemento(String nombreElemento, String valorElemento, Element padre, Document documento) {
		Element elemento = documento.createElement(nombreElemento);
		Text texto = documento.createTextNode(valorElemento);
		padre.appendChild(elemento); // Se lo asigno a su padre como Hijo
		elemento.appendChild(texto); // Cargo el elemento con el valor
		return elemento;
	}
	
	private void agregaEmpleadoADocumento(Document documento, Element padre, Empleado e) {
		// Atributos
		if (e.getId() != null) {
			padre.setAttribute("id", e.getId());
		}
		// Elementos
		Element nombreAp = this.creaElemento("nombreApellidos", e.getNombreAp(), padre, documento);
		if (Integer.toString(e.getEdad()) != null) {
			Element edad = this.creaElemento("edad", String.valueOf(e.getEdad()), padre, documento);
		}
		Element empresa = this.creaElemento("empresa", e.getEmpresa(), padre, documento);
		Puesto p = e.getPuesto();
		Element puesto = this.creaElemento("puesto", null, padre, documento);
		Element nombrePuesto = this.creaElemento("nombrePuesto", p.getNombre(), puesto, documento);
		String sal = String.valueOf(p.getSalario());
		Element salario = this.creaElemento("salario", sal, puesto, documento);
		if (p.getId() != null) {
			puesto.setAttribute("id", p.getId());
		}
		if (p.getDepartamento() != null) {
			puesto.setAttribute("departamento", p.getDepartamento());
		}
	}
	
	private void escribeDocumentoEnFichero(Document documento, String nombreFichero) throws TransformerException{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(documento);
		StreamResult resultado = new StreamResult(new File(RUTA_RESOURCES + nombreFichero));
		transformer.transform(source, resultado);
	}
	
	public void escribeEmpleadoEnXML(String nombreFichero, Empleado e) throws EmpleadoException{
		try {
			Document documento = this.construyoObjetoDocumento("empleado");
			Element raiz = documento.getDocumentElement();
			agregaEmpleadoADocumento(documento, raiz, e);
			escribeDocumentoEnFichero(documento, nombreFichero);
		} catch (ParserConfigurationException e1) {
			throw new EmpleadoException("Error al escribir xml");
		} catch (TransformerException e1) {
			throw new EmpleadoException("Error muy serio");
		}
	}
	
	public void escribeEmpleadosEnXML(String nombreFichero, Set<Empleado> empleados) throws EmpleadoException{
		try {
			Document documento = this.construyoObjetoDocumento("empleados");
			for (Empleado e: empleados) {
				Element elemento = this.creaElemento("empleado", null, documento.getDocumentElement(), documento);
				agregaEmpleadoADocumento(documento, elemento, e);
			}
			escribeDocumentoEnFichero(documento, nombreFichero);
		} catch (ParserConfigurationException e) {
			throw new EmpleadoException("Error serio");
		} catch (TransformerException e1) {
			throw new EmpleadoException("Error muy serio");
		}
	}
}
