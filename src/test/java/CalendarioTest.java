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
		// var indice = calendarioDePrueba.crearTarea("Nombre Tarea", "Descripcion Tarea",null,true,termina);
		var indice = calendarioDePrueba.crearTarea(termina);
		calendarioDePrueba.modificarActividadNombre(indice, "Nombre Tarea");
		calendarioDePrueba.modificarActividadDescripcion(indice, "Descripcion Tarea");
		calendarioDePrueba.modificarActividadEsDiaEntero(indice, true);

		////assert
	}

}
