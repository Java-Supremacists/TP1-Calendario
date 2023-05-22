import java.time.DayOfWeek;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.time.LocalDateTime;

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
                    break;
                case "TerminaPrincipio" :
                    terminaPrincipio = LocalDateTime.parse(elementoInterno.getTextContent());
                    break;
                case "ArranqueActual" :
                    arranqueActual = LocalDateTime.parse(elementoInterno.getTextContent());
                    break;
                case "TerminaActual" :
                    terminaActual = LocalDateTime.parse(elementoInterno.getTextContent());
                    break;

                // Esto no es ideal, pero creemos que es la manera
                // mas prolija de hacerlo.
                // Por la manera en la que estan hechos las frecuencia, no
                // tensmos manera de poder hacer algo generico. Incluso en
                // el caso de reemplazar la interfaz por una clase abstracta,
                // los Constructores son tan distintos que terminaria resultando a un conjunto
                // de ifs de cualquier manera.
                // :(

                // RepeticionInfinita se usa como un valor momentaneo
                case "FrecuenciaMensual":
                    frecuenciaEncontrada = new FrecuenciaMensual(new RepeticionInfinita());
                    break;

                case "FrecuenciaDiaria":
                    frecuenciaEncontrada = new FrecuenciaDiaria(Integer.parseInt(elementoInterno.getTextContent()), new RepeticionInfinita());
                    break;

                case "FrecuenciaAnual":
                    frecuenciaEncontrada = new FrecuenciaAnual(new RepeticionInfinita());
                    break;

                case "FrecuenciaSemanal":
                    String[] diasDeLaSemanaString = elementoInterno.getTextContent().split(",");
                    DayOfWeek[] diasDeLaSemana = new DayOfWeek[diasDeLaSemanaString.length];

                    for (int j = 0; j < diasDeLaSemanaString.length; j++) {
                        diasDeLaSemana[j] = DayOfWeek.valueOf(diasDeLaSemanaString[j]);
                    }

                    frecuenciaEncontrada = new FrecuenciaSemanal(diasDeLaSemana, new RepeticionInfinita());
                    break;

                case "RepeticionFecha":
                    repeticionEncontrada = new RepeticionFecha(LocalDateTime.parse(elementoInterno.getTextContent()));
                    break;

                case "RepeticionInfinita":
                    repeticionEncontrada = new RepeticionInfinita();
                    break;

                case "RepeticionCantVeces":
                    String[] repeticionCantVeceString = elementoInterno.getTextContent().split("@");
                    int cantidadDeRepeticionesMaximas = Integer.valueOf(repeticionCantVeceString[0]);
                    LocalDateTime fechaComienzo = LocalDateTime.parse(repeticionCantVeceString[2]);

                    try {
                        int cadaCuantosDias = Integer.valueOf(repeticionCantVeceString[1]);
                        repeticionEncontrada = new RepeticionCantVeces(cantidadDeRepeticionesMaximas, cadaCuantosDias, fechaComienzo);
                    }
                    //Esto pasa cuando es por dias de la semana y no por
                    //cantidad dias
                    catch (IllegalArgumentException e) {
                        String[] diasDeLaSemanaStrings = repeticionCantVeceString[1].split(",");

                        DayOfWeek[] diasDeLaSemanas = new DayOfWeek[diasDeLaSemanaStrings.length];
                        for (int j = 0; j < diasDeLaSemanaStrings.length; j++) {
                            diasDeLaSemanas[j] = DayOfWeek.valueOf(diasDeLaSemanaStrings[j]);
                        }

                        repeticionEncontrada = new RepeticionCantVeces(cantidadDeRepeticionesMaximas, diasDeLaSemanas, fechaComienzo);
                    }

                    break;
                }
            }
        }
        this.frecuencia = frecuenciaEncontrada;
        this.frecuencia.cambiarRepeticion(repeticionEncontrada);
    }
    //--------- Metodos ---------
}
