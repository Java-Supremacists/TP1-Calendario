import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
/**
 * CalendarioTest
 */
public class CalendarioTest {

	@Test
	public void creacionDeCalendario() {
		//arrange
		Calendario calendarioDePrueba = new Calendario();
	}
	@Test
	public void creacionDeTareaEnCalendario() {
		//arrange
		Calendario calendarioDePrueba = new Calendario();
		//act
		LocalDateTime termina = LocalDateTime.of(2002,1,1,0,0);
		var indice = calendarioDePrueba.crearTarea("Nombre Tarea", "Descripcion Tarea",null,true,termina);

		////assert
		var tarea = (Tarea) calendarioDePrueba.obtenerActividad(indice);
		assertEquals("Nombre Tarea", tarea.getTitulo());
		assertEquals("Descripcion Tarea", tarea.getDescripcion());
		assertTrue(tarea.esDiaEntero());
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
