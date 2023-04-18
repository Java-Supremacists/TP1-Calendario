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


    // public LocalDateTime finDeLasRepeticionesDadaFecha(LocalDateTime fechaComienzo){
	// LocalDateTime finDeLaRepeticion = this.repeticion.finDeLaRepeticion(fechaComienzo, this.diasDeLaSemana);
	// return finDeLaRepeticion;
    // }

    public boolean dadoComienzoCaeElDia(LocalDateTime fechaComienzo, LocalDateTime diaEspecifico){
	//Averiguo el dia de la semana del evento que me piden
	DayOfWeek diasDeLaSemanaDelDiaEspecifico = diaEspecifico.getDayOfWeek();

	//En este loop me fijo si el dia de la semana del dia que que me 
	//piden esta en mi lista original
	//TODO: Se podria hacer con un is value in List
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
