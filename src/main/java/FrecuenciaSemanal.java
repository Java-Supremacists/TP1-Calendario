import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.util.Arrays;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 * FrecuenciaSemanal
 */
public class FrecuenciaSemanal implements Frecuencia {

    private DayOfWeek[] diasDeLaSemana; //arrays de dias de la semana en ingles.
    //PRECONDICION: Estan en orden y el
    //primer dia que aparece es el mismo
    //dia que el comienzo del evento
    private Repeticion repeticion;

    public FrecuenciaSemanal(DayOfWeek[] diasDeLaSemana, Repeticion repeticion) {
        this.diasDeLaSemana = diasDeLaSemana;
        this.repeticion = repeticion;
    }


    public boolean dadoComienzoCaeElDia(LocalDateTime fechaComienzo, LocalDateTime diaEspecifico) {
        if (this.repeticion.estaDentroDeRepeticiones(diaEspecifico) == false) {
            return false; //Si cae DESPUES del ultimo dia, entonces ni nos
            //molestamos en calcular si  la frecuencia hace
            //que caiga el dia que me piden
        }



        //Averiguamos el dia de la semana del evento que me piden
        DayOfWeek diasDeLaSemanaDelDiaEspecifico = diaEspecifico.getDayOfWeek();

        //Nos fijamos si el dia de la semana del dia que que me piden esta
        //en mi lista original
        boolean estaEnElDiaDeLaSemana = Arrays.asList(this.diasDeLaSemana).contains(diasDeLaSemanaDelDiaEspecifico);

        return estaEnElDiaDeLaSemana;
    }

    @Override
    public LocalDateTime proximoEventoMasCercanoAFechaEspecifica(LocalDateTime inicioEvento, LocalDateTime diaEspecifico) {
        if (this.repeticion.estaDentroDeRepeticiones(diaEspecifico) == false) {
            return diaEspecifico; //Si cae DESPUES del ultimo dia,
            //devolvevemos el dia pedido
        }


        LocalDateTime proximoEvento = diaEspecifico;
        //7 hardcodeado porque en 7 dias ya ves todos los dias de la semana
        for (int i = 0; i <= 7; i++) {
            proximoEvento = diaEspecifico.plusDays(i);

            //El primer evento que cumpla la siguiente condicion es el evento
            //mas proximo al dia al pedido. Como i arranca en 0, incluye al
            //dia mismo. Este if SI O SI tiene que cumplirse, porque abarca los
            //7 dias de la semana.
            boolean proxEventoCaeEnLaSemana = Arrays.asList(this.diasDeLaSemana).contains(proximoEvento.getDayOfWeek());
            if (proxEventoCaeEnLaSemana == true) {
                break;
            }
        }

        return proximoEvento;
    }

    @Override
    public void guardar(Element estructura, Document doc) {
        Element Frecuencia = doc.createElement("FrecuenciaSemanal");
        Frecuencia.appendChild(doc.createTextNode(String.valueOf(this.diasDeLaSemana)));
        estructura.appendChild(Frecuencia);
    	
	this.repeticion.guardar(estructura, doc);
    	
    }


}
