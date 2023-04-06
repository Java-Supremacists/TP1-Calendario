import org.junit.Test;

import static org.junit.Assert.*;
/**
 * CalendarioTest
 */
public class CalendarioTest {

	@Test
	public void creacionDeTareaSimple() {
		//arrange
		Calendario calendarioDePrueba = new Calendario("Calendario de Prueba");
		//act
		calendarioDePrueba.crearTarea("Nombre Tarea", "Descripcion Tarea", false);

		//assert
		assertEquals("Nombre Tarea", calendarioDePrueba.obtenerActividadPorIndice(0).getTitulo());
	}
}
