import java.time.temporal.ChronoUnit; //Libreria para formatear dias en LocalDateTime
import java.time.LocalDateTime;
/**
 * RepeticionCantVeces
 */
public class RepeticionCantVeces {
    private int cantidadDeRepeticiones;

    private LocalDateTime fechaFinRepeticion;
    
    public RepeticionCantVeces(int cantidadDeRepeticiones, LocalDateTime diaComienzo) {
	this.cantidadDeRepeticiones = cantidadDeRepeticiones;
    }

    public LocalDateTime finDeLasRepeticionesDadaFechaREPETICION(LocalDateTime fechaComienzo, int cadaCuantosDias) {
	int cantidadDeDiasASumar = cadaCuantosDias + this.cantidadDeRepeticiones; //Esto nos da la cantidad de dias extra que para llegar al ultimo dia

	LocalDateTime fechaFinal = fechaComienzo.plusDays(cantidadDeDiasASumar);
	
	return fechaFinal;
    }



































    public boolean laRepeticionSigue(LocalDateTime fechaComienzo, LocalDateTime diaEspecifico) {
	long cantDiasHastaDiaPedido = fechaComienzo.until(diaEspecifico, ChronoUnit.DAYS); //Se fija cuantos dias hay hasta el dia pasado como argumento
	
	if (cantidadDeRepeticionesRequeridas > cantidadDeRepeticiones) {
	    return false; //Si el evento necesita mas repeticiones de las que tengo disponibles, no cae ese dia
	}

	return true;
    }





	
}
