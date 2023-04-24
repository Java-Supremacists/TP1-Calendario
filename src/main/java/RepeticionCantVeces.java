import java.time.LocalDateTime;
import java.time.DayOfWeek;
/**
 * RepeticionCantVeces
 */
public class RepeticionCantVeces implements Repeticion{
    private LocalDateTime fechaFinRepeticion;

    //No estoy totalmente contento con la presencia de dos constructores 
    //distintos, sin 
    public RepeticionCantVeces(int cantidadDeRepeticionesMaximas, int cadaCuantosDias, LocalDateTime fechaComienzo) {
	//Esto nos da la cantidad de dias extra para llegar al ultimo dia
	//Le restamos uno a cantidadDeRepeticionesMaximas porque el dia en el 
	//que estamos cuenta como una repeticion
	int cantidadDeDiasASumar = cadaCuantosDias * (cantidadDeRepeticionesMaximas - 1);

	//Le sumamos esos dias a la fecha que nos pasaron
	LocalDateTime fechaFinal = fechaComienzo.plusDays(cantidadDeDiasASumar);

	
	this.fechaFinRepeticion = fechaFinal;
    }

    public RepeticionCantVeces(int cantidadDeRepeticionesMaximas, LocalDateTime fechaComienzo, DayOfWeek[] diasDeLaSemana) {
	//La idea de este for loop es que te diga en que dia DE LA SEMANA cae 
	//el ultimo dia
	int diaDeLaSemana = -1; //Arranca en -1 porque lo primero que hace es 
				//sumar, y tiene que arrancar siendo 0
	int cantidadDeRepeticionesSemanales = 0;
	for (int i = 0; i < cantidadDeRepeticionesMaximas; i ++) {
	    //Cuando esto se cumple, significa que dio una vuelta completa a la
	    //cantidad de dias. Le sumo un dia porque el if "le tiene que ganar"
	    //al for en la siguiente iteracion

	    diaDeLaSemana++;
	    if (diaDeLaSemana == diasDeLaSemana.length ) {
		diaDeLaSemana = 0;
		cantidadDeRepeticionesSemanales++;
	    }
	}
	//Esto de aca arriba tal vez se puede hacer con un modulo
	//TODO: Conseguir a alguien inteligente que sepa hacer esto con un modulo

	LocalDateTime offsetDiaDeLaSemana = fechaComienzo;
	DayOfWeek diaDeLaSemanaDeInicio = diasDeLaSemana[diaDeLaSemana];

	//Cuando estos dos sean iguales significa que llegamos al dia mas 
	//proximo con el dia de la semana que necesitamos
	while (offsetDiaDeLaSemana.getDayOfWeek() != diaDeLaSemanaDeInicio) { 
	    offsetDiaDeLaSemana = offsetDiaDeLaSemana.plusDays(1);
	}


	//7 Harcodeado porque hay 7 dias entre dos fechas con el mismo dia de 
	//la semana (ej: 7 dias entre el martes 4 y martes 11)
	LocalDateTime fechaFinal = offsetDiaDeLaSemana.plusDays(cantidadDeRepeticionesSemanales * 7); 
	
	this.fechaFinRepeticion = fechaFinal;
    }

    @Override
    public boolean estaDentroDeRepeticiones(LocalDateTime fechaPedida) {
	boolean estaDespuesDelLimite = fechaPedida.isBefore(this.fechaFinRepeticion);
	boolean esJustoElLimite = fechaPedida.isEqual(this.fechaFinRepeticion);
	boolean estaDentro = (estaDespuesDelLimite || esJustoElLimite);
    	return estaDentro;
    }
	
}
