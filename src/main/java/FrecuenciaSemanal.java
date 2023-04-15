import java.util.Arrays;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit; //Libreria para formatear dias en LocalDateTime
import java.util.Locale; //Libreria que indica el output del idioma del dia de la semana
import java.time.format.TextStyle; //Libreria para darle formato al dia de la semana
import java.time.DayOfWeek;


/**
 * FrecuenciaSemanal
 */
public class FrecuenciaSemanal implements Frecuencia {

    private DayOfWeek[] diasDeLaSemana; //arrays de dias de la semana en ingles. Estan en orden y el primer dia que aparece es el mismo dia que el comienzo del evento
    // private Repeticion repeticion;
    // private LocalDateTime finFrecuencia;
    private Repeticion repeticion;

    public FrecuenciaSemanal(DayOfWeek[] diasDeLaSemana, Repeticion repeticion){
	// this.tipo = tipo;
	// this.fin = fin;
	this.diasDeLaSemana = diasDeLaSemana;
	this.repeticion = repeticion;
    }


    public LocalDateTime finDeLasRepeticionesDadaFecha(LocalDateTime fechaComienzo){
	this.repeticion.finDeLasRepeticionesDadaFechaREPETICION(fechaComienzo, this.diasDeLaSemana);
    }

    public boolean dadoComienzoCaeElDia(LocalDateTime fechaComienzo, LocalDateTime diaEspecifico){
    






	// String diasDeLaSemanaDelDiaEspecifico = diaEspecifico.getDayOfWeek().getDisplayName(TextStyle.SHORT,Locale.ENGLISH);
	// boolean esUnDiaDeRepeticion = Arrays.asList(this.diasDeLaSemana).contains(diasDeLaSemanaDelDiaEspecifico); //Va a buscar si el dia de la semana del evento cae en uno de los dias repetidos
	int diferenciaEnDiasDeSemana = fechaComienzo.getDayOfWeek().getValue() - diaEspecifico.getDayOfWeek().getValue();

	
	//
	// LocalDateTime fechaComienzoPivote;
	// for (int i = 0; i < this.diasDeLaSemana.length; i++) {
	//     if (diaEspecifico.getDayOfWeek().getDisplayName(TextStyle.SHORT,Locale.ENGLISH).equals(this.diasDeLaSemana[i])) {
	// 	fechaComienzoPivote = 

	//     }

	



	return true;
    }
	
}
