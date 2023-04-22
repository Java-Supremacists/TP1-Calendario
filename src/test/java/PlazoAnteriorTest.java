import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.*;

public class PlazoAnteriorTest {

    @Test
    public void elHorarioEstablecido() {
        Duration d1 = Duration.ofDays(1L);
        Duration d2 = Duration.ofHours(1L);
        Duration d3 = Duration.ofMinutes(30L);
        Duration d4 = Duration.ofMinutes(15L);
        Duration d5 = Duration.ofMinutes(10L);
        Duration d6 = Duration.ofMinutes(5L);

        assertEquals(d1,PlazoAnterior.DIAANTES.elHorarioEstablecido());
        assertEquals(d2,PlazoAnterior.HORAANTES.elHorarioEstablecido());
        assertEquals(d3,PlazoAnterior.MEDIAHORAANTES.elHorarioEstablecido());
        assertEquals(d4,PlazoAnterior.QUINCEMINUTOSANTES.elHorarioEstablecido());
        assertEquals(d5,PlazoAnterior.DIEZMINUTOSANTES.elHorarioEstablecido());
        assertEquals(d6,PlazoAnterior.CINCOMINUTOSANTES.elHorarioEstablecido());
    }

    @Test
    public void compararHorariosDescriptosTest() {
        assertEquals(PlazoAnterior.DIAANTES,PlazoAnterior.compararHorariosDescriptos("1 Dia Antes"));
        assertEquals(PlazoAnterior.HORAANTES,PlazoAnterior.compararHorariosDescriptos("1 Hora Antes"));
        assertEquals(PlazoAnterior.MEDIAHORAANTES,PlazoAnterior.compararHorariosDescriptos("30 Minutos Antes"));
        assertEquals(PlazoAnterior.QUINCEMINUTOSANTES,PlazoAnterior.compararHorariosDescriptos("15 Minutos Antes"));
        assertEquals(PlazoAnterior.DIEZMINUTOSANTES,PlazoAnterior.compararHorariosDescriptos("10 Minutos Antes"));
        assertEquals(PlazoAnterior.CINCOMINUTOSANTES,PlazoAnterior.compararHorariosDescriptos("5 Minutos Antes"));
    }

}