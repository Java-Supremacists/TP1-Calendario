import java.time.LocalDateTime;
import java.time.DayOfWeek;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 * RepeticionCantVecesTest
 */
public class RepeticionCantVecesTest {

    @Test
    public void finDeLaRepeticionFrecuenciaDiaria() {
        LocalDateTime fechaComienzoRepeticion = LocalDateTime.of(2023, 4, 4, 7, 45, 55);
        int maximaCantidadDeRepeticiones = 4;
        int cadaCuantosDias = 2;
        RepeticionCantVeces repeticionCantVecesDePrueba = new RepeticionCantVeces(maximaCantidadDeRepeticiones, cadaCuantosDias, fechaComienzoRepeticion);

        // Esta fecha es la que cae si haces la cuenta manualmente
        // (Sumarle 2 dias 4 veces a fechaComienzoRepeticion)
        LocalDateTime fechaFinRepeticion = LocalDateTime.of(2023, 4, 9, 7, 45, 55);

        assertEquals(true, repeticionCantVecesDePrueba.estaDentroDeRepeticiones(fechaFinRepeticion));
    }

    @Test
    public void finDeLaRepeticionFrecuenciaDiariaMismoDia() {
        LocalDateTime fechaComienzoRepeticion = LocalDateTime.of(2023, 4, 4, 7, 45, 55);
        int maximaCantidadDeRepeticiones = 4;
        int cadaCuantosDias = 2;
        RepeticionCantVeces repeticionCantVecesDePrueba = new RepeticionCantVeces(maximaCantidadDeRepeticiones, cadaCuantosDias, fechaComienzoRepeticion);

        // (Sumarle 2 dias 4 veces a fechaComienzoRepeticion)
        LocalDateTime fechaFinRepeticion = LocalDateTime.of(2023, 4, 10, 7, 45, 55);

        //assert
        assertEquals(true, repeticionCantVecesDePrueba.estaDentroDeRepeticiones(fechaFinRepeticion));
    }

    @Test
    public void finDeLaRepeticionPorFechaArrayDaysOfWeek() {
        LocalDateTime fechaComienzoRepeticion = LocalDateTime.of(2023, 4, 4, 7, 45, 55);
        int maximaCantidadDeRepeticiones = 4;
        DayOfWeek[] diasDeLaSemana = {DayOfWeek.TUESDAY, DayOfWeek.THURSDAY};
        RepeticionCantVeces repeticionCantVecesDePrueba = new RepeticionCantVeces(maximaCantidadDeRepeticiones, fechaComienzoRepeticion, diasDeLaSemana);

        // Esta fecha es la que cae si haces la cuenta manualmente
        // (Martes 4, Jueves 6, Martes 11 y Jueves 13)
        LocalDateTime fechaFinRepeticion = LocalDateTime.of(2023, 4, 13, 7, 45, 55);

        assertEquals(true, repeticionCantVecesDePrueba.estaDentroDeRepeticiones(fechaFinRepeticion));
    }

}
