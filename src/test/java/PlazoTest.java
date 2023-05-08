import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.*;

public class PlazoTest {

    @Test
    public void elHorarioEstablecido() {
        Duration d1 = Duration.ofDays(1L);
        Duration d2 = Duration.ofHours(1L);
        Duration d3 = Duration.ofMinutes(30L);
        Duration d4 = Duration.ofMinutes(15L);
        Duration d5 = Duration.ofMinutes(10L);
        Duration d6 = Duration.ofMinutes(5L);
        Duration d10 = Duration.ofMinutes(-30L);
        Duration d9 = Duration.ofMinutes(-15L);
        Duration d8 = Duration.ofMinutes(-10L);
        Duration d7 = Duration.ofMinutes(-5L);

        assertEquals(d1, Plazo.DIAANTES.elHorarioEstablecido());
        assertEquals(d2, Plazo.HORAANTES.elHorarioEstablecido());
        assertEquals(d3, Plazo.MEDIAHORAANTES.elHorarioEstablecido());
        assertEquals(d4, Plazo.QUINCEMINUTOSANTES.elHorarioEstablecido());
        assertEquals(d5, Plazo.DIEZMINUTOSANTES.elHorarioEstablecido());
        assertEquals(d6, Plazo.CINCOMINUTOSANTES.elHorarioEstablecido());
        assertEquals(d7, Plazo.CINCOMINUTOSDESPUES.elHorarioEstablecido());
        assertEquals(d8, Plazo.DIEZMINUTOSDESPUES.elHorarioEstablecido());
        assertEquals(d9, Plazo.QUINCEMINUTODESPUES.elHorarioEstablecido());
        assertEquals(d10, Plazo.MEDIAHORADESPUES.elHorarioEstablecido());
    }

    @Test
    public void compararHorariosDescriptosTest() {
        assertEquals(Plazo.DIAANTES, Plazo.compararHorariosDescriptos("1 Dia Antes"));
        assertEquals(Plazo.HORAANTES, Plazo.compararHorariosDescriptos("1 Hora Antes"));
        assertEquals(Plazo.MEDIAHORAANTES, Plazo.compararHorariosDescriptos("30 Minutos Antes"));
        assertEquals(Plazo.QUINCEMINUTOSANTES, Plazo.compararHorariosDescriptos("15 Minutos Antes"));
        assertEquals(Plazo.DIEZMINUTOSANTES, Plazo.compararHorariosDescriptos("10 Minutos Antes"));
        assertEquals(Plazo.CINCOMINUTOSANTES, Plazo.compararHorariosDescriptos("5 Minutos Antes"));
        assertEquals(Plazo.CINCOMINUTOSDESPUES, Plazo.compararHorariosDescriptos("5 Minutos Despues"));
        assertEquals(Plazo.DIEZMINUTOSDESPUES, Plazo.compararHorariosDescriptos("10 Minutos Despues"));
        assertEquals(Plazo.QUINCEMINUTODESPUES, Plazo.compararHorariosDescriptos("15 Minutos Despues"));
        assertEquals(Plazo.MEDIAHORADESPUES, Plazo.compararHorariosDescriptos("30 Minutos Despues"));
    }

}