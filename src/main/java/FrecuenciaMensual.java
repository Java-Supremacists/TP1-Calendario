import java.time.temporal.ChronoUnit; //Libreria para formatear dias en LocalDateTime
import java.time.LocalDateTime;
import java.lang.Math;
/**
 * FrecuenciaMensual
 */
public class FrecuenciaMensual implements Frecuencia {

    private final int cadaCuantosDias = 30;
    private Repeticion repeticion;

    public FrecuenciaMensual(Repeticion repeticion) {
        this.repeticion = repeticion;

    }

    // Dado un dia de comienzo y un dia en especifico, te dice si hay manera
    // de que la repeticion haga que "caiga" en ese dia
    // Ej: Si tenes repeticion cada 2 dias, tu comienzo es el 10 y tu fin el 12,
    // devuelve True. Si fuese cada 3 dias y le pasas los mismos dias devuelve False
    @Override
    public boolean dadoComienzoCaeElDia(LocalDateTime fechaComienzo, LocalDateTime diaEspecifico) {
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

    @Override
    public LocalDateTime proximoEventoMasCercanoAFechaEspecifica(LocalDateTime inicioEvento, LocalDateTime diaEspecifico) {
        if (this.repeticion.estaDentroDeRepeticiones(diaEspecifico) == false) {
            return diaEspecifico; //Si cae DESPUES del ultimo dia,
            //devolvevemos el dia pedido
        }

        //Se fija cuantos dias hay hasta el dia pasado como argumento
        long cantDiasHastaDiaPedido = inicioEvento.until(diaEspecifico, ChronoUnit.DAYS);

        //Esta funcion me devuelve la division redondeada para arriba.
        //Esto nos sirve para calcular la cantidad de repeticiones (pasandose,
        //dado el caso) que necesitamos para hallar el evento mas proximo
        //Funcion sacada de: https://stackoverflow.com/a/17149572/13683575
        int cantidadRepsHastaProxEvento = (int) Math.ceil((double)cantDiasHastaDiaPedido / this.cadaCuantosDias);

        LocalDateTime proximoEvento = inicioEvento.plusDays(cantidadRepsHastaProxEvento * this.cadaCuantosDias);

        return proximoEvento;
    }

}
