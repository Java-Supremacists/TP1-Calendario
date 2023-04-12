import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class EventoTest {

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
        var even1 = new Evento("Nombre1","Descripcion1",alarmas,true,arranca,termina);
        var even2 = new Evento("Nombre2","Descripcion2",false,arranca,termina);
        assertEquals("Nombre1", even1.getTitulo());
        assertEquals("Descripcion1", even1.getDescripcion());
        assertTrue(even1.esDiaEntero());
        assertEquals(Activities.tipo.EVENTO, even1.type());
        assertEquals(arranca, even1.cuandoEmpieza());
        assertEquals(termina, even1.cuandoTermina());
        assertTrue(even1.quedanAlarmas());
        assertEquals(LocalDateTime.of(2023,1,1,0,0), even1.ultimaAlarma());
        even1.setAlarm(new ArrayList<>());
        assertFalse(even1.quedanAlarmas());
        assertEquals("Nombre2", even2.getTitulo());
        assertEquals("Descripcion2", even2.getDescripcion());
        assertFalse(even2.esDiaEntero());
        assertEquals(Activities.tipo.EVENTO, even2.type());
        assertEquals(arranca, even2.cuandoEmpieza());
        assertEquals(termina, even2.cuandoTermina());
        assertFalse(even2.quedanAlarmas());
        even2.setAlarm(new ArrayList<>());
        assertFalse(even2.quedanAlarmas());
    }
    @Test
    public void quedanAlarmas() {
    }

    @Test
    public void ultimaAlarma() {
    }

    @Test
    public void sonarUltimaAlarma() {
    }

    @Test
    public void getTitulo() {
    }

    @Test
    public void getDescripcion() {
    }

    @Test
    public void esDiaEntero() {
    }

    @Test
    public void setName() {
    }

    @Test
    public void setDescription() {
    }

    @Test
    public void setAlarm() {
    }

    @Test
    public void setComplete() {
    }

    @Test
    public void testType() {
    }

    @Test
    public void testCuandoEmpieza() {
    }

    @Test
    public void testCuandoTermina() {
    }

    @Test
    public void setArranque() {
    }

    @Test
    public void setTermina() {
    }
}