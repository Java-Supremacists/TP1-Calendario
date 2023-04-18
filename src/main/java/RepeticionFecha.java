import java.time.LocalDateTime;
import java.time.DayOfWeek;
/**
 * RepeticionCantVeces
 */
public class RepeticionFecha implements Repeticion{
    private LocalDateTime fechaFinRepeticion;
    
    public RepeticionFecha(LocalDateTime fechaFinRepeticion) {
	this.fechaFinRepeticion = fechaFinRepeticion;
    }

    @Override
    public boolean estaDentroDeRepeticiones(LocalDateTime fechaPedida) {
	boolean estaDespuesDelLimite = fechaPedida.isBefore(this.fechaFinRepeticion);
    	return estaDespuesDelLimite;
    }
	
}
