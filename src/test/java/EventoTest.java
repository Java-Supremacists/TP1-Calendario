import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.time.LocalDateTime;


public class EventoTest {

    @Test
    public void eventoCaeDiaPedido(){

	//arrange
	ArrayList<LocalDateTime> alarmas = new ArrayList<LocalDateTime>();
	//of(int year, int month, int dayOfMonth, int hour, int minute) 
	Evento eventoDePrueba = new Evento("Evento de prueba", "Descripcion de prueba", alarmas, false, LocalDateTime.of(2023, 4, 10, 7, 45, 55), LocalDateTime.of(2024, 3, 10, 7, 45, 55));

	//assert
	assertEquals(true, eventoDePrueba.caeElDia(LocalDateTime.of(2023, 4, 22, 7, 45, 55)));
	assertEquals(false, eventoDePrueba.caeElDia(LocalDateTime.of(2023, 4, 21, 7, 45, 55)));
	assertEquals(false, eventoDePrueba.caeElDia(LocalDateTime.of(2023, 4, 20, 7, 45, 55)));
    }

}
