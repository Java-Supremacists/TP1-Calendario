import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.time.LocalDateTime;


public class EventoTest {
    @Test
    public void crearEventoVacio() {
        var arranca = LocalDateTime.of(2023,12,8,0,0);
        var termina = LocalDateTime.of(2023,12,9,0,0);
        var even1 = new Evento("","",null,false,arranca,termina,null);


        assertEquals(Activities.tipo.EVENTO, even1.type());
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
        even1.setComplete(true);
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


        var even1 = new Evento("Nombre1","Descripcion1",alarmas,true,arranca,termina,null);
        var even2 = new Evento("Nombre2","Descripcion2",null,false,arranca,termina,null);


        assertEquals("Nombre1", even1.getTitulo());
        assertEquals("Descripcion1", even1.getDescripcion());
        assertTrue(even1.esDiaEntero());
        assertEquals(Activities.tipo.EVENTO, even1.type());
        assertEquals(arranca, even1.cuandoEmpieza());
        assertEquals(termina, even1.cuandoTermina());
        assertEquals("Nombre2", even2.getTitulo());
        assertEquals("Descripcion2", even2.getDescripcion());
        assertFalse(even2.esDiaEntero());
        assertEquals(Activities.tipo.EVENTO, even2.type());
        assertEquals(arranca, even2.cuandoEmpieza());
        assertEquals(termina, even2.cuandoTermina());
    }
    @Test
    public void CreacionEventosVariado() {
    }

    @Test
    public void eventoCaeDiaPedido(){

	//arrange
	ArrayList<LocalDateTime> alarmas = new ArrayList<LocalDateTime>();
	//of(int year, int month, int dayOfMonth, int hour, int minute) 
	// Evento eventoDePrueba = new Evento("Evento de prueba", "Descripcion de prueba", alarmas, false, LocalDateTime.of(2023, 4, 10, 7, 45, 55), LocalDateTime.of(2024, 3, 10, 7, 45, 55));

	//assert
	// assertEquals(true, eventoDePrueba.caeElDia(LocalDateTime.of(2023, 4, 22, 7, 45, 55)));
	// assertEquals(false, eventoDePrueba.caeElDia(LocalDateTime.of(2023, 4, 21, 7, 45, 55)));
	// assertEquals(false, eventoDePrueba.caeElDia(LocalDateTime.of(2023, 4, 20, 7, 45, 55)));
    }

    @Test
    public void cuandoEmpiezaTest(){

    }
    @Test
    public void cuandoTerminaTest(){

    }
    @Test
    public void UltimaAlarmaYSonarUltimaAlarma(){

    }

}
