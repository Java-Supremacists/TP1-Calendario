import org.junit.Test;

import static org.junit.Assert.*;


/**
 * TareaTest
 */
public class TareaTest {

	@Test
	public void creacionDeTarea() {
		//arrange
		Tarea tareaDePrueba = new Tarea("Nombre Tarea", "Descripcion Tarea", false);
		//act
		//assert
		assertEquals("Nombre Tarea", tareaDePrueba.getTitulo());
		assertEquals("Descripcion Tarea", tareaDePrueba.getDescripcion());
		assertEquals(false, tareaDePrueba.esDiaEntero());

	}
}
