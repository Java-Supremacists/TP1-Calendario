/**
 * Main
 */
// import org.junit.Test;
// import java.io.ByteArrayInputStream;
// import java.io.ByteArrayOutputStream;
// import java.io.InputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;
// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertNotEquals;

public class Main {
    public static void main(String args[]) throws FileNotFoundException {
	var calendario = new Calendario();

        var inicia = LocalDateTime.of(2023,5,19,10,30);
        var termina = LocalDateTime.of(2023,5,19,20,0);
	int iev1 = calendario.crearEvento(inicia, termina);
	Evento ev1 = calendario.obtenerEvento(iev1);
	ev1.setName("Evento1");

        var inicia2 = LocalDateTime.of(2023,5,18,10,30);
        var termina22 = LocalDateTime.of(2023,5,20,20,0);
	int iev2 = calendario.crearEvento(inicia, termina);
	Evento ev2 = calendario.obtenerEvento(iev2);
	ev2.setName("Evento2");


	var repeticion = new RepeticionInfinita();
	var frecuencia = new FrecuenciaMensual(repeticion);
	ev2.setFrecuencia(frecuencia);

	calendario.modificarActividadAgregarAlarma(iev2, Plazo.DIAANTES);


	String str = new String();
	str = "archivos/hola";

	FileOutputStream fw = new FileOutputStream(str);

        var xmlManejador = new ControlerXml();
        xmlManejador.generateXml(ev1,"Evento",fw);
        xmlManejador.generateXml(ev2,"Evento",fw);

	System.out.println("Hola mundo");
	System.out.println(ev1.getTitulo());
    }


	
}
