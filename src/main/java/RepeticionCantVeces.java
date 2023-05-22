import java.time.LocalDateTime;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.time.DayOfWeek;
/**
 * RepeticionCantVeces
 */
public class RepeticionCantVeces implements Repeticion {
    private LocalDateTime fechaFinRepeticion;

    //Los guardamos solamente para que lo podamos gurdar en un archivo despues
    private int cantidadDeRepeticionesMaximas;
    private int cadaCuantosDias;
    private DayOfWeek[] diasDeLaSemana;
    private LocalDateTime fechaComienzo;

    //No estamos totalmente contentos con la presencia de dos constructores
    //distintos, sin embargo, fue la mejor manera que se nos ocurrio de poder
    //encapsular dos casos distinto en 1
    public RepeticionCantVeces(int cantidadDeRepeticionesMaximas, int cadaCuantosDias, LocalDateTime fechaComienzo) {
        //Esto nos da la cantidad de dias extra para llegar al ultimo dia
        //Le restamos uno a cantidadDeRepeticionesMaximas porque el dia en el
        //que estamos cuenta como una repeticion
        int cantidadDeDiasASumar = cadaCuantosDias * (cantidadDeRepeticionesMaximas - 1);

        //Le sumamos esos dias a la fecha que nos pasaron
        LocalDateTime fechaFinal = fechaComienzo.plusDays(cantidadDeDiasASumar);

        this.fechaFinRepeticion = fechaFinal;

        this.cantidadDeRepeticionesMaximas = cantidadDeRepeticionesMaximas;
        this.cadaCuantosDias = cadaCuantosDias;
        this.fechaComienzo = fechaComienzo;


    }

    public RepeticionCantVeces(int cantidadDeRepeticionesMaximas, DayOfWeek[] diasDeLaSemana, LocalDateTime fechaComienzo) {
        //La idea de este for loop es que te diga en que dia DE LA SEMANA cae
        //el ultimo dia
        int diaDeLaSemana = -1; //Arranca en -1 porque lo primero que hace es
        //sumar, y tiene que arrancar siendo 0
        int cantidadDeRepeticionesSemanales = 0;
        for (int i = 0; i < cantidadDeRepeticionesMaximas; i ++) {
            //Cuando esto se cumple, significa que dio una vuelta completa a la
            //cantidad de dias. Le sumo un dia porque el if "le tiene que ganar"
            //al for en la siguiente iteracion

            diaDeLaSemana++;
            if (diaDeLaSemana == diasDeLaSemana.length ) {
                diaDeLaSemana = 0;
                cantidadDeRepeticionesSemanales++;
            }
        }
        //TODO: Esto de aca arriba tal vez se puede hacer con la funcion modulo
        //No estamos seguro si es el caso.

        LocalDateTime offsetDiaDeLaSemana = fechaComienzo;
        DayOfWeek diaDeLaSemanaDeInicio = diasDeLaSemana[diaDeLaSemana];

        //Cuando estos dos sean iguales significa que llegamos al dia mas
        //proximo con el dia de la semana que necesitamos
        while (offsetDiaDeLaSemana.getDayOfWeek() != diaDeLaSemanaDeInicio) {
            offsetDiaDeLaSemana = offsetDiaDeLaSemana.plusDays(1);
        }


        //7 Harcodeado porque hay 7 dias entre dos fechas con el mismo dia de
        //la semana (ej: 7 dias entre el martes 4 y martes 11)
        LocalDateTime fechaFinal = offsetDiaDeLaSemana.plusDays(cantidadDeRepeticionesSemanales * 7);

        this.fechaFinRepeticion = fechaFinal;

        this.cantidadDeRepeticionesMaximas = cantidadDeRepeticionesMaximas;
        this.diasDeLaSemana = diasDeLaSemana;
        this.fechaComienzo = fechaComienzo;
    }

    @Override
    public boolean estaDentroDeRepeticiones(LocalDateTime fechaPedida) {
        boolean estaDespuesDelLimite = fechaPedida.isBefore(this.fechaFinRepeticion);
        boolean esJustoElLimite = fechaPedida.isEqual(this.fechaFinRepeticion);
        boolean estaDentro = (estaDespuesDelLimite || esJustoElLimite);
        return estaDentro;
    }

    @Override
    public void guardar(Element estructura, Document doc) {
	//Solo tengo que guardar una cosa. Si dias de la semana no existe,
	//entonces el la repeticion se creo con cadaCuantosDias
        String diasDeLaSemanaOCadaCuantosDias = "";
	if (this.diasDeLaSemana != null) {
	    for (int i = 0; i < this.diasDeLaSemana.length; i++) {
		if (i != 0) {   //Le ponemos coma (,) a todos los elementos (excepto
		    diasDeLaSemanaOCadaCuantosDias += ",";//al primero) para evitar que quede: ,MONDAY,THURSDAY
		}
		diasDeLaSemanaOCadaCuantosDias += this.diasDeLaSemana[i].toString();

	    }
	}
	else {
	    diasDeLaSemanaOCadaCuantosDias = String.valueOf(this.cadaCuantosDias);
	}


        Element Repeticion = doc.createElement("RepeticionCantVeces");
        Repeticion.appendChild(doc.createTextNode(String.valueOf(this.cantidadDeRepeticionesMaximas) + "@" +
						  String.valueOf(diasDeLaSemanaOCadaCuantosDias) + "@" +
						  String.valueOf(this.fechaComienzo)));
        estructura.appendChild(Repeticion);

    }
}
