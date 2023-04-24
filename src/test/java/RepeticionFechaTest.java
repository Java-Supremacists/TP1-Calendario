import java.time.LocalDateTime;
import java.time.DayOfWeek;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 * RepeticionFechaTest
 */
public class RepeticionFechaTest {

    @Test
    public void finDeLaRepeticionPorFechaAnterior(){
	//arrange
	LocalDateTime fechaFinRepeticion = LocalDateTime.of(2023, 4, 22, 7, 45, 55);
	LocalDateTime fechaComienzoRepeticion = LocalDateTime.of(2023, 2, 22, 7, 45, 55);
	LocalDateTime fechaAnterior = LocalDateTime.of(2023, 2, 20, 7, 45, 55);

	RepeticionFecha repeticionFechaDePrueba = new RepeticionFecha(fechaFinRepeticion);

	//
	//assert
	assertEquals(true, repeticionFechaDePrueba.estaDentroDeRepeticiones(fechaFinRepeticion));
    }

    @Test
    public void finDeLaRepeticionPorFechaMismoDia(){
	//arrange
	LocalDateTime fechaFinRepeticion = LocalDateTime.of(2023, 4, 22, 7, 45, 55);
	LocalDateTime fechaComienzoRepeticion = LocalDateTime.of(2023, 2, 22, 7, 45, 55);

	RepeticionFecha repeticionFechaDePrueba = new RepeticionFecha(fechaFinRepeticion);

	//
	//assert
	assertEquals(true, repeticionFechaDePrueba.estaDentroDeRepeticiones(fechaFinRepeticion));
    }

    //@Test
    //public void finDeLaRepeticionPorFechaArrayDaysOfWeek(){
	////arrange
	//LocalDateTime fechaFinRepeticion = LocalDateTime.of(2023, 4, 22, 7, 45, 55);
	//LocalDateTime fechaComienzoRepeticion = LocalDateTime.of(2023, 2, 22, 7, 45, 55);
	//DayOfWeek[] diasDeLaSemana = {DayOfWeek.MONDAY, DayOfWeek.TUESDAY}; 

	//RepeticionFecha repeticionFechaDePrueba = new RepeticionFecha(fechaFinRepeticion);

	////assert
	//assertEquals(fechaFinRepeticion, repeticionFechaDePrueba.finDeLaRepeticion(fechaComienzoRepeticion, diasDeLaSemana));
    //}
    @Test
    public void aseertTrue() {
        assertTrue(true);
    }
}
