import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;


class Conductor {

    String usuario;
    String contrasena;
    String ubicacion;

    public Conductor(String usuario, String contrasena, String ubicacion){

        this.usuario = usuario;
        this.contrasena = contrasena;
        this.ubicacion = ubicacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

}

class Pasajero{

    String usuario;
    String contrasena;
    String ubicacion;

    public Pasajero(String usuario, String contrasena, String ubicacion){

        this.usuario = usuario;
        this.contrasena = contrasena;
        this.ubicacion = ubicacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}

public class Registro {

    public void registrarConductor(Conductor conductor) throws TransformerException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        {
            try {
                builder = factory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            }
        }


        Document doc = builder.parse(new File("conductores.xml"));

        doc.setXmlVersion("1.0");


        Element clientes = doc.createElement("Clientes");
        Element conductores = doc.createElement("Conductores");

        Element usuario = doc.createElement("Usuario");
        Text textUsuario = doc.createTextNode(conductor.getUsuario());
        usuario.appendChild(textUsuario);
        conductores.appendChild(usuario);

        Element contrasena = doc.createElement("Contrasena");
        Text textContrasena = doc.createTextNode(conductor.getContrasena());
        contrasena.appendChild(textContrasena);
        conductores.appendChild(contrasena);

        Element ubicacion = doc.createElement("Ubicacion");
        Text textUbicacion = doc.createTextNode(conductor.getUbicacion());
        ubicacion.appendChild(textUbicacion);
        conductores.appendChild(ubicacion);

        clientes.appendChild(conductores);

        doc.getDocumentElement().appendChild(clientes);

        Source source = new DOMSource(doc);
        Result result = new StreamResult(new File("conductores.xml"));

        TransformerFactory transfactory = TransformerFactory.newInstance();


        Transformer transformer = transfactory.newTransformer();
        transformer.transform(source, result);

        doc.getDocumentElement().normalize();


    }
/**
    public void eliminar() throws ParserConfigurationException, IOException, SAXException, TransformerException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(new File("conductores.xml"));

        doc.setXmlVersion("1.0");

        Element element = (Element)doc.getElementsByTagName("Clientes").item(0);
        element.getParentNode().removeChild(element);

        Source source = new DOMSource(doc);
        Result result = new StreamResult(new File("conductores.xml"));

        TransformerFactory transfactory = TransformerFactory.newInstance();


        Transformer transformer = transfactory.newTransformer();
        transformer.transform(source, result);

        doc.getDocumentElement().normalize();

    }
*/
    public int buscarConductor(Conductor conductor) throws ParserConfigurationException, IOException, SAXException {

        String usuario = conductor.getUsuario();
        String contrasena = conductor.getContrasena();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(new File("conductores.xml"));

        NodeList nodes = doc.getElementsByTagName("Conductores");

        for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(i);
            Node node = element.getFirstChild();
            String atributo1 = node.getTextContent();
            if (usuario.equals(atributo1)){
                String atributo2 = node.getNextSibling().getTextContent();
                if (contrasena.equals(atributo2)){
                    return 1;
                }
            }


        }
        return -1;
    }
    public void registrarPasajero(Pasajero pasajero) throws TransformerException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        {
            try {
                builder = factory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            }
        }

        Document doc = builder.parse(new File("pasajeros.xml"));

        doc.setXmlVersion("1.0");


        Element clientes = doc.createElement("Clientes");
        Element pasajeros = doc.createElement("Pasajeros");

        Element usuario = doc.createElement("Usuario");
        Text textUsuario = doc.createTextNode(pasajero.getUsuario());
        usuario.appendChild(textUsuario);
        pasajeros.appendChild(usuario);

        Element contrasena = doc.createElement("Contrasena");
        Text textContrasena = doc.createTextNode(pasajero.getContrasena());
        contrasena.appendChild(textContrasena);
        pasajeros.appendChild(contrasena);

        Element ubicacion = doc.createElement("Ubicacion");
        Text textUbicacion = doc.createTextNode(pasajero.getUbicacion());
        ubicacion.appendChild(textUbicacion);
        pasajeros.appendChild(ubicacion);

        clientes.appendChild(pasajeros);

        doc.getDocumentElement().appendChild(clientes);

        Source source = new DOMSource(doc);
        Result result = new StreamResult(new File("pasajeros.xml"));

        TransformerFactory transfactory = TransformerFactory.newInstance();


        Transformer transformer = transfactory.newTransformer();
        transformer.transform(source, result);

        doc.getDocumentElement().normalize();


    }

    public int buscarPasajero(Pasajero pasajero) throws ParserConfigurationException, IOException, SAXException {

        String usuario = pasajero.getUsuario();
        String contrasena = pasajero.getContrasena();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(new File("pasajeros.xml"));

        NodeList nodes = doc.getElementsByTagName("Pasajeros");

        for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(i);
            Node node = element.getFirstChild();
            String atributo1 = node.getTextContent();
            if (usuario.equals(atributo1)){
                String atributo2 = node.getNextSibling().getTextContent();
                if (contrasena.equals(atributo2)){
                    return 1;
                }
            }


        }
        return -1;
    }

    public void eliminarConductor(String usuario) throws ParserConfigurationException, IOException, SAXException, TransformerException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(new File("conductores.xml"));

        NodeList nodes = doc.getElementsByTagName("Conductores");

        for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(i);
            Node node = element.getFirstChild();
            String atributo = node.getTextContent();
            if (usuario.equals(atributo)) {
                Element ele = (Element)doc.getElementsByTagName("Clientes").item(i);
                ele.getParentNode().removeChild(ele);
                Source source = new DOMSource(doc);
                Result result = new StreamResult(new File("conductores.xml"));

                TransformerFactory transfactory = TransformerFactory.newInstance();


                Transformer transformer = transfactory.newTransformer();
                transformer.transform(source, result);

                doc.getDocumentElement().normalize();
                System.out.println(1);
                break;
            }
        }
    }
    public void eliminarPasajero(String usuario) throws ParserConfigurationException, IOException, SAXException, TransformerException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(new File("pasajeros.xml"));

        NodeList nodes = doc.getElementsByTagName("Pasajeros");

        for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(i);
            Node node = element.getFirstChild();
            String atributo = node.getTextContent();
            if (usuario.equals(atributo)) {
                Element ele = (Element)doc.getElementsByTagName("Clientes").item(i);
                ele.getParentNode().removeChild(ele);
                Source source = new DOMSource(doc);
                Result result = new StreamResult(new File("pasajeros.xml"));

                TransformerFactory transfactory = TransformerFactory.newInstance();


                Transformer transformer = transfactory.newTransformer();
                transformer.transform(source, result);

                doc.getDocumentElement().normalize();
                System.out.println(1);
                break;
            }
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException, SAXException {
        Registro registro = new Registro();
        Pasajero pasajero1 = new Pasajero("Josue", "147", "Fundacion");
        registro.registrarPasajero(pasajero1);
    }
}