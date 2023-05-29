import java.time.DayOfWeek;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ControlerXmlTest {
    @Test
    public void testConGuardadoDeEventos() {
        var inicia = LocalDateTime.of(2023,5,19,10,30);
        var termina = LocalDateTime.of(2023,5,19,20,0);
        var ev1 = new Evento("Evento1","Esta es la descripcion a cargar",false,inicia,termina);
        var ev2 = new Evento(null,null);
        ByteArrayOutputStream archivo = new ByteArrayOutputStream();
        var xmlManejador = new ControlerXml();


        xmlManejador.generateXml(ev1,"Evento",archivo);
        InputStream cargarArchivo = new ByteArrayInputStream(archivo.toByteArray());
        xmlManejador.cargarXml(ev2,cargarArchivo);


        assertEquals(ev1.getTitulo(),ev2.getTitulo());
        assertEquals(ev1.getDescripcion(),ev2.getDescripcion());
        assertEquals(ev1.esDiaEntero(),ev2.esDiaEntero());
        assertEquals(ev1.cuandoEmpieza(),ev2.cuandoEmpieza());
        assertEquals(ev1.cuandoTermina(),ev2.cuandoTermina());
        assertNotEquals(ev1,ev2);
    }

    @Test
    public void testConGuardadoDeTareas() {
        var termina = LocalDateTime.of(2023,5,19,20,0);
        var tarea1 = new Tarea(termina);
        var tarea2 = new Tarea(null);
        ByteArrayOutputStream archivo = new ByteArrayOutputStream();
        var xmlManejador = new ControlerXml();


        xmlManejador.generateXml(tarea1,"Tarea",archivo);
        InputStream cargarArchivo = new ByteArrayInputStream(archivo.toByteArray());
        xmlManejador.cargarXml(tarea2,cargarArchivo);


        assertEquals(tarea1.getTitulo(),tarea2.getTitulo());
        assertEquals(tarea1.getDescripcion(),tarea2.getDescripcion());
        assertEquals(tarea1.esDiaEntero(),tarea2.esDiaEntero());
        assertEquals(tarea1.cuandoEmpieza(),tarea2.cuandoEmpieza());
        assertNotEquals(tarea1,tarea2);
    }


    @Test
    public void testConGuardadoDeAlarmas() {
        var alarm1 = new Alarmas(true,null);
        var alarm2 = new Alarmas();
        List<LocalDateTime> array = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            array.add(LocalDateTime.of(2023,i,i,i,i));
        }
        alarm1.agregarAlarma(array);
        ByteArrayOutputStream archivo = new ByteArrayOutputStream();
        var xmlManejador = new ControlerXml();

        xmlManejador.generateXml(alarm1,"Clase_Alarma",archivo);
        InputStream cargarArchivo = new ByteArrayInputStream(archivo.toByteArray());
        xmlManejador.cargarXml(alarm2,cargarArchivo);

        assertEquals(alarm1.size(),alarm2.size());
        assertEquals(alarm1.repiteLasAlarmas(),alarm2.repiteLasAlarmas());
        assertEquals(alarm1.primerAlarmaASonar(),alarm2.primerAlarmaASonar());
        for (int i = 1; i < 13; i++) {
            var alarmax = LocalDateTime.of(2023,i,i,i,i);
            assertEquals(alarm1.size(),alarm2.size());
            assertEquals(alarm1.quedanAlarmas(),alarm2.quedanAlarmas());
            assertEquals(alarm1.existeAlarma(alarmax),alarm2.existeAlarma(alarmax));
            assertEquals(alarm1.primerAlarmaASonar(),alarm2.primerAlarmaASonar());
            alarm1.eliminarAlarma(alarmax);
            alarm2.eliminarAlarma(alarmax);
            assertEquals(alarm1.size(),alarm2.size());
            assertEquals(alarm1.quedanAlarmas(),alarm2.quedanAlarmas());
            assertEquals(alarm1.existeAlarma(alarmax),alarm2.existeAlarma(alarmax));
            assertEquals(alarm1.primerAlarmaASonar(),alarm2.primerAlarmaASonar());
        }
    }
    @Test
    public void testConGuardadoDeCalendarios() {
        var calendario1 = new Calendario();
        var calendario2 = new Calendario();


        var inicia1 = LocalDateTime.of(2023,5,19,10,30);
        var termina1 = LocalDateTime.of(2023,5,19,20,0);
        var inicia2 = LocalDateTime.of(2023,5,23,10,30);
        var termina2 = LocalDateTime.of(2023,5,24,10,0);
        var inicia3 = LocalDateTime.of(2023,5,24,10,30);
        var termina3 = LocalDateTime.of(2023,5,24,18,0);
        var inicia4 = LocalDateTime.of(2023,5,22,10,30);
        var termina4 = LocalDateTime.of(2023,5,22,18,0);
        int ID1 = calendario1.crearEvento("Evento1","Descripcion1",true,inicia1,termina1);
        int ID2 = calendario1.crearEvento("Evento2","Descripcion2",false,inicia2,termina2);
        int ID3 = calendario1.crearEvento("Evento3","Descripcion3",false,inicia3,termina3);
        int ID4 = calendario1.crearEvento("Evento4","Descripcion4",false,inicia4,termina4);

        calendario1.modificarActividadAgregarAlarma(ID1,Plazo.HORAANTES);
        calendario1.modificarActividadAgregarAlarma(ID1,Plazo.DIAANTES);
        calendario1.modificarActividadAgregarAlarma(ID1,LocalDateTime.of(2023,5,2,8,30));
        calendario1.modificarActividadAgregarAlarma(ID1,LocalDateTime.of(2023,3,30,20,30));

        calendario1.modificarActividadAgregarAlarma(ID2,Plazo.HORAANTES);
        calendario1.modificarActividadAgregarAlarma(ID2,Plazo.DIAANTES);
        calendario1.modificarActividadAgregarAlarma(ID2,LocalDateTime.of(2023,5,19,8,30));
        calendario1.modificarActividadAgregarAlarma(ID2,LocalDateTime.of(2023,1,30,11,0));

        var repeticionInfinita = new RepeticionInfinita();
        var frecuenciaDiaria = new FrecuenciaDiaria(3, repeticionInfinita);
        calendario1.modificarEventoFrecuencia(ID3, frecuenciaDiaria);

        DayOfWeek[] diasDeLaSemana = {DayOfWeek.MONDAY, DayOfWeek.THURSDAY};
        var frecuenciaSemanal = new FrecuenciaSemanal(diasDeLaSemana, repeticionInfinita);
        calendario1.modificarEventoFrecuencia(ID4, frecuenciaSemanal);

        ByteArrayOutputStream archivo = new ByteArrayOutputStream();
        var xmlManejador = new ControlerXml();

        xmlManejador.generateXml(calendario1,"Calendario",archivo);
        InputStream cargarArchivo = new ByteArrayInputStream(archivo.toByteArray());
        xmlManejador.cargarXml(calendario2,cargarArchivo);

        assertEquals(calendario1.proximaAlarma(),calendario2.proximaAlarma());
        assertEquals(calendario1.obtenerEvento(ID1).getTitulo(),calendario2.obtenerEvento(ID1).getTitulo());
        assertEquals(calendario1.obtenerEvento(ID1).getDescripcion(),calendario2.obtenerEvento(ID1).getDescripcion());
        assertEquals(calendario1.obtenerEvento(ID1).esDiaEntero(),calendario2.obtenerEvento(ID1).esDiaEntero());
        assertEquals(calendario1.obtenerEvento(ID2).getTitulo(),calendario2.obtenerEvento(ID2).getTitulo());
        assertEquals(calendario1.obtenerEvento(ID2).getDescripcion(),calendario2.obtenerEvento(ID2).getDescripcion());
        assertEquals(calendario1.obtenerEvento(ID2).esDiaEntero(),calendario2.obtenerEvento(ID2).esDiaEntero());

        //Corroboramos que el evento sea el mismo despues de aplicar la frecuencia
        assertEquals(calendario1.eventosEnRango(LocalDateTime.of(2023, 5, 26, 0,0),LocalDateTime.of(2023, 5, 28, 0,0)).get(0).cuandoEmpieza(), calendario2.eventosEnRango(LocalDateTime.of(2023, 5, 26, 0,0),LocalDateTime.of(2023, 5, 28, 0,0)).get(0).cuandoEmpieza());
        assertEquals(calendario1.eventosEnRango(LocalDateTime.of(2023, 5, 28, 0,0),LocalDateTime.of(2023, 5, 29, 0,0)).get(0).cuandoEmpieza(), calendario2.eventosEnRango(LocalDateTime.of(2023, 5, 28, 0,0),LocalDateTime.of(2023, 5, 29, 0,0)).get(0).cuandoEmpieza());
    }

    @Test
    public void testTareaDiaCompleto() {
        var calendario1 = new Calendario();
        var calendario2 = new Calendario();


        var termina1 = LocalDateTime.of(2023,5,19,20,0);
	
        int ID1 = calendario1.crearTarea("Tarea1","Descripcion1",true,termina1);
	calendario1.modificarActividadEsDiaEntero(ID1, true);

        ByteArrayOutputStream archivo = new ByteArrayOutputStream();
        var xmlManejador = new ControlerXml();

        xmlManejador.generateXml(calendario1,"Calendario",archivo);
        InputStream cargarArchivo = new ByteArrayInputStream(archivo.toByteArray());
        xmlManejador.cargarXml(calendario2,cargarArchivo);

        assertEquals(calendario1.proximaAlarma(),calendario2.proximaAlarma());
        assertEquals(calendario1.obtenerActividad(ID1).getTitulo(),calendario2.obtenerActividad(ID1).getTitulo());
        assertEquals(calendario1.obtenerActividad(ID1).getDescripcion(),calendario2.obtenerActividad(ID1).getDescripcion());
        assertEquals(calendario1.obtenerActividad(ID1).esDiaEntero(),calendario2.obtenerActividad(ID1).esDiaEntero());

    }

    @Test
    public void testEventoDiaCompleto() {
        var calendario1 = new Calendario();
        var calendario2 = new Calendario();


        var inicia1 = LocalDateTime.of(2023,5,19,10,30);
        var termina1 = LocalDateTime.of(2023,5,19,20,0);
	
        int ID1 = calendario1.crearEvento("Evento1","Descripcion1",true,inicia1, termina1);
	calendario1.modificarActividadEsDiaEntero(ID1, true);

        ByteArrayOutputStream archivo = new ByteArrayOutputStream();
        var xmlManejador = new ControlerXml();

        xmlManejador.generateXml(calendario1,"Calendario",archivo);
        InputStream cargarArchivo = new ByteArrayInputStream(archivo.toByteArray());
        xmlManejador.cargarXml(calendario2,cargarArchivo);

        assertEquals(calendario1.proximaAlarma(),calendario2.proximaAlarma());
        assertEquals(calendario1.obtenerActividad(ID1).getTitulo(),calendario2.obtenerActividad(ID1).getTitulo());
        assertEquals(calendario1.obtenerActividad(ID1).getDescripcion(),calendario2.obtenerActividad(ID1).getDescripcion());
        assertEquals(calendario1.obtenerActividad(ID1).esDiaEntero(),calendario2.obtenerActividad(ID1).esDiaEntero());

    }

    @Test
    public void testTareaCompleta() {
        var calendario1 = new Calendario();
        var calendario2 = new Calendario();


        var termina1 = LocalDateTime.of(2023,5,19,20,0);
	
        int ID1 = calendario1.crearTarea("Tarea1","Descripcion1",true,termina1);
	calendario1.modificarTareaCompletarODescompletar(ID1);

        ByteArrayOutputStream archivo = new ByteArrayOutputStream();
        var xmlManejador = new ControlerXml();

        xmlManejador.generateXml(calendario1,"Calendario",archivo);
        InputStream cargarArchivo = new ByteArrayInputStream(archivo.toByteArray());
        xmlManejador.cargarXml(calendario2,cargarArchivo);

        assertEquals(calendario1.proximaAlarma(),calendario2.proximaAlarma());
        assertEquals(calendario1.obtenerActividad(ID1).getTitulo(),calendario2.obtenerActividad(ID1).getTitulo());
        assertEquals(calendario1.obtenerActividad(ID1).getDescripcion(),calendario2.obtenerActividad(ID1).getDescripcion());
        assertEquals(calendario1.obtenerTarea(ID1).estaCompleta(),calendario2.obtenerTarea(ID1).estaCompleta());

    }
}
