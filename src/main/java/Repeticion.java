import java.time.LocalDateTime;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
/**
 * Repeticion
 */
public interface Repeticion {

    public boolean estaDentroDeRepeticiones(LocalDateTime fechaPedida);

    void guardar(Element estructura, Document doc);

}
