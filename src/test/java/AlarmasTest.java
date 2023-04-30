import org.junit.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class AlarmasTest {
    @Test
    public void CrearAlarmaVacia() {
        Alarmas a1 = new Alarmas();
        Alarmas a2 = new Alarmas(true);

        assertFalse(a1.existeAlarma(null));
        assertFalse(a1.repiteLasAlarmas());
        assertFalse(a1.quedanAlarmas());
        assertNull(a1.primerAlarmaASonar());
        assertEquals(0,a1.size());

        assertFalse(a2.existeAlarma(null));
        assertTrue(a2.repiteLasAlarmas());
        assertFalse(a2.quedanAlarmas());
        assertNull(a2.primerAlarmaASonar());
        assertEquals(0,a2.size());
    }
    @Test
    public void AlarmaConNull() {
        Alarmas a1 = new Alarmas();
        Alarmas a2 = new Alarmas(true);

        a1.agregarAlarma((LocalDateTime) null);
        assertFalse(a1.existeAlarma(null));
        assertFalse(a1.quedanAlarmas());
        assertNull(a1.primerAlarmaASonar());
        assertFalse(a1.repiteLasAlarmas());
        a1.eliminarAlarma(null);
        a1.sonarAlarma();

        a2.agregarAlarma((LocalDateTime) null);
        assertTrue(a2.repiteLasAlarmas());
        assertFalse(a2.existeAlarma(null));
        assertFalse(a2.quedanAlarmas());
        assertNull(a2.primerAlarmaASonar());
        a2.eliminarAlarma(null);
        a2.sonarAlarma();
    }
    @Test
    public void quedanAlarmas() {
        Alarmas a1 = new Alarmas();
        LocalDateTime agregar;
        ArrayList<LocalDateTime> array = new ArrayList<>();

        assertFalse(a1.quedanAlarmas());
        a1.agregarAlarma(array);
        assertFalse(a1.quedanAlarmas());
        for (int i = 0 ; i < 1000 ; i++ ) {
            agregar = LocalDateTime.of(3022-i,4,24,23,59);
            a1.agregarAlarma(agregar);
            assertTrue(a1.quedanAlarmas());
        }
        for (int j = 0 ; j < 1000 ; j++ ) {
            agregar = LocalDateTime.of(2023+j,4,24,23,59);
            assertTrue(a1.quedanAlarmas());
            a1.eliminarAlarma(agregar);
        }
    }
    @Test
    public void primerAlarmaYSonarAlarma() {
        Alarmas a1 = new Alarmas();
        LocalDateTime agregar;
        ArrayList<LocalDateTime> array = new ArrayList<>();
        for (int i = 12 ; i > 0 ; i-- ) {
            agregar = LocalDateTime.of(i,i,i,i,i);
            array.add(agregar);
        }
        a1.agregarAlarma(array);
        for (int i = 1 ; i < 13 ; i++ ) {
            agregar = LocalDateTime.of(i,i,i,i,i);
            assertEquals(agregar,a1.primerAlarmaASonar());
            a1.sonarAlarma();
        }
    }
    @Test
    public void AgregarUnaAlarmaPruebaCompleta() {
        Alarmas a1 = new Alarmas();
        Alarmas a2 = new Alarmas(true);
        LocalDateTime agregar = LocalDateTime.of(2023,4,24,23,59);

        a1.agregarAlarma(agregar);
        assertFalse(a1.repiteLasAlarmas());
        assertTrue(a1.existeAlarma(agregar));
        assertTrue(a1.quedanAlarmas());
        assertEquals(agregar,a1.primerAlarmaASonar());
        a1.eliminarAlarma(agregar);
        assertFalse(a1.existeAlarma(agregar));
        assertFalse(a1.quedanAlarmas());
        assertNull(a1.primerAlarmaASonar());
        a1.agregarAlarma(agregar);
        a1.sonarAlarma();
        assertFalse(a1.existeAlarma(agregar));
        assertFalse(a1.quedanAlarmas());
        assertNull(a1.primerAlarmaASonar());

        a2.agregarAlarma(agregar);
        assertTrue(a2.repiteLasAlarmas());
        assertTrue(a2.existeAlarma(agregar));
        assertTrue(a2.quedanAlarmas());
        assertEquals(agregar,a2.primerAlarmaASonar());
        a2.eliminarAlarma(agregar);
        assertFalse(a2.existeAlarma(agregar));
        assertFalse(a2.quedanAlarmas());
        assertNull(a2.primerAlarmaASonar());
        a2.agregarAlarma(agregar);
        a2.sonarAlarma();
        assertFalse(a2.existeAlarma(agregar));
        assertFalse(a2.quedanAlarmas());
        assertNull(a2.primerAlarmaASonar());
    }
    @Test
    public void AgregarArrayVacio() {
        Alarmas a1 = new Alarmas();
        Alarmas a2 = new Alarmas(true);
        ArrayList<LocalDateTime> array = new ArrayList<>();

        a1.agregarAlarma(array);
        a2.agregarAlarma(array);
        assertEquals(0,a1.size());
        assertEquals(0,a2.size());
        assertFalse(a1.existeAlarma(null));
        assertFalse(a2.existeAlarma(null));
        assertFalse(a1.quedanAlarmas());
        assertFalse(a2.quedanAlarmas());
        assertNull(a1.primerAlarmaASonar());
        assertNull(a2.primerAlarmaASonar());
    }
    @Test
    public void AgregarArrayAlarmasCompleto() {
        Alarmas a1 = new Alarmas();
        Alarmas a2 = new Alarmas(true);
        ArrayList<LocalDateTime> array = new ArrayList<>();
        LocalDateTime agregar;

        for (int i = 0 ; i < 1000 ; i++ ) {
            agregar = LocalDateTime.of(2023+i,4,24,23,59);
            array.add(agregar);
        }
        a1.agregarAlarma(array);
        a2.agregarAlarma(array);
        assertEquals(1000,a1.size());
        assertEquals(1000,a2.size());
        assertTrue(a1.existeAlarma(LocalDateTime.of(2023,4,24,23,59)));
        assertTrue(a2.existeAlarma(LocalDateTime.of(2023,4,24,23,59)));
        assertTrue(a1.quedanAlarmas());
        assertTrue(a2.quedanAlarmas());
        assertEquals(LocalDateTime.of(2023,4,24,23,59),a1.primerAlarmaASonar());
        assertEquals(LocalDateTime.of(2023,4,24,23,59),a2.primerAlarmaASonar());
    }
    @Test
    public void AgregarAlarmasIdenticas() {
        Alarmas a1 = new Alarmas();
        Alarmas a2 = new Alarmas(true);
        LocalDateTime agregar = LocalDateTime.of(2023,4,24,23,59);
        for (int i = 0 ; i < 100 ; i++ ) {
            a1.agregarAlarma(agregar);
            assertEquals(1,a1.size());

            a2.agregarAlarma(agregar);
            assertEquals(1,a2.size());
        }
    }
    @Test
    public void AgregarMuchasAlarmasPruebaCompleta() {
        Alarmas a1 = new Alarmas();
        Alarmas a2 = new Alarmas(true);
        LocalDateTime agregar;
        for (int i = 0 ; i < 1000 ; i++ ) {
            agregar = LocalDateTime.of(3022-i,4,24,23,59);

            assertEquals(i,a1.size());
            a1.agregarAlarma(agregar);
            assertEquals(i+1,a1.size());
            assertTrue(a1.existeAlarma(agregar));
            assertTrue(a1.quedanAlarmas());
            assertEquals(agregar,a1.primerAlarmaASonar());

            a2.agregarAlarma(agregar);
            assertTrue(a2.existeAlarma(agregar));
            assertTrue(a2.quedanAlarmas());
            assertEquals(agregar,a2.primerAlarmaASonar());
        }
        for (int j = 0 ; j < 1000 ; j++ ) {
            agregar = LocalDateTime.of(2023+j,4,24,23,59);

            assertEquals(agregar,a1.primerAlarmaASonar());
            assertTrue(a1.existeAlarma(agregar));
            assertEquals(1000-j,a1.size());
            a1.sonarAlarma();
            assertEquals(999-j,a1.size());
            assertFalse(a1.existeAlarma(agregar));
            a1.agregarAlarma(agregar);
            a1.eliminarAlarma(agregar);
            assertEquals(999-j,a1.size());
            assertFalse(a1.existeAlarma(agregar));

            assertEquals(agregar,a2.primerAlarmaASonar());
            assertTrue(a2.existeAlarma(agregar));
            assertEquals(1000-j,a2.size());
            a2.sonarAlarma();
            assertEquals(999-j,a2.size());
            assertFalse(a2.existeAlarma(agregar));
            a2.agregarAlarma(agregar);
            a2.eliminarAlarma(agregar);
            assertEquals(999-j,a2.size());
            assertFalse(a2.existeAlarma(agregar));

        }
    }
    @Test
    public void actualizarAlarmaTest() {
        Alarmas a1 = new Alarmas(true);
        LocalDateTime diaDelEvento = LocalDateTime.of(2023,4,4,23,1);
        LocalDateTime agregar;

        a1.actualizarAlarmas(10L);//no hace nada porque no hay alarmas

        for (int i = 0; i<23; i++) {
            agregar  = LocalDateTime.of(2023,4,4,1+i,0);
            a1.agregarAlarma(agregar);
        }

        a1.actualizarAlarmas(10L);//no hace nada porque no paso el evento(No sonaron todas las alarmas)

        for (int i = 0; i<23; i++) {
            agregar  = LocalDateTime.of(2023,4,4,1+i,0);
            LocalDateTime primeraAlarma = a1.primerAlarmaASonar();
            a1.sonarAlarma();
            assertEquals(agregar,primeraAlarma);
            assertTrue(diaDelEvento.isAfter(primeraAlarma));
        }

        //Hora de actualizarlas a una fecha (Ya no hay más alarmas y se terminó el evento)

        diaDelEvento = LocalDateTime.of(2023,4,8,23,1);//el evento se repite el día 8 ahora
        a1.actualizarAlarmas(4L);

        for (int i = 0; i<23; i++) {
            agregar  = LocalDateTime.of(2023,4,8,1+i,0);
            LocalDateTime primeraAlarma = a1.primerAlarmaASonar();
            a1.sonarAlarma();
            assertEquals(agregar,primeraAlarma);
            assertTrue(diaDelEvento.isAfter(primeraAlarma));
        }

        //Hora de actualizarlas a una fecha (Ya no hay más alarmas y se terminó el evento)
        diaDelEvento = LocalDateTime.of(2023,5,8,23,1);//el evento se repite el día 8 pero dentro de un mes ahora
        a1.actualizarAlarmas(30L);

        for (int i = 0; i<23; i++) {
            agregar  = LocalDateTime.of(2023,5,8,1+i,0);
            LocalDateTime primeraAlarma = a1.primerAlarmaASonar();
            a1.sonarAlarma();
            assertEquals(agregar,primeraAlarma);
            assertTrue(diaDelEvento.isAfter(primeraAlarma));
        }

        //Hora de actualizarlas a una fecha (Ya no hay más alarmas y se terminó el evento)
        diaDelEvento = LocalDateTime.of(2024,5,8,23,1);//el evento se repite el día 8 pero dentro de un AÑO ahora
        a1.actualizarAlarmas(366L);//esta exactitud de cuando cae la calcula un módulo exportado

        for (int i = 0; i<23; i++) {
            agregar  = LocalDateTime.of(2024,5,8,1+i,0);
            LocalDateTime primeraAlarma = a1.primerAlarmaASonar();
            a1.sonarAlarma();
            assertEquals(agregar,primeraAlarma);
            assertTrue(diaDelEvento.isAfter(primeraAlarma));
        }
    }

}