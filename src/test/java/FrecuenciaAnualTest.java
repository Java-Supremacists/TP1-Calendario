import java.time.LocalDateTime;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * FrecuenciaAnualTest
 */
public class FrecuenciaAnualTest {
    @Test
    public void testFrecuenciaAnualCaeElDia(){
	    int cadaCuantosdias = 365;
	    var arranca = LocalDateTime.of(2023, 4, 4, 0, 0, 0);
	    var fechaAConfirmar = LocalDateTime.of(2024, 4, 3, 0, 0, 0); //Si se hace la cuenta manualmente este es el dia que cae
	    var repeticionInfinita = new RepeticionInfinita();
	    var frecuenciadiaria = new FrecuenciaDiaria(cadaCuantosdias, repeticionInfinita);
     
	    assertEquals(true, frecuenciadiaria.dadoComienzoCaeElDia(arranca, fechaAConfirmar));
    }

    @Test
    public void testFrecuenciaAnualMasCercano(){
	    int cadaCuantosdias = 365;
	    var arranca = LocalDateTime.of(2023, 4, 4, 0, 0, 0);
	    var fechaObjetivo= LocalDateTime.of(2024, 4, 2, 0, 0, 0);
	    var fechaMasCercana= LocalDateTime.of(2024, 4, 3, 0, 0, 0);
	    var repeticionInfinita = new RepeticionInfinita();
	    var frecuenciadiaria = new FrecuenciaDiaria(cadaCuantosdias, repeticionInfinita);
     
	    assertEquals(fechaMasCercana, frecuenciadiaria.proximoEventoMasCercanoAFechaEspecifica(arranca, fechaObjetivo));
    }

}
