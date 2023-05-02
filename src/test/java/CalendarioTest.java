import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;
/**
 * CalendarioTest
 */
public class CalendarioTest {

    @Test
    public void creacionDeCalendarioVacio() {
        //arrange
        Calendario calendario = new Calendario();


        assertNull(calendario.proximaAlarma());
        assertEquals(0, calendario.sonarAlarmas().size());
        calendario.modificarActividadAgregarAlarma(123,PlazoAnterior.DIAANTES);
        assertNull(calendario.proximaAlarma());
        assertEquals(0, calendario.sonarAlarmas().size());
        calendario.modificarActividadNombre(123,"Nombre1");
        calendario.eliminarEvento(123);
    }
    @Test
    public void creacionDeTareaEnCalendario() {
        //arrange
        Calendario calendarioDePrueba = new Calendario();
        //act

        LocalDateTime termina = LocalDateTime.of(2002,1,1,0,0);
        var indice = calendarioDePrueba.crearTarea(termina);
        var tarea = calendarioDePrueba.obtenerTarea(indice);

        assertEquals("",tarea.getTitulo());
        assertEquals("",tarea.getDescripcion());
        assertFalse(tarea.esDiaEntero());
        assertFalse(tarea.estaCompleta());
        assertEquals(termina,tarea.cuandoEmpieza());
        calendarioDePrueba.modificarActividadNombre(indice, "Nombre Tarea");
        assertEquals("Nombre Tarea",tarea.getTitulo());
        calendarioDePrueba.modificarActividadDescripcion(indice, "Descripcion Tarea");
        assertEquals("Descripcion Tarea",tarea.getDescripcion());
        calendarioDePrueba.modificarActividadEsDiaEntero(indice, true);
        assertTrue(tarea.esDiaEntero());
        calendarioDePrueba.modificarTareaCompletarODescompletar(indice);
        assertTrue(tarea.estaCompleta());
        termina = LocalDateTime.of(2002,1,2,0,0);
        calendarioDePrueba.modificarTareaPlazoTemporal(indice,termina);
        assertEquals(termina,tarea.cuandoEmpieza());


    }
    @Test
    public void creacionesDeAlarmasCalendario() {
        Calendario calendarioDePrueba = new Calendario();
        LocalDateTime termina = LocalDateTime.of(2002,1,2,0,0);
        var indice = calendarioDePrueba.crearTarea(termina);
        var tarea = calendarioDePrueba.obtenerTarea(indice);

        calendarioDePrueba.modificarActividadEliminarAlarma(indice,PlazoAnterior.DIAANTES);
        calendarioDePrueba.modificarActividadEliminarAlarma(indice,termina);

        LocalDateTime alarm = LocalDateTime.of(2002,1,1,0,0);
        ArrayList<LocalDateTime> array = new ArrayList<>();
        for (int i = 1; i<23; i++) {
            array.add(LocalDateTime.of(2002,1,1,i,0));
        }
        calendarioDePrueba.modificarActividadAgregarAlarma(indice,PlazoAnterior.HORAANTES);
        calendarioDePrueba.modificarActividadAgregarAlarma(indice,alarm);
        calendarioDePrueba.modificarActividadAgregarAlarma(indice,array);
        for (int i = 0 ; i <= 23; i++) {
            assertEquals(LocalDateTime.of(2002,1,1,i,0),calendarioDePrueba.proximaAlarma());
            var a = calendarioDePrueba.sonarAlarmas();
            assertEquals(tarea,a.remove(0));

        }
    }

    @Test
    public void eventosDadoRangoDeFechas() {
        Calendario calendarioDePrueba = new Calendario();

        LocalDateTime comienzo = LocalDateTime.of(2023, 4, 13, 7, 45, 55);
        LocalDateTime fin = LocalDateTime.of(2023, 4, 13, 10, 45, 55);
        var indice = calendarioDePrueba.crearEvento(comienzo, fin);
        RepeticionInfinita repeticionInfinita = new RepeticionInfinita();
        FrecuenciaDiaria frecuenciaDiaria = new FrecuenciaDiaria(1, repeticionInfinita);
        String descripcion = "Evento en rango";
        calendarioDePrueba.modificarEventoFrecuencia(indice, frecuenciaDiaria);
        calendarioDePrueba.modificarActividadDescripcion(indice, descripcion);

        LocalDateTime comienzoFuera = LocalDateTime.of(2024, 4, 13, 7, 45, 55);
        LocalDateTime finFuera = LocalDateTime.of(2024, 4, 13, 10, 45, 55);
        var indiceFuera = calendarioDePrueba.crearEvento(comienzoFuera, finFuera);
        RepeticionInfinita repeticionInfinitaFuera = new RepeticionInfinita();
        FrecuenciaDiaria frecuenciaDiariaFuera = new FrecuenciaDiaria(1, repeticionInfinitaFuera);
        String descripcionFuera = "Evento fuera de rango";
        calendarioDePrueba.modificarEventoFrecuencia(indiceFuera, frecuenciaDiariaFuera);
        calendarioDePrueba.modificarActividadDescripcion(indiceFuera, descripcionFuera);

        var eventosEnRangoDeLaFecha = calendarioDePrueba.eventosEnRango(LocalDateTime.of(2023, 4, 11, 7, 45, 55), LocalDateTime.of(2023, 4, 15, 7, 45, 55));

        assertEquals(descripcion, eventosEnRangoDeLaFecha.get(0).getDescripcion());
        assertEquals(1, eventosEnRangoDeLaFecha.size()); //Solo  tiene que tener un evento, el otro evento no deberia formar parte



    }

}
