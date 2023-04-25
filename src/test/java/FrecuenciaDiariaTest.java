import java.time.LocalDateTime;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * FrecuenciaDiariaTest
 */
public class FrecuenciaDiariaTest {

    @Test
    public void calcularProximoEventoMasCercanoAFecha(){
	//arrange
	int cadaCuantosDias = 2;

	LocalDateTime fechaComienzo = LocalDateTime.of(2023, 4, 4, 7, 45, 55);
	LocalDateTime fechaFinal = LocalDateTime.of(2023, 4, 13, 7, 45, 55);
	RepeticionInfinita repeticionInfinita = new RepeticionInfinita();

	//Le pasamos repeticionInfinita de constructor ya que solo queremos 
	//testear la frecuencia
	FrecuenciaDiaria frecuenciaDiaria = new FrecuenciaDiaria(cadaCuantosDias, repeticionInfinita);
	// Esta fecha es la que cae si haces la cuenta manualmente
	// (Sumarle 2 dias 4 veces a fechaComienzoRepeticion)
	// LocalDateTime fechaFinRepeticion = LocalDateTime.of(2023, 4, 10, 7, 45, 55);
	LocalDateTime fechaMasCercana = LocalDateTime.of(2023, 4, 14, 7, 45, 55);

	//assert
	assertEquals(fechaMasCercana, frecuenciaDiaria.proximoEventoMasCercanoAFechaEspecifica(fechaComienzo, fechaFinal));
    }

    @Test
    public void calcularProximoEventoMasCercanoAFechaMismoDia(){
	//arrange
	int cadaCuantosDias = 2;

	LocalDateTime fechaComienzo = LocalDateTime.of(2023, 4, 5, 7, 45, 55);
	LocalDateTime fechaFinal = LocalDateTime.of(2023, 4, 13, 7, 45, 55);
	RepeticionInfinita repeticionInfinita = new RepeticionInfinita();

	//Le pasamos repeticionInfinita de constructor ya que solo queremos 
	//testear la frecuencia
	FrecuenciaDiaria frecuenciaDiaria = new FrecuenciaDiaria(cadaCuantosDias, repeticionInfinita);
	// Esta fecha es la que cae si haces la cuenta manualmente
	// (Sumarle 2 dias 4 veces a fechaComienzoRepeticion)
	// LocalDateTime fechaFinRepeticion = LocalDateTime.of(2023, 4, 10, 7, 45, 55);
	LocalDateTime fechaMasCercana = LocalDateTime.of(2023, 4, 13, 7, 45, 55);

	//assert
	assertEquals(fechaMasCercana, frecuenciaDiaria.proximoEventoMasCercanoAFechaEspecifica(fechaComienzo, fechaFinal));
    }

    @Test
    public void dadoComienzoCaeElDia(){
	//arrange
	int cadaCuantosDias = 2;

	LocalDateTime fechaComienzo = LocalDateTime.of(2023, 4, 4, 7, 45, 55);
	LocalDateTime fechaATestearFalso = LocalDateTime.of(2023, 4, 13, 7, 45, 55);
	LocalDateTime fechaATestearPositivo = LocalDateTime.of(2023, 4, 12, 7, 45, 55);
	RepeticionInfinita repeticionInfinita = new RepeticionInfinita();

	//Le pasamos repeticionInfinita de constructor ya que solo queremos 
	//testear la frecuencia
	FrecuenciaDiaria frecuenciaDiaria = new FrecuenciaDiaria(cadaCuantosDias, repeticionInfinita);
	// Esta fecha es la que cae si haces la cuenta manualmente
	// (Sumarle 2 dias 4 veces a fechaComienzoRepeticion)
	// LocalDateTime fechaFinRepeticion = LocalDateTime.of(2023, 4, 10, 7, 45, 55);

	//assert
	assertEquals(true, frecuenciaDiaria.dadoComienzoCaeElDia(fechaComienzo, fechaATestearPositivo));
	assertEquals(false, frecuenciaDiaria.dadoComienzoCaeElDia(fechaComienzo, fechaATestearFalso));
    }

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
     
    // @Test
    // public void testFrecuenciaMensualDadoComienzo(){
	    // int cadaCuantosdias = 30;
	    // var arranca = LocalDateTime.of(2023, 4, 4, 0, 0, 0)
	    // var termina = LocalDateTime.of(2023, 4, 13, 0, 0, 0)
	    // var fechaMasCercana = LocalDateTime.of(2023, 4, 14, 0, 0, 0)
     
	    // var repeticionInfinita = RepeticionInfinita();
	    // var frecuenciadiaria = FrecuenciaDiaria(cadaCuantosdias, repeticionInfinita);
     
	    // assertEquals(true, even1.dadoComienzo(fechaQueCae));
    // }
     
     
    // @Test
    // public void testFrecuenciaAnualCaeElDia(){
	    // int cadaCuantosdias = 365;
	    // var arranca = LocalDateTime.of(2023, 4, 4, 0, 0, 0)
	    // var termina = LocalDateTime.of(2023, 4, 13, 0, 0, 0)
	    // var repeticionInfinita = RepeticionInfinita();
	    // var frecuenciadiaria = FrecuenciaDiaria(cadaCuantosdias, repeticionInfinita);
     
	    // assertEquals(true, even1.caeElDia(fechaQueCae));
    // }
     
    // @Test
    // public void testFrecuenciaAnualCaeElDia(){
	    // int cadaCuantosdias = 365;
	    // var arranca = LocalDateTime.of(2023, 4, 4, 0, 0, 0)
	    // var termina = LocalDateTime.of(2023, 4, 13, 0, 0, 0)
	    // var fechaMasCercana = LocalDateTime.of(2023, 4, 14, 0, 0, 0)
     
	    // var repeticionInfinita = RepeticionInfinita();
	    // var frecuenciadiaria = FrecuenciaDiaria(cadaCuantosdias, repeticionInfinita);
     
	    // assertEquals(true, even1.dadoComienzo(fechaQueCae));
    // }
 
	
}
