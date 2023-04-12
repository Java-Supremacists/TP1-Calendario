import java.time.LocalDateTime;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * TareaTest
 */
public class TareaTest {

	@Test
	public void creacionDeTarea() {
		//arrange
		Tarea tareaDePrueba = new Tarea("Nombre Tarea", "Descripcion Tarea", false, LocalDateTime.now());
		//act
		//assert
		assertEquals("Nombre Tarea", tareaDePrueba.getTitulo());
		assertEquals("Descripcion Tarea", tareaDePrueba.getDescripcion());
		assertEquals(false, tareaDePrueba.esDiaEntero());
		assertEquals(false, tareaDePrueba.estaCompleta()); //Las tareas empiezan como incompletas
	}

	@Test
	public void marcarTareaCompleta() {
		//arrange
		Tarea tareaDePrueba = new Tarea("Nombre Tarea", "Descripcion Tarea", false, LocalDateTime.now());
		//act
		tareaDePrueba.marcarCompleta();
		//assert
		assertEquals(true, tareaDePrueba.estaCompleta());
	}

	@Test
	public void marcarTareaCompletaDosVecesVuelveAIncompleta() {
		//arrange
		Tarea tareaDePrueba = new Tarea("Nombre Tarea", "Descripcion Tarea", false, LocalDateTime.now());
		//act
		tareaDePrueba.marcarCompleta();
		tareaDePrueba.marcarCompleta();
		//assert
		assertEquals(false, tareaDePrueba.estaCompleta());
	}

	@Test
	public void estaCompleta() {
	}

	@Test
	public void marcarCompleta() {
	}


	@Test
	public void setTermina() {
	}

	@Test
	public void type() {
	}

	@Test
	public void cuandoTermina() {
	}

	@Test
	public void cuandoEmpieza() {
	}

	@Test
	public void quedanAlarmas() {
	}

	@Test
	public void ultimaAlarma() {
	}

	@Test
	public void sonarUltimaAlarma() {
	}

	@Test
	public void getTitulo() {
	}

	@Test
	public void getDescripcion() {
	}

	@Test
	public void esDiaEntero() {
	}

	@Test
	public void setName() {
	}

	@Test
	public void setDescription() {
	}

	@Test
	public void setAlarm() {
	}

	@Test
	public void setComplete() {
	}
}
