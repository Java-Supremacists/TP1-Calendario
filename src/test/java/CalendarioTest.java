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

}
