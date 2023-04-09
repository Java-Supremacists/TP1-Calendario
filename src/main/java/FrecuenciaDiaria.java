import java.time.temporal.ChronoUnit; //Libreria para formatear dias en LocalDateTime
import java.time.LocalDateTime;
/**
 * Frecuencia
 */
public class FrecuenciaDiaria implements Frecuencia{

    private String tipo; //Diaria, mensual, etc
    private LocalDateTime fin; //Cuando termina
    private int cadaCuantosDias; //Cada 3 dias, todos los martes y jueves, etc
    private LocalDateTime finDeLasRepeticiones;

    public FrecuenciaDiaria(String tipo, LocalDateTime fin, int cadaCuantosDias, int CuantasRepeticiones){
	this.tipo = tipo;
	this.fin = fin;
	this.cadaCuantosDias = cadaCuantosDias;
    }

    public LocalDateTime finDeLasRepeticionesDadaFecha(LocalDateTime fechaComienzo){
    }
    
    // Dado un dia de comienzo y un dia en especifico, te dice si hay manera de que la repeticion haga que "caiga" en ese dia
    // Ej: Si tenes repeticion cada 2 dias, tu comienzo es el 10 y tu fin el 12, devuelve. Si fuese cada 3 dias y le pasas los mismos dias devuelve False
    public boolean dadoComienzoCaeElDia(LocalDateTime fechaComienzo, LocalDateTime diaEspecifico){
	long cantDiasHastaDiaPedido = diaEspecifico.until(diaEspecifico, ChronoUnit.DAYS); //Se fija cuantos dias hay hasta el dia pasado como argumento
	boolean eventoCaeElDiaPedidio = (cantDiasHastaDiaPedido % this.cadaCuantosDias == 0); //True: El evento tiene una "aparicion" ese dia. False: no.
	return eventoCaeElDiaPedidio;
    }
}
