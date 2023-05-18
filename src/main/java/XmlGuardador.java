import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface XmlGuardador {
    void guardar(Element e , Document d);
    void cargar(Element e);
}
