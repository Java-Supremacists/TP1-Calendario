/**
 * Main
 */
import java.io.File;
import java.time.DayOfWeek;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;

public class Main {
    public static void main(String args[]) throws FileNotFoundException {
	var calendario = new Calendario();

        var inicia = LocalDateTime.of(2023,5,19,10,30);
        var termina = LocalDateTime.of(2023,5,19,20,0);
	int iev1 = calendario.crearEvento(inicia, termina);
	Evento ev1 = calendario.obtenerEvento(iev1);
	ev1.setName("Evento1");
	var repeticion1 = new RepeticionFecha(LocalDateTime.of(2023,5,19,10,30));
	var frecuencia1 = new FrecuenciaMensual(repeticion1);
	ev1.setFrecuencia(frecuencia1);

        var inicia2 = LocalDateTime.of(2023,5,18,10,30);
        var termina2 = LocalDateTime.of(2023,5,20,20,0);
	int iev2 = calendario.crearEvento(inicia, termina);
	Evento ev2 = calendario.obtenerEvento(iev2);
	ev2.setName("Evento2");


	var repeticion = new RepeticionCantVeces(5, 30, inicia2);
	var frecuencia = new FrecuenciaMensual(repeticion);
	ev2.setFrecuencia(frecuencia);

	calendario.modificarActividadAgregarAlarma(iev2, Plazo.DIAANTES);


        var inicia3 = LocalDateTime.of(2023,5,18,10,30);
        var termina3 = LocalDateTime.of(2023,5,20,20,0);
	int iev3 = calendario.crearEvento(inicia3, termina3);
	Evento ev3 = calendario.obtenerEvento(iev3);
	ev3.setName("Evento3");
	var repeticion3 = new RepeticionInfinita();
        DayOfWeek[] diasDeLaSemana = {DayOfWeek.MONDAY, DayOfWeek.THURSDAY};
	var frecuencia3 = new FrecuenciaSemanal(diasDeLaSemana, repeticion3);
	ev3.setFrecuencia(frecuencia3);

	

	String str = new String();
	str = "archivos/hola";

	FileOutputStream fw = new FileOutputStream(str);

        var xmlManejador = new ControlerXml();
        xmlManejador.generateXml(ev1,"Evento",fw);
        xmlManejador.generateXml(ev2,"Evento",fw);
        xmlManejador.generateXml(ev3,"Evento",fw);

	System.out.println("Hola mundo");
	System.out.println(ev1.getTitulo());
    }


	
}
