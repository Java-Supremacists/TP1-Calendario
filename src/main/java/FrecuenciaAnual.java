import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit; //Libreria para formatear dias en LocalDateTime
/**
 * FrecuenciaAnual
 */
public class FrecuenciaAnual implements Frecuencia {

    private final int cadaCuantosDias = 365; //Cada 3 dias, cada 5 dias
    private Repeticion repeticion;

    public FrecuenciaAnual(int cadaCuantosDias, Repeticion repeticion){
	// this.cadaCuantosDias = cadaCuantosDias;
	this.repeticion = repeticion;

    }

    // Dado un dia de comienzo y un dia en especifico, te dice si hay manera 
    // de que la repeticion haga que "caiga" en ese dia
    // Ej: Si tenes repeticion cada 2 dias, tu comienzo es el 10 y tu fin el 12,
    // devuelve True. Si fuese cada 3 dias y le pasas los mismos dias devuelve False
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
