import java.time.temporal.ChronoUnit; //Libreria para formatear dias en LocalDateTime
import java.time.LocalDateTime;
import java.time.DayOfWeek;
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

    //Esta funcion ignora todos los argumentos que le pasas, esto esta diseniado
    //para que la funcion pueda ser polimorfica con la clase RepeticionCantVeces
    //No me parece ideal, pero creo que queda mejor que un par de "ifs"
    public LocalDateTime finDeLasRepeticionesDadaFechaREPETICION(LocalDateTime fechaComienzo, int cadaCuantosDias) {
	return this.fechaFinRepeticion;
	
    }

    public LocalDateTime finDeLasRepeticionesDadaFechaREPETICION(LocalDateTime fechaComienzo, DayOfWeek[] diasDeLaSemana) {
	return this.fechaFinRepeticion;

    }
	
}
