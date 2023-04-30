import org.junit.Test;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;


public class EventoTest {
    @Test
    public void crearEventoVacio() {
        var arranca = LocalDateTime.of(2023,12,8,0,0);
        var termina = LocalDateTime.of(2023,12,9,0,0);
        var even1 = new Evento(arranca,termina);


        assertEquals(arranca, even1.cuandoEmpieza());
        assertEquals(termina, even1.cuandoTermina());
        arranca = LocalDateTime.of(2023,12,9,0,0);
        termina = LocalDateTime.of(2023,12,10,0,0);
        even1.setArranque(arranca);
        even1.setTermina(termina);
        assertEquals(arranca, even1.cuandoEmpieza());
        assertEquals(termina, even1.cuandoTermina());
        assertNull(even1.ultimaAlarma());
        assertEquals("", even1.getDescripcion());
        assertEquals("", even1.getTitulo());
        assertFalse(even1.esDiaEntero());
        even1.setEsDiaCompleto(true);
        even1.setName("Nombre1");
        even1.setDescription("Descripcion1");
        assertEquals("Descripcion1", even1.getDescripcion());
        assertEquals("Nombre1", even1.getTitulo());
        assertTrue(even1.esDiaEntero());
    }
    @Test
    public void creacionDeEventos() {
        ArrayList<LocalDateTime> alarmas = new ArrayList<>();
        alarmas.add(LocalDateTime.of(2023,1,1,0,0));
        alarmas.add(LocalDateTime.of(2023,5,24,11,30));
        alarmas.add(LocalDateTime.of(2023,3,12,18,0));
        alarmas.add(LocalDateTime.of(2023,9,12,23,30));
        alarmas.add(LocalDateTime.of(2023,1,1,0,0));
        var arranca = LocalDateTime.of(2023,12,8,0,0);
        var termina = LocalDateTime.of(2023,12,8,0,0);


        var even1 = new Evento(arranca,termina);
        even1.setName("Nombre1");
        even1.setDescription("Descripcion1");
        even1.agregarAlarmas(alarmas);
        even1.setEsDiaCompleto(true);

        var even2 = new Evento(arranca,termina);
        even2.setName("Nombre2");
        even2.setDescription("Descripcion2");
        even2.setEsDiaCompleto(false);


        assertEquals("Nombre1", even1.getTitulo());
        assertEquals("Descripcion1", even1.getDescripcion());
        assertTrue(even1.esDiaEntero());
        assertEquals(arranca, even1.cuandoEmpieza());
        assertEquals(termina, even1.cuandoTermina());
        assertEquals("Nombre2", even2.getTitulo());
        assertEquals("Descripcion2", even2.getDescripcion());
        assertFalse(even2.esDiaEntero());
        assertEquals(arranca, even2.cuandoEmpieza());
        assertEquals(termina, even2.cuandoTermina());
    }

    @Test
    public void UltimaAlarmaYSonarUltimaAlarma() {
        var arranca = LocalDateTime.of(2023,12,8,0,0);
        var termina = LocalDateTime.of(2023,12,8,0,0);
        var even1 = new Evento(arranca,termina);
        var even2 = new Evento(arranca,termina);

        var hascodeEven1 =even1.hashCode();
        var hascodeEven2 =even2.hashCode();
        assertNotEquals(hascodeEven1,hascodeEven2 );
        even1.setName("Distinto 1");
        assertEquals(hascodeEven1,even1.hashCode() );
        // https://stackoverflow.com/a/32450295/13683575
    }

    @Test
    public void testDeCaeElDia() {
        var arranca = LocalDateTime.of(2023, 4,4,5,0);
        var termina = LocalDateTime.of(2023, 4,4,12,0);

        var even1 = new Evento(arranca, termina);

        int cadaCuantosDias = 2;
        var repeticionInfinita = new RepeticionInfinita();
        FrecuenciaDiaria frecuenciaDiaria = new FrecuenciaDiaria(cadaCuantosDias, repeticionInfinita);

        even1.setFrecuencia(frecuenciaDiaria);
        var fechaQueCae = LocalDateTime.of(2023, 4, 12, 5, 0);
        var fechaQueNoCae = LocalDateTime.of(2023, 4, 13, 5, 0);

        assertEquals(true, even1.caeElDia(fechaQueCae));
        assertEquals(false, even1.caeElDia(fechaQueNoCae));
    }

    @Test
    public void testDeSetFrecuencia() {
        var arranca = LocalDateTime.of(2023, 4,4,5,0);
        var termina = LocalDateTime.of(2023, 4,4,12,0);

        var even1 = new Evento(arranca, termina);

        int cadaCuantosDias = 2;
        var repeticionInfinita = new RepeticionInfinita();
        FrecuenciaDiaria frecuenciaDiaria = new FrecuenciaDiaria(cadaCuantosDias, repeticionInfinita);
        even1.setFrecuencia(frecuenciaDiaria);

        DayOfWeek[] diasDeLaSemana = {DayOfWeek.TUESDAY, DayOfWeek.THURSDAY};
        FrecuenciaSemanal frecuenciaSemanal = new FrecuenciaSemanal(diasDeLaSemana, repeticionInfinita);
        even1.setFrecuencia(frecuenciaSemanal);//Actualizo la frecuencia

        var fechaQueCae = LocalDateTime.of(2023, 4, 11, 5, 0);
        var fechaQueNoCae = LocalDateTime.of(2023, 4, 12, 5, 0);

        assertEquals(true, even1.caeElDia(fechaQueCae));
        assertEquals(false, even1.caeElDia(fechaQueNoCae));
    }

    @Test
    public void testProximoEventoMasCercanoFrecuenciaDiaria() {
        int cadaCuantosdias = 2;
        var arranca = LocalDateTime.of(2023, 4, 4, 0, 0, 0);
        var termina = LocalDateTime.of(2023, 4, 13, 0, 0, 0);
        var fechaMasCercana = LocalDateTime.of(2023, 4, 14, 0, 0, 0);

        var repeticionInfinita = new RepeticionInfinita();
        var frecuenciadiaria = new FrecuenciaDiaria(cadaCuantosdias, repeticionInfinita);

        var even1 = new Evento(arranca, termina);
        even1.setFrecuencia(frecuenciadiaria);

        assertEquals(fechaMasCercana, even1.proximoEventoMasCercanoAFechaEspecifica(termina));
    }

}
