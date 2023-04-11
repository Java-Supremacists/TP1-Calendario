import java.time.temporal.ChronoUnit; //Libreria para formatear dias en LocalDateTime
import java.time.LocalDateTime;
/**
 * RepeticionCantVeces
 */
public class RepeticionFecha{
    private LocalDateTime fechaFinRepeticion;
    
    public RepeticionFecha(LocalDateTime fechaFinRepeticion) {
	this.fechaFinRepeticion = fechaFinRepeticion;
    }

    public boolean laRepeticionSigue(LocalDateTime diaEspecifico) {

	return true;
    }

	
}
