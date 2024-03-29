import java.time.DayOfWeek;
import java.time.LocalDateTime;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * FrecuenciaSemanalTest
 */
public class FrecuenciaSemanalTest {

    @Test
    public void calcularProximoEventoMasCercanoAFecha() {
        DayOfWeek[] diasDeLaSemana = {DayOfWeek.MONDAY, DayOfWeek.THURSDAY};

        LocalDateTime fechaComienzo = LocalDateTime.of(2023, 4, 3, 7, 45, 55);
        LocalDateTime fechaFinal = LocalDateTime.of(2023, 4, 11, 7, 45, 55);
        RepeticionInfinita repeticionInfinita = new RepeticionInfinita();

        FrecuenciaSemanal frecuenciaSemanal = new FrecuenciaSemanal(diasDeLaSemana, repeticionInfinita);
        // Esta fecha es la que cae si haces la cuenta manualmente
        LocalDateTime fechaMasCercana = LocalDateTime.of(2023, 4, 13, 7, 45, 55);

        assertEquals(fechaMasCercana, frecuenciaSemanal.proximoEventoMasCercanoAFechaEspecifica(fechaComienzo, fechaFinal));
    }

    @Test
    public void calcularProximoEventoMasCercanoAFechaMismoDia() {
        DayOfWeek[] diasDeLaSemana = {DayOfWeek.MONDAY, DayOfWeek.THURSDAY};

        LocalDateTime fechaComienzo = LocalDateTime.of(2023, 4, 3, 7, 45, 55);
        LocalDateTime fechaFinal = LocalDateTime.of(2023, 4, 10, 7, 45, 55);
        RepeticionInfinita repeticionInfinita = new RepeticionInfinita();

        FrecuenciaSemanal frecuenciaSemanal = new FrecuenciaSemanal(diasDeLaSemana, repeticionInfinita);
        LocalDateTime fechaMasCercana = LocalDateTime.of(2023, 4, 10, 7, 45, 55);

        assertEquals(fechaMasCercana, frecuenciaSemanal.proximoEventoMasCercanoAFechaEspecifica(fechaComienzo, fechaFinal));
    }

    @Test
    public void dadoComienzoCaeElDia() {
        DayOfWeek[] diasDeLaSemana = {DayOfWeek.MONDAY, DayOfWeek.THURSDAY};

        LocalDateTime fechaComienzo = LocalDateTime.of(2023, 3, 4, 7, 45, 55);
        LocalDateTime fechaATestearFalso = LocalDateTime.of(2023, 4, 11, 7, 45, 55);
        LocalDateTime fechaATestearPositivo = LocalDateTime.of(2023, 4, 10, 7, 45, 55);
        LocalDateTime fechaATestearFalso2 = LocalDateTime.of(2023, 4, 14, 7, 45, 55);
        LocalDateTime fechaATestearPositivo2 = LocalDateTime.of(2023, 4, 13, 7, 45, 55);
        RepeticionInfinita repeticionInfinita = new RepeticionInfinita();
        FrecuenciaSemanal frecuenciaSemanal = new FrecuenciaSemanal(diasDeLaSemana, repeticionInfinita);

        assertEquals(true, frecuenciaSemanal.dadoComienzoCaeElDia(fechaComienzo, fechaATestearPositivo));
        assertEquals(false, frecuenciaSemanal.dadoComienzoCaeElDia(fechaComienzo, fechaATestearFalso));
        assertEquals(true, frecuenciaSemanal.dadoComienzoCaeElDia(fechaComienzo, fechaATestearPositivo2));
        assertEquals(false, frecuenciaSemanal.dadoComienzoCaeElDia(fechaComienzo, fechaATestearFalso2));
    }


}
