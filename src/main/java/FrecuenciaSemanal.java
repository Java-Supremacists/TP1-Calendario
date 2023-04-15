import java.time.LocalDateTime;
import java.time.DayOfWeek;


/**
 * FrecuenciaSemanal
 */
public class FrecuenciaSemanal implements Frecuencia {

    private DayOfWeek[] diasDeLaSemana; //arrays de dias de la semana en ingles.
					//PRECONDICION: Estan en orden y el 
					//primer dia que aparece es el mismo
					//dia que el comienzo del evento
    private Repeticion repeticion;

    public FrecuenciaSemanal(DayOfWeek[] diasDeLaSemana, Repeticion repeticion){
	this.diasDeLaSemana = diasDeLaSemana;
	this.repeticion = repeticion;
    }


    public LocalDateTime finDeLasRepeticionesDadaFecha(LocalDateTime fechaComienzo){
	LocalDateTime finDeLaRepeticion = this.repeticion.finDeLaRepeticion(fechaComienzo, this.diasDeLaSemana);
	return finDeLaRepeticion;
    }

    public boolean dadoComienzoCaeElDia(LocalDateTime fechaComienzo, LocalDateTime diaEspecifico){
	DayOfWeek diasDeLaSemanaDelDiaEspecifico = diaEspecifico.getDayOfWeek();

	boolean estaEnElDiaDeLaSemana = false;
	for (int i = 0; i < this.diasDeLaSemana.length; i++) {
	    if (this.diasDeLaSemana[i] == diasDeLaSemanaDelDiaEspecifico) {
		estaEnElDiaDeLaSemana = true;
		break;
	    }
	}

	return estaEnElDiaDeLaSemana;
    }
	
}
