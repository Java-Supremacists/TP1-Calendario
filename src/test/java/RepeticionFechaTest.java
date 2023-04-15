import java.time.LocalDateTime;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * RepeticionFechaTest
 */
public class RepeticionFechaTest {

    @Test
    public void creacionDeRepeticionFecha(){
	//arrange
	LocalDateTime fechaFinRepeticion = LocalDateTime.of(2023, 4, 22, 7, 45, 55);
	LocalDateTime fechaComienzoRepeticion = LocalDateTime.of(2023, 2, 22, 7, 45, 55);
	int cadaCuantosDias = 8;

	RepeticionFecha repeticionFechaDePrueba = new RepeticionFecha(fechaFinRepeticion);

	//
	//assert
	assertEquals(fechaFinRepeticion, repeticionFechaDePrueba.finDeLaRepeticion(fechaComienzoRepeticion, cadaCuantosDias));



    }
}
