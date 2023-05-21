import java.time.DayOfWeek;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Evento extends Activities {
    //--------- Atributos ---------

    private LocalDateTime arranquePrincipio;
    private LocalDateTime terminaPrincipio;
    private LocalDateTime arranqueActual;
    private LocalDateTime terminaActual;
    private Frecuencia frecuencia;

    //--------- Atributos ---------

    //--------- Constructores ---------
    public Evento(LocalDateTime arranque, LocalDateTime termina ) {
        super();
        this.arranquePrincipio = arranque;
        this.arranqueActual = arranque;
        this.terminaPrincipio = termina;
        this.terminaActual = termina;
        frecuencia = null;
    }
    public Evento(String nombre, String descripcion, boolean esCompleto,LocalDateTime arranque, LocalDateTime termina ) {
        super(nombre, descripcion, esCompleto);
        this.arranquePrincipio = arranque;
        this.arranqueActual = arranque;
        this.terminaPrincipio = termina;
        this.terminaActual = termina;
        frecuencia = null;
    }

    //--------- Metodos ---------

    @Override
    public LocalDateTime cuandoEmpieza() {
        return this.arranqueActual;
    }
    @Override
    public LocalDateTime cuandoTermina() {
        return this.terminaActual;
    }
    public boolean caeElDia(LocalDateTime diaEspecifico) {
        //Averiguo si la frecuencia hace que el evento caiga el día pedido
        return this.frecuencia.dadoComienzoCaeElDia(this.arranquePrincipio, diaEspecifico);
    }
    public LocalDateTime proximoEventoMasCercanoAFechaEspecifica(LocalDateTime diaEspecifico) {
        LocalDateTime proximoEvento;
        proximoEvento = this.frecuencia.proximoEventoMasCercanoAFechaEspecifica(this.arranquePrincipio, diaEspecifico);
        return proximoEvento;
    }
    public void setArranque(LocalDateTime arranque) {
        this.arranquePrincipio = arranque;
        this.arranqueActual = arranque;
    }
    public void setTermina(LocalDateTime termina) {
        this.terminaPrincipio = termina;
        this.terminaActual = termina;
    }
    public void setFrecuencia(Frecuencia frecuenciaNueva) {
        frecuencia = frecuenciaNueva;
    }
    @Override
    public void guardar(Element estructura, Document doc) {
        super.guardar(estructura,doc);
        Element FechaInicio1 = doc.createElement("ArranquePrincipio");
        FechaInicio1.appendChild(doc.createTextNode(arranquePrincipio.toString()));
        estructura.appendChild(FechaInicio1);

        Element FechaFinal1 = doc.createElement("TerminaPrincipio");
        FechaFinal1.appendChild(doc.createTextNode(terminaPrincipio.toString()));
        estructura.appendChild(FechaFinal1);

        Element FechaInicio2 = doc.createElement("ArranqueActual");
        FechaInicio2.appendChild(doc.createTextNode(arranqueActual.toString()));
        estructura.appendChild(FechaInicio2);

        Element FechaFinal2 = doc.createElement("TerminaActual");
        FechaFinal2.appendChild(doc.createTextNode(terminaActual.toString()));
        estructura.appendChild(FechaFinal2);

        if (this.frecuencia != null) {
            this.frecuencia.guardar(estructura, doc);
        }
    }
    @Override
    public void cargar(Element Evento) {
        super.cargar(Evento);

	Frecuencia frecuenciaEncontrada;
	Repeticion repeticionEncontrada;

	// Le damos valores momentaneos, simplemente porque Java dice que
	// PUEDE que no sean inicializadas
	repeticionEncontrada = new RepeticionInfinita();
	frecuenciaEncontrada = new FrecuenciaDiaria(1, repeticionEncontrada);

        var elementosDelEvento = Evento.getChildNodes();
        for (int i = 0; i< elementosDelEvento.getLength(); i++) {
            if (elementosDelEvento.item(i) instanceof Element elementoInterno) {
                switch (elementoInterno.getTagName()) {
		    case "ArranquePrincipio":
			arranquePrincipio = LocalDateTime.parse(elementoInterno.getTextContent());
		    case "TerminaPrincipio" :
			terminaPrincipio = LocalDateTime.parse(elementoInterno.getTextContent());
		    case "ArranqueActual" :
			arranqueActual = LocalDateTime.parse(elementoInterno.getTextContent());
		    case "TerminaActual" :
			terminaActual = LocalDateTime.parse(elementoInterno.getTextContent());

		    // @Facu, esto te lo digo a vos. Cuando hagamos la entrega
		    // podemos reemplazar el "yo" por nosotros o podemos borrar
		    // a la mierda este comentario. Como vos prefieras
		    //
		    // Esto no es ideal, pero creo yo que es la manera mas prolija
		    // de hacerlo. 
		    // Por la manera en la que estan hechos las frecuencia, no tengo
		    // manera de poder hacer algo generico. Incluso en el caso de 
		    // reemplazar la interfaz por una clase abstracta, los Constructores
		    // son tan distintos que terminaria resultando a un conjunto
		    // de ifs de cualquier manera. 
		    // :(
		    //
		    // RepeticionInfinita se usa como un valor momentaneo
		    case "FrecuenciaMensual":
			frecuenciaEncontrada = new FrecuenciaMensual(new RepeticionInfinita()); 

		    case "FrecuenciaDiaria":
			frecuenciaEncontrada = new FrecuenciaDiaria(Integer.parseInt(elementoInterno.getTextContent()), new RepeticionInfinita());

		    case "FrecuenciaAnual":
			frecuenciaEncontrada = new FrecuenciaAnual(new RepeticionInfinita());

		    // case "FrecuenciaSemanal":
			// String[] diasDeLaSemana = elementoInterno.getTextContent().split(",");
			// System.out.println(diasDeLaSemana);
			// frecuenciaEncontrada = new FrecuenciaSemanal

		    case "RepeticionFecha":
			repeticionEncontrada = new RepeticionFecha(LocalDateTime.parse(elementoInterno.getTextContent()));

		    case "RepeticionInfinita":
			repeticionEncontrada = new RepeticionInfinita();

		// case "RepeticionCantVeces" -> repeticionEncontrada = new RepeticionCantVeces
                }
            }
        }
	this.frecuencia = frecuenciaEncontrada;
	this.frecuencia.cambiarRepeticion(repeticionEncontrada);
        //frecuencia.cargar()
    }
    //TODO: Funcion para debugear
    public String mostrarFrecuencia() {
	return String.valueOf(this.frecuencia);

    }
    //--------- Metodos ---------
}
