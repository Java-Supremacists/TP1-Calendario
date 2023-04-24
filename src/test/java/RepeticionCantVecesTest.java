import java.time.LocalDateTime;
import java.time.DayOfWeek;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 * RepeticionCantVecesTest
 */
public class RepeticionCantVecesTest {

    @Test
    public void finDeLaRepeticionFrecuenciaDiaria(){
	//arrange
	LocalDateTime fechaComienzoRepeticion = LocalDateTime.of(2023, 4, 4, 7, 45, 55);
	int maximaCantidadDeRepeticiones = 4;
	int cadaCuantosDias = 2;
	RepeticionCantVeces repeticionCantVecesDePrueba = new RepeticionCantVeces(maximaCantidadDeRepeticiones, cadaCuantosDias, fechaComienzoRepeticion);

	// Esta fecha es la que cae si haces la cuenta manualmente
	// (Sumarle 2 dias 4 veces a fechaComienzoRepeticion)
	// LocalDateTime fechaFinRepeticion = LocalDateTime.of(2023, 4, 10, 7, 45, 55);
	LocalDateTime fechaFinRepeticion = LocalDateTime.of(2023, 4, 9, 7, 45, 55);

	//assert
	assertEquals(true, repeticionCantVecesDePrueba.estaDentroDeRepeticiones(fechaFinRepeticion));
    }

    @Test
    public void finDeLaRepeticionFrecuenciaDiariaMismoDia(){
	//arrange
	LocalDateTime fechaComienzoRepeticion = LocalDateTime.of(2023, 4, 4, 7, 45, 55);
	int maximaCantidadDeRepeticiones = 4;
	int cadaCuantosDias = 2;
	RepeticionCantVeces repeticionCantVecesDePrueba = new RepeticionCantVeces(maximaCantidadDeRepeticiones, cadaCuantosDias, fechaComienzoRepeticion);

	// Esta fecha es la que cae si haces la cuenta manualmente
	// (Sumarle 2 dias 4 veces a fechaComienzoRepeticion)
	LocalDateTime fechaFinRepeticion = LocalDateTime.of(2023, 4, 10, 7, 45, 55);

	//assert
	assertEquals(true, repeticionCantVecesDePrueba.estaDentroDeRepeticiones(fechaFinRepeticion));
    }

    @Test
    public void finDeLaRepeticionPorFechaArrayDaysOfWeek(){
	//arrange
	LocalDateTime fechaComienzoRepeticion = LocalDateTime.of(2023, 4, 4, 7, 45, 55);
	int maximaCantidadDeRepeticiones = 4;
	DayOfWeek[] diasDeLaSemana = {DayOfWeek.TUESDAY, DayOfWeek.THURSDAY}; 
	RepeticionCantVeces repeticionCantVecesDePrueba = new RepeticionCantVeces(maximaCantidadDeRepeticiones, fechaComienzoRepeticion, diasDeLaSemana);

	// Esta fecha es la que cae si haces la cuenta manualmente
	// (Martes 4, Jueves 6, Martes 11 y Jueves 13)
	LocalDateTime fechaFinRepeticion = LocalDateTime.of(2023, 4, 13, 7, 45, 55);

	//assert
	assertEquals(true, repeticionCantVecesDePrueba.estaDentroDeRepeticiones(fechaFinRepeticion));
    }

    //@Test
    //public void finDeLaRepeticionPorFechaArrayDaysOfWeekMismaSemana(){
	////arrange
	//LocalDateTime fechaComienzoRepeticion = LocalDateTime.of(2023, 4, 4, 7, 45, 55);
	//int maximaCantidadDeRepeticiones = 2;
	//DayOfWeek[] diasDeLaSemana = {DayOfWeek.TUESDAY, DayOfWeek.THURSDAY}; 
	//RepeticionCantVeces repeticionCantVecesDePrueba = new RepeticionCantVeces(maximaCantidadDeRepeticiones);

	//// Esta fecha es la que cae si haces la cuenta manualmente
	//// (Martes 4, Jueves 6)
	//LocalDateTime fechaFinRepeticion = LocalDateTime.of(2023, 4, 6, 7, 45, 55);

	////assert
	//assertEquals(fechaFinRepeticion, repeticionCantVecesDePrueba.finDeLaRepeticion(fechaComienzoRepeticion, diasDeLaSemana));
    //}
}

    //@Test
    //public void finDeLaRepeticionPorFechaArrayDaysOfWeekCantidadImparDeDias(){
	////arrange
	//LocalDateTime fechaComienzoRepeticion = LocalDateTime.of(2023, 4, 4, 7, 45, 55);
	//int maximaCantidadDeRepeticiones = 7;
	//DayOfWeek[] diasDeLaSemana = {DayOfWeek.TUESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY}; 
	//RepeticionCantVeces repeticionCantVecesDePrueba = new RepeticionCantVeces(maximaCantidadDeRepeticiones);

	//// Esta fecha es la que cae si haces la cuenta manualmente
	//// (Martes 4, Jueves 6, Viernes 7, Martes 11, Jueves 13, Viernes 14, Martes 18)
	//LocalDateTime fechaFinRepeticion = LocalDateTime.of(2023, 4, 18, 7, 45, 55);

	////assert
	//assertEquals(fechaFinRepeticion, repeticionCantVecesDePrueba.finDeLaRepeticion(fechaComienzoRepeticion, diasDeLaSemana));
    //}
//}
