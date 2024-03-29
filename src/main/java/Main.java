/**
 * Main
 */
import java.time.DayOfWeek;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;

public class Main {
    public static void main(String args[]) throws FileNotFoundException {
        //Esta funcion main la usamos para testear el comportamiento de las
        //funciones mientras las desarrollabamos
        //Genera un archivo con los datos guardados

        var calendario = new Calendario();

        var inicia = LocalDateTime.of(2023,5,19,10,30);
        var termina = LocalDateTime.of(2023,5,19,20,0);
        int iev1 = calendario.crearEvento(inicia, termina);
        Evento ev1 = calendario.obtenerEvento(iev1);
        ev1.setName("Evento1");
        var repeticion1 = new RepeticionFecha(LocalDateTime.of(2023,5,19,10,30));
        var frecuencia1 = new FrecuenciaDiaria(6, repeticion1);
        ev1.setFrecuencia(frecuencia1);

        var inicia2 = LocalDateTime.of(2023,5,18,10,30);
        var termina2 = LocalDateTime.of(2023,5,20,20,0);
        int iev2 = calendario.crearEvento(inicia, termina2);
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





        var inicia4 = LocalDateTime.of(2023,5,18,10,30);
        var termina4 = LocalDateTime.of(2023,5,20,20,0);
        int iev4 = calendario.crearEvento(inicia4, termina4);
        Evento ev4 = calendario.obtenerEvento(iev4);
        ev4.setName("Evento4");
        DayOfWeek[] diasDeLaSemana4 = {DayOfWeek.MONDAY, DayOfWeek.FRIDAY};
        var repeticion4 = new RepeticionCantVeces(4, diasDeLaSemana, inicia4);
        var frecuencia4 = new FrecuenciaSemanal(diasDeLaSemana4, repeticion4);
        ev4.setFrecuencia(frecuencia4);



        String str = new String();
        str = "archivos/hola";

        FileOutputStream fw = new FileOutputStream(str);

        var xmlManejador = new ControlerXml();


        int ita1 = calendario.crearTarea(termina);
        calendario.modificarActividadDescripcion(ita1,"Descripcion tarea");

        xmlManejador.generateXml(calendario,"Calendario",fw);

        System.out.println(ev1.getTitulo());








        // Esta porcion del codigo lee el archivo
        // String str = new String();
        // str = "archivos/hola";

        // FileInputStream fi = new FileInputStream(str);

        // var xmlManejador = new ControlerXml();

        // var calendario = new Calendario();
        // System.out.println("Hola mundo");
        // xmlManejador.cargarXml(calendario, fi);

        // calendario.longTareasYEventos();

        System.out.println("\u001B[32m" + "BUILD SUCCESS" + "\u001B[0m");



    }



}
