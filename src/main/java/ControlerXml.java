import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ControlerXml {
    public void generateXml(XmlGuardador objeto,String nombreObjeto,OutputStream out) {

        try {
            // Crear un DocumentBuilder
            DocumentBuilderFactory creador = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = creador.newDocumentBuilder();

            // Creamos el nuevo documento XML
            Document doc = docBuilder.newDocument();
            Element objetoRecibido = doc.createElement(nombreObjeto);
            objeto.guardar(objetoRecibido, doc);
            doc.appendChild(objetoRecibido);

            // Escribir el Documento XML en el OutputStream
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            Result result = new StreamResult(out);
            Source source = new DOMSource(doc);
            transformer.transform(source, result);
            //se generó el xml en el outputStream

        } catch (TransformerException | DOMException | ParserConfigurationException e) {
            //algo salió mal en la creacion o en la transformacion
            e.printStackTrace();
        }

    }
    public void cargarXml(XmlGuardador objeto, InputStream in) {
        try {
            // Crea el objeto DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // El inputStream lo convierte en un documento XML
            Document document = builder.parse(in);

            //Se obtiene la raiz y se la pasa al objeto para que se lea a si mismo
            Element elementoParaCargar = document.getDocumentElement();
            objeto.cargar(elementoParaCargar);

        } catch (IOException | ParserConfigurationException | SAXException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
