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

	RepeticionFecha repeticionFechaDePrueba = new RepeticionFecha(fechaFinRepeticion);

	assertEquals(true, repeticionFechaDePrueba.estaDentroDeRepeticiones(fechaFinRepeticion));
    }

    @Test
    public void finDeLaRepeticionPorFechaMismoDia(){
	LocalDateTime fechaFinRepeticion = LocalDateTime.of(2023, 4, 22, 7, 45, 55);

	RepeticionFecha repeticionFechaDePrueba = new RepeticionFecha(fechaFinRepeticion);

	assertEquals(true, repeticionFechaDePrueba.estaDentroDeRepeticiones(fechaFinRepeticion));
    }

}
