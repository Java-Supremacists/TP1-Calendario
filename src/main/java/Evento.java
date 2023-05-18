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
    public void guardar(Element estructura, Document doc){
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

        //frecuencia.guardar()
    }
    @Override
    public void cargar(Element Evento) {
        super.cargar(Evento);
        DateTimeFormatter formateadorDeStringALocaldatetime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        Element arranqueAlinicio = (Element) Evento.getElementsByTagName("ArranquePrincipio");
        arranquePrincipio = LocalDateTime.parse(arranqueAlinicio.getTextContent(), formateadorDeStringALocaldatetime);

        Element terminaAlinicio = (Element) Evento.getElementsByTagName("TerminaPrincipio");
        terminaPrincipio = LocalDateTime.parse(terminaAlinicio.getTextContent(), formateadorDeStringALocaldatetime);

        Element arranqueActualizado = (Element) Evento.getElementsByTagName("ArranqueActual");
        arranqueActual = LocalDateTime.parse(arranqueActualizado.getTextContent(), formateadorDeStringALocaldatetime);

        Element terminaActualizado = (Element) Evento.getElementsByTagName("ArranqueActual");
        terminaActual = LocalDateTime.parse(terminaActualizado.getTextContent(), formateadorDeStringALocaldatetime);

        //frecuencia.cargar()
    }
    //--------- Metodos ---------
}
