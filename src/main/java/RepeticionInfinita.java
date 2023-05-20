import java.time.LocalDateTime;
/**
 * RepeticionInfinita
 */

import org.w3c.dom.Document;
import org.w3c.dom.Element;
public class RepeticionInfinita implements Repeticion {

    public RepeticionInfinita() {
    }

    @Override
    public boolean estaDentroDeRepeticiones(LocalDateTime fechaPedida) {
        return true;
    }

    @Override
    public void guardar(Element estructura, Document doc) {
    	// TODO Auto-generated method stub
    	
    }
}
