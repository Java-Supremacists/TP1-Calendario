import java.time.LocalDateTime;
import java.time.DayOfWeek;
/**
 * Repeticion
 */
public interface Repeticion {
    public LocalDateTime finDeLaRepeticion(LocalDateTime fechaCominezo, int cadaCuantosDias);

    public LocalDateTime finDeLaRepeticion(LocalDateTime fechaCominezo, DayOfWeek[] diasDeLaSemana);


	
}
