import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class AlarmasTest {

    @Test
    public void CrearAlarmaVacia() {
        Alarmas a1 = new Alarmas();
        Alarmas a2 = new Alarmas(true);

        assertFalse(a1.existeAlarma(null));
        assertFalse(a1.quedanAlarmas());
        assertNull(a1.primerAlarmaASonar());
        assertEquals(0,a1.size());

        assertFalse(a2.existeAlarma(null));
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
        a1.eliminarAlarma(null);
        a1.sonarAlarma();

        a2.agregarAlarma((LocalDateTime) null);
        assertFalse(a2.existeAlarma(null));
        assertFalse(a2.quedanAlarmas());
        assertNull(a2.primerAlarmaASonar());
        a2.eliminarAlarma(null);
        a2.sonarAlarma();
    }

    @Test
    public void agregarUnaAlarma() {
        Alarmas a1 = new Alarmas();
        Alarmas a2 = new Alarmas(true);
        LocalDateTime agregar = LocalDateTime.of(2023,4,24,23,59);

        a1.agregarAlarma(agregar);
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
    public void agregarAlarmasIdenticas() {
        Alarmas a1 = new Alarmas();
        Alarmas a2 = new Alarmas(true);



    }
    @Test
    public void agregarMuchasAlarmas() {
        Alarmas a1 = new Alarmas();
        Alarmas a2 = new Alarmas(true);
        LocalDateTime agregar;
        for (int i = 0 ; i < 1000 ; i++ ){
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
        for (int j = 0 ; j < 1000 ; j++ ){
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
/*
*
    @Test
    public void quedanAlarmas() {
    }

    @Test
    public void existeAlarma() {
    }

    @Test
    public void eliminarAlarma() {
    }

    @Test
    public void primerAlarmaASonar() {
    }

    @Test
    public void sonarAlarma() {
    }

 */
}