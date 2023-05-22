import java.time.LocalDateTime;
/**
 * RepeticionFecha
 */

import org.w3c.dom.Document;
import org.w3c.dom.Element;
public class RepeticionFecha implements Repeticion {
    private LocalDateTime fechaFinRepeticion;

    public RepeticionFecha(LocalDateTime fechaFinRepeticion) {
        this.fechaFinRepeticion = fechaFinRepeticion;
    }

    @Override
    public boolean estaDentroDeRepeticiones(LocalDateTime fechaPedida) {
        boolean estaDespuesDelLimite = fechaPedida.isBefore(this.fechaFinRepeticion);
        boolean esJustoElLimite = fechaPedida.isEqual(this.fechaFinRepeticion);
        boolean estaDentro = (estaDespuesDelLimite || esJustoElLimite);
        return estaDentro;
    }

    @Override
    public void guardar(Element estructura, Document doc) {
        Element Repeticion = doc.createElement("RepeticionFecha");
        Repeticion.appendChild(doc.createTextNode(String.valueOf(this.fechaFinRepeticion)));
        estructura.appendChild(Repeticion);

    }
}
