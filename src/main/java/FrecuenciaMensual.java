import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit; //Libreria para formatear dias en LocalDateTime
/**
 * FrecuenciaMensual
 */
public class FrecuenciaMensual implements Frecuencia {

    private final int cadaCuantosDias = 30; //Cada 3 dias, cada 5 dias
    private Repeticion repeticion;

    public FrecuenciaAnual(int cadaCuantosDias, Repeticion repeticion){
	// this.cadaCuantosDias = cadaCuantosDias;
	this.repeticion = repeticion;

    }

    public boolean dadoComienzoCaeElDia(LocalDateTime fechaComienzo, LocalDateTime diaEspecifico){
	if (this.repeticion.estaDentroDeRepeticiones(diaEspecifico) == false) {
	    return false; //Si cae DESPUES del ultimo dia, entonces ni nos 
			  //molestamos en calcular si  la frecuencia hace 
			  //que caiga el dia que me piden
	}

	//Se fija cuantos dias hay hasta el dia pasado como argumento
	long cantDiasHastaDiaPedido = fechaComienzo.until(diaEspecifico, ChronoUnit.DAYS);

	//True: El evento tiene una "aparicion" ese dia. False: no.
	boolean eventoCaeElDiaPedidio = (cantDiasHastaDiaPedido % this.cadaCuantosDias == 0);
	return eventoCaeElDiaPedidio;
    }
    
}
