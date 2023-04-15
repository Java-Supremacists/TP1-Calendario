// import java.time.temporal.ChronoUnit; //Libreria para formatear dias en LocalDateTime
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

    //Esta funcion ignora todos los argumentos que le pasas, esto esta diseniado
    //para que la funcion pueda ser polimorfica con la clase RepeticionCantVeces
    //No me parece ideal, pero creo que queda mejor que un par de "ifs"
    public LocalDateTime finDeLaRepeticion(LocalDateTime fechaComienzo, int cadaCuantosDias) {
	return this.fechaFinRepeticion;
	
    }

    public LocalDateTime finDeLaRepeticion(LocalDateTime fechaComienzo, DayOfWeek[] diasDeLaSemana) {
	return this.fechaFinRepeticion;

    }
	
}
