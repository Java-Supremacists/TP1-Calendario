import java.time.DayOfWeek;
import java.time.LocalDateTime;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * FrecuenciaSemanalTest
 */
public class FrecuenciaSemanalTest {

    @Test
    public void calcularProximoEventoMasCercanoAFecha(){
	//arrange
	DayOfWeek[] diasDeLaSemana = {DayOfWeek.MONDAY, DayOfWeek.THURSDAY};

	LocalDateTime fechaComienzo = LocalDateTime.of(2023, 4, 3, 7, 45, 55);
	LocalDateTime fechaFinal = LocalDateTime.of(2023, 4, 11, 7, 45, 55);
	RepeticionInfinita repeticionInfinita = new RepeticionInfinita();

	FrecuenciaSemanal frecuenciaSemanal = new FrecuenciaSemanal(diasDeLaSemana, repeticionInfinita);
	// Esta fecha es la que cae si haces la cuenta manualmente
	// (Sumarle 2 dias 4 veces a fechaComienzoRepeticion)
	// LocalDateTime fechaFinRepeticion = LocalDateTime.of(2023, 4, 10, 7, 45, 55);
	LocalDateTime fechaMasCercana = LocalDateTime.of(2023, 4, 13, 7, 45, 55);

	//assert
	assertEquals(fechaMasCercana, frecuenciaSemanal.proximoEventoMasCercanoAFechaEspecifica(fechaComienzo, fechaFinal));
    }

    @Test
    public void calcularProximoEventoMasCercanoAFechaMismoDia(){
	//arrange
	DayOfWeek[] diasDeLaSemana = {DayOfWeek.MONDAY, DayOfWeek.THURSDAY};

	LocalDateTime fechaComienzo = LocalDateTime.of(2023, 4, 3, 7, 45, 55);
	LocalDateTime fechaFinal = LocalDateTime.of(2023, 4, 10, 7, 45, 55);
	RepeticionInfinita repeticionInfinita = new RepeticionInfinita();

	FrecuenciaSemanal frecuenciaSemanal = new FrecuenciaSemanal(diasDeLaSemana, repeticionInfinita);
	// Esta fecha es la que cae si haces la cuenta manualmente
	// (Sumarle 2 dias 4 veces a fechaComienzoRepeticion)
	// LocalDateTime fechaFinRepeticion = LocalDateTime.of(2023, 4, 10, 7, 45, 55);
	LocalDateTime fechaMasCercana = LocalDateTime.of(2023, 4, 10, 7, 45, 55);

	//assert
	assertEquals(fechaMasCercana, frecuenciaSemanal.proximoEventoMasCercanoAFechaEspecifica(fechaComienzo, fechaFinal));
    }

	
}
