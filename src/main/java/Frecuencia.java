import java.time.LocalDateTime;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
/**
 * Frecuencia
 */
public interface Frecuencia {

    boolean dadoComienzoCaeElDia(LocalDateTime inicioEvento, LocalDateTime diaEspecifico);

    LocalDateTime proximoEventoMasCercanoAFechaEspecifica(LocalDateTime inicioEvento, LocalDateTime diaEspecifico);

    void guardar(Element estructura, Document doc);

    void cambiarRepeticion(Repeticion repeticion);

}
