import java.time.LocalDateTime;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * FrecuenciaDiariaTest
 */
public class FrecuenciaMensualTest {

    @Test
    public void testFrecuenciaMensualCaeElDia(){
	    int cadaCuantosdias = 30;
	    var arranca = LocalDateTime.of(2023, 4, 4, 0, 0, 0);
	    var fechaAConfirmar = LocalDateTime.of(2023, 5, 4, 0, 0, 0);
	    var repeticionInfinita = new RepeticionInfinita();
	    var frecuenciadiaria = new FrecuenciaDiaria(cadaCuantosdias, repeticionInfinita);
     
	    assertEquals(true, frecuenciadiaria.dadoComienzoCaeElDia(arranca, fechaAConfirmar));
    }

    @Test
    public void testFrecuenciaMensualMasCercano(){
	    int cadaCuantosdias = 30;
	    var arranca = LocalDateTime.of(2023, 4, 4, 0, 0, 0);
	    var fechaObjetivo= LocalDateTime.of(2023, 5, 3, 0, 0, 0);
	    var fechaMasCercana= LocalDateTime.of(2023, 5, 4, 0, 0, 0);
	    var repeticionInfinita = new RepeticionInfinita();
	    var frecuenciadiaria = new FrecuenciaDiaria(cadaCuantosdias, repeticionInfinita);
     
	    assertEquals(fechaMasCercana, frecuenciadiaria.proximoEventoMasCercanoAFechaEspecifica(arranca, fechaObjetivo));
    }
}
