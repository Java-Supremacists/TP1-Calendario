import java.time.LocalDateTime;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * TareaTest
 */
public class TareaTest {

	@Test
	public void creacionDeTareaVacia() {
		//arrange
		LocalDateTime termina = LocalDateTime.of(2023,4,24,23,59);
		Tarea tar = new Tarea("", "", null,false, termina);
		//act

		//assert

		assertEquals(Activities.tipo.TAREA, tar.type());
		assertEquals(termina, tar.cuandoEmpieza());
		assertEquals(termina, tar.cuandoTermina());
		termina = LocalDateTime.of(2023,4,25,23,59);
		tar.setTermina(termina);
		assertEquals(termina, tar.cuandoEmpieza());
		assertEquals(termina, tar.cuandoTermina());
		assertNull(tar.ultimaAlarma());
		assertEquals("", tar.getDescripcion());
		assertEquals("", tar.getTitulo());
		assertFalse(tar.esDiaEntero());
		tar.setComplete(true);
		tar.setName("Nombre1");
		tar.setDescription("Descripcion1");
		assertEquals("Descripcion1", tar.getDescripcion());
		assertEquals("Nombre1", tar.getTitulo());
		assertTrue(tar.esDiaEntero());
	}

	@Test
	public void creacionDeTarea() {
		//arrange
		LocalDateTime termina = LocalDateTime.of(2023,4,24,23,59);
		Tarea tar = new Tarea("Nombre Tarea", "Descripcion Tarea", null,false, termina);
		//act
		//assert
		assertEquals("Nombre Tarea", tar.getTitulo());
		assertEquals("Descripcion Tarea", tar.getDescripcion());
		assertFalse(tar.esDiaEntero());
		assertFalse(tar.estaCompleta()); //Las tareas empiezan como incompletas
		assertEquals(Activities.tipo.TAREA, tar.type());
		assertEquals(termina, tar.cuandoEmpieza());
		assertEquals(termina, tar.cuandoTermina());
		assertNull(tar.ultimaAlarma());
	}

	@Test
	public void marcarTareaCompleta() {
		//arrange
		Tarea tareaDePrueba = new Tarea("Nombre Tarea", "Descripcion Tarea", null,false, LocalDateTime.now());
		//act
		tareaDePrueba.marcarCompleta();
		//assert
		assertTrue(tareaDePrueba.estaCompleta());
	}

	@Test
	public void marcarTareaCompletaDosVecesVuelveAIncompleta() {
		//arrange
		Tarea tareaDePrueba = new Tarea("Nombre Tarea", "Descripcion Tarea", null,false, LocalDateTime.now());
		//act
		tareaDePrueba.marcarCompleta();
		tareaDePrueba.marcarCompleta();
		//assert
		assertFalse(tareaDePrueba.estaCompleta());
	}
	@Test
	public void cuandoEmpiezaTest(){

	}
	@Test
	public void cuandoTerminaTest(){

	}
	@Test
	public void UltimaAlarmaYSonarUltimaAlarma(){

	}
}
