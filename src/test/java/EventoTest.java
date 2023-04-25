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
        // var even1 = new Evento("","",null,false,arranca,termina,null);
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


        // TODO: Sacar comentario de la creacio nde la version anterior
        // var even1 = new Evento("Nombre1","Descripcion1",alarmas,true,arranca,termina,null);
        var even1 = new Evento(arranca,termina);
        even1.setName("Nombre1");
        even1.setDescription("Descripcion1");
        even1.agregarAlarmas(alarmas);
        even1.setComplete(true);

        // TODO: Sacar comentario de la creacio nde la version anterior
        // var even2 = new Evento("Nombre2","Descripcion2",null,false,arranca,termina,null);
        var even2 = new Evento(arranca,termina);
        even2.setName("Nombre2");
        even2.setDescription("Descripcion2");
        even2.setComplete(false);


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
    public void CreacionEventosVariado() {
    }
    @Test
    public void eventoCaeDiaPedido(){

    /*arrange
	//ArrayList<LocalDateTime> alarmas = new ArrayList<LocalDateTime>();
	//of(int year, int month, int dayOfMonth, int hour, int minute) 
	// Evento eventoDePrueba = new Evento("Evento de prueba", "Descripcion de prueba", alarmas, false, LocalDateTime.of(2023, 4, 10, 7, 45, 55), LocalDateTime.of(2024, 3, 10, 7, 45, 55));

	//assert
	// assertEquals(true, eventoDePrueba.caeElDia(LocalDateTime.of(2023, 4, 22, 7, 45, 55)));
	// assertEquals(false, eventoDePrueba.caeElDia(LocalDateTime.of(2023, 4, 21, 7, 45, 55)));
	// assertEquals(false, eventoDePrueba.caeElDia(LocalDateTime.of(2023, 4, 20, 7, 45, 55)));*/
    }
    @Test
    public void cuandoEmpiezaTest(){
    }
    @Test
    public void cuandoTerminaTest(){
    }
    @Test
    public void UltimaAlarmaYSonarUltimaAlarma(){
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
    public void actualizarEvento() {
    }

    @Test
    public void testDeCaeElDia(){
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
    public void testDeSetFrecuencia(){
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
     
    //@Test
    //public void testProximoEventoMasCercanoFrecuenciaDiaria(){
	    //int cadaCuantosdias = 2;
	    //var arranca = LocalDateTime.of(2023, 4, 4, 0, 0, 0)
	    //var termina = LocalDateTime.of(2023, 4, 13, 0, 0, 0)
	    //var fechaMasCercana = LocalDateTime.of(2023, 4, 14, 0, 0, 0)
     
	    //var repeticionInfinita = RepeticionInfinita();
	    //var frecuenciadiaria = FrecuenciaDiaria(cadaCuantosdias, repeticionInfinita);
	    ////Copiar fechas de test de frecuencia
     
	    //var even1 = new Evento(arranca, termina);
	    //var even1.setFrecuencia(frecuenciadiaria) //Cambiamos la frecuencia para ver si sigue funcionando
     
	    //assertEquals(fechaMasCercana, even1.proximoEvento(fechaQueCae));
    //}

}
