import java.time.LocalDateTime;
import java.time.DayOfWeek;
/**
 * RepeticionCantVeces
 */
public class RepeticionCantVeces implements Repeticion{
    private int cantidadDeRepeticiones;

    public RepeticionCantVeces(int cantidadDeRepeticiones) {
	this.cantidadDeRepeticiones = cantidadDeRepeticiones;
    }

    public LocalDateTime finDeLaRepeticion(LocalDateTime fechaComienzo, int cadaCuantosDias) {
	//Esto nos da la cantidad de dias extra que para llegar al ultimo dia
	int cantidadDeDiasASumar = cadaCuantosDias + this.cantidadDeRepeticiones; 

	//Le sumamos esos dias a la fecha que nos pasaron
	LocalDateTime fechaFinal = fechaComienzo.plusDays(cantidadDeDiasASumar);

	
	return fechaFinal;
    }

    public LocalDateTime finDeLaRepeticion(LocalDateTime fechaComienzo, DayOfWeek[] diasDeLaSemana) {

	//La idea de este for loop es que te diga en que dia DE LA SEMANA cae 
	//el ultimo dia
	int diaDeLaSemana = 0;
	int cantidadDeRepeticiones = 1;
	for (int i = 0; i < this.cantidadDeRepeticiones; i ++) {
	    if (diaDeLaSemana > diasDeLaSemana.length) {
		diaDeLaSemana = 0;
		cantidadDeRepeticiones++;
	    }
	    diaDeLaSemana++;
	}
	//Esto tal vez se puede hacer con un modulo
	//TODO: Conseguir a alguien inteligente que sepa hacer esto con un modulo

	
	LocalDateTime offsetDiaDeLaSemana = fechaComienzo;
	DayOfWeek diaDeLaSemanaDeInicio = diasDeLaSemana[diaDeLaSemana];

	//Cuando estos dos sean iguales significa que llegamos al dia mas 
	//proximo con el dia de la semana que necesitamos
	while (offsetDiaDeLaSemana.getDayOfWeek() != diaDeLaSemanaDeInicio) { 
	    offsetDiaDeLaSemana = offsetDiaDeLaSemana.plusDays(1);
	}

	LocalDateTime fechaFinal;

	//7 Harcodeado porque hay 7 dias entre dos fechas con el mismo dia de 
	//la semana (ej: 7 dias entre el martes 4 y martes 11)
	fechaFinal = offsetDiaDeLaSemana.plusDays(cantidadDeRepeticiones * 7); 
	
	return fechaFinal;

    }
	
}