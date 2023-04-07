import org.junit.Test;

import static org.junit.Assert.*;
/**
 * CalendarioTest
 */
public class CalendarioTest {

	@Test
	public void creacionDeTareaSimpleDesdeCalendario() {
		//arrange
		Calendario calendarioDePrueba = new Calendario("Calendario de Prueba");
		//act
		//calendarioDePrueba.crearTarea("Nombre Tarea", "Descripcion Tarea", false);

		////assert
		//assertEquals("Nombre Tarea", calendarioDePrueba.obtenerActividadPorIndice(0).getTitulo());
		//assertEquals("Descripcion Tarea", calendarioDePrueba.obtenerActividadPorIndice(0).getDescripcion());
		//assertEquals(false, calendarioDePrueba.obtenerActividadPorIndice(0).esDiaEntero());
	}

	//@Test
	//public void marcarTareaComoCompleta(){
	//	//arrange
	//	Calendario calendarioDePrueba = new Calendario("Calendario de Prueba");

	//	//act
	//	calendarioDePrueba.crearTarea("Nombre Tarea", "Descripcion Tarea", false);
	//	calendarioDePrueba.obtenerActividadPorIndice(0).marcarTareaComoCompleta();

	//	//assert
	//	assertEquals("Nombre Tarea", calendarioDePrueba.obtenerActividadPorIndice(0).getTitulo());
	//	assertEquals(true, calendarioDePrueba.obtenerActividadPorIndice(0).estaCompleta());
	//}
}
