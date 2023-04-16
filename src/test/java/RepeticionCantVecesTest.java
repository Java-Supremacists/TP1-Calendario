import java.time.LocalDateTime;
import java.time.DayOfWeek;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 * RepeticionCantVecesTest
 */
public class RepeticionCantVecesTest {

    @Test
    public void finDeLaRepeticionPorFechaInt(){
	//arrange
	LocalDateTime fechaComienzoRepeticion = LocalDateTime.of(2023, 4, 4, 7, 45, 55);
	int maximaCantidadDeRepeticiones = 4;
	int cadaCuantosDias = 2;
	RepeticionCantVeces repeticionCantVecesDePrueba = new RepeticionCantVeces(maximaCantidadDeRepeticiones);

	// Esta fecha es la que cae si haces la cuenta manualmente
	// (Sumarle 2 dias 4 veces a fechaComienzoRepeticion)
	LocalDateTime fechaFinRepeticion = LocalDateTime.of(2023, 4, 10, 7, 45, 55);

	//assert
	assertEquals(fechaFinRepeticion, repeticionCantVecesDePrueba.finDeLaRepeticion(fechaComienzoRepeticion, cadaCuantosDias));
    }

    @Test
    public void finDeLaRepeticionPorFechaArrayDaysOfWeek(){
	//arrange
	LocalDateTime fechaComienzoRepeticion = LocalDateTime.of(2023, 4, 4, 7, 45, 55);
	int maximaCantidadDeRepeticiones = 2;
	DayOfWeek[] diasDeLaSemana = {DayOfWeek.TUESDAY, DayOfWeek.THURSDAY}; 
	RepeticionCantVeces repeticionCantVecesDePrueba = new RepeticionCantVeces(maximaCantidadDeRepeticiones);

	// Esta fecha es la que cae si haces la cuenta manualmente
	// (Sumarle 2 dias 4 veces a fechaComienzoRepeticion)
	LocalDateTime fechaFinRepeticion = LocalDateTime.of(2023, 4, 13, 7, 45, 55);

	//assert
	// assertEquals(fechaFinRepeticion, repeticionCantVecesDePrueba.finDeLaRepeticion(fechaComienzoRepeticion, diasDeLaSemana));
    }
}
