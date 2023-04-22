import java.time.LocalDateTime;
import java.time.DayOfWeek;

public class Main {
    public static void main(String[] args) {
	// var tarea = new Tarea("Esto es una tarea","descripcion",false);
	// var evento = new Tarea("Esto es un evento","descripcion",false);

	// Actividad[] array = new Actividad[2];
	// array[0] = tarea;
	// array[1] = evento;

	// var cal = new Calendario("Nombre",array);
	// System.out.println(cal.tituloPrimeraActividad());
	// System.out.println(cal.tituloSegundaActividad());

	LocalDateTime fechaComienzoRepeticion = LocalDateTime.of(2023, 4, 4, 7, 45, 55);
	int maximaCantidadDeRepeticiones = 4;
	DayOfWeek[] diasDeLaSemana = {DayOfWeek.TUESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY}; 
	// RepeticionCantVeces repeticionCantVecesDePrueba = new RepeticionCantVeces(maximaCantidadDeRepeticiones);

	// Esta fecha es la que cae si haces la cuenta manualmente
	// (Sumarle 2 dias 4 veces a fechaComienzoRepeticion)
	LocalDateTime fechaFinRepeticion = LocalDateTime.of(2023, 4, 13, 7, 45, 55);
	// repeticionCantVecesDePrueba.finDeLaRepeticion(fechaComienzoRepeticion, diasDeLaSemana);
    }
}
