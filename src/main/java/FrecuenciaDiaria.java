import java.time.temporal.ChronoUnit; //Libreria para formatear dias en LocalDateTime
import java.time.LocalDateTime;
/**
 * Frecuencia
 */
public class FrecuenciaDiaria implements Frecuencia{

    private int cadaCuantosDias; //Cada 3 dias, cada 5 dias
    private Repeticion repeticion;

    public FrecuenciaDiaria(int cadaCuantosDias, Repeticion repeticion){
	this.cadaCuantosDias = cadaCuantosDias;
	this.repeticion = repeticion;

    }

    public LocalDateTime finDeLasRepeticionesDadaFecha(LocalDateTime fechaComienzo){
	LocalDateTime finDeLaRepeticion = this.repeticion.finDeLaRepeticion(fechaComienzo, this.cadaCuantosDias);
	return finDeLaRepeticion;

    };
    

    // Dado un dia de comienzo y un dia en especifico, te dice si hay manera 
    // de que la repeticion haga que "caiga" en ese dia
    // Ej: Si tenes repeticion cada 2 dias, tu comienzo es el 10 y tu fin el 12,
    // devuelve. Si fuese cada 3 dias y le pasas los mismos dias devuelve False
    public boolean dadoComienzoCaeElDia(LocalDateTime fechaComienzo, LocalDateTime diaEspecifico){
	//Se fija cuantos dias hay hasta el dia pasado como argumento
	long cantDiasHastaDiaPedido = fechaComienzo.until(diaEspecifico, ChronoUnit.DAYS);

	//True: El evento tiene una "aparicion" ese dia. False: no.
	boolean eventoCaeElDiaPedidio = (cantDiasHastaDiaPedido % this.cadaCuantosDias == 0);
	return eventoCaeElDiaPedidio;
    }
    
}
