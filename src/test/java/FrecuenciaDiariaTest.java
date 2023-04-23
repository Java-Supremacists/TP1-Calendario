import java.time.LocalDateTime;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * FrecuenciaDiariaTest
 */
public class FrecuenciaDiariaTest {

    @Test
    public void calcularProximoEventoMasCercanoAFecha(){
	//arrange
	int cadaCuantosDias = 2;

	LocalDateTime fechaComienzo = LocalDateTime.of(2023, 4, 4, 7, 45, 55);
	LocalDateTime fechaFinal = LocalDateTime.of(2023, 4, 13, 7, 45, 55);
	RepeticionInfinita repeticionInfinita = new RepeticionInfinita();

	FrecuenciaDiaria frecuenciaDiaria = new FrecuenciaDiaria(cadaCuantosDias, repeticionInfinita);
	// Esta fecha es la que cae si haces la cuenta manualmente
	// (Sumarle 2 dias 4 veces a fechaComienzoRepeticion)
	// LocalDateTime fechaFinRepeticion = LocalDateTime.of(2023, 4, 10, 7, 45, 55);
	LocalDateTime fechaMasCercana = LocalDateTime.of(2023, 4, 14, 7, 45, 55);

	//assert
	assertEquals(fechaMasCercana, frecuenciaDiaria.proximoEventoMasCercanoAFechaEspecifica(fechaComienzo, fechaFinal));
    }

    @Test
    public void calcularProximoEventoMasCercanoAFechaMismoDia(){
	//arrange
	int cadaCuantosDias = 2;

	LocalDateTime fechaComienzo = LocalDateTime.of(2023, 4, 5, 7, 45, 55);
	LocalDateTime fechaFinal = LocalDateTime.of(2023, 4, 13, 7, 45, 55);
	RepeticionInfinita repeticionInfinita = new RepeticionInfinita();

	FrecuenciaDiaria frecuenciaDiaria = new FrecuenciaDiaria(cadaCuantosDias, repeticionInfinita);
	// Esta fecha es la que cae si haces la cuenta manualmente
	// (Sumarle 2 dias 4 veces a fechaComienzoRepeticion)
	// LocalDateTime fechaFinRepeticion = LocalDateTime.of(2023, 4, 10, 7, 45, 55);
	LocalDateTime fechaMasCercana = LocalDateTime.of(2023, 4, 13, 7, 45, 55);

	//assert
	assertEquals(fechaMasCercana, frecuenciaDiaria.proximoEventoMasCercanoAFechaEspecifica(fechaComienzo, fechaFinal));
    }

	
}
