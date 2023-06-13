import java.time.LocalDateTime;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Tarea extends Activities {
    //--------- Atributos ---------

    private LocalDateTime termina;
    private boolean estaCompletada;

    //--------- Atributos ---------

    //--------- Constructores ---------

    public Tarea(LocalDateTime termina) {
        super();
        this.termina = termina;
        estaCompletada = false;
    }
    public Tarea(String nombre, String descripcion, boolean esCompleto,LocalDateTime termina) {
        super(nombre, descripcion, esCompleto);
        this.termina = termina;
        estaCompletada = false;
    }
    //--------- Constructores ---------

    //--------- Metodos ---------

    public boolean estaCompleta() {
        return this.estaCompletada;
    }
    public void marcarCompleta() {
        this.estaCompletada = !this.estaCompletada;
    }
    @Override
    public LocalDateTime cuandoTermina() {
        return termina;
    }
    @Override
    public LocalDateTime cuandoEmpieza() {
        return this.cuandoTermina();
    }
    public void setTermina(LocalDateTime termina) {
        this.termina = termina;
    }

    @Override
    public void guardar(Element estructura, Document doc) {
        super.guardar(estructura, doc);

        Element estaCompleta = doc.createElement("EstaCompleta");
        estaCompleta.appendChild(doc.createTextNode(String.valueOf(this.estaCompleta())));
        estructura.appendChild(estaCompleta);

        Element termina = doc.createElement("Termina");
        termina.appendChild(doc.createTextNode(String.valueOf(this.termina)));
        estructura.appendChild(termina);
    }

    @Override
    public void cargar(Element Evento) {
        super.cargar(Evento);

        var elementosDelEvento = Evento.getChildNodes();
        for (int i = 0; i< elementosDelEvento.getLength(); i++) {
            if (elementosDelEvento.item(i) instanceof Element elementoInterno) {
                switch (elementoInterno.getTagName()) {
                case "EstaCompleta" -> this.estaCompletada = Boolean.parseBoolean(elementoInterno.getTextContent());
                case "Termina" -> termina = LocalDateTime.parse(elementoInterno.getTextContent());
                }
            }
        }
    }

    public boolean caeElDia(LocalDateTime fechaAChequear) {
        if (fechaAChequear.getDayOfYear() == this.termina.getDayOfYear()) {
            return true;
        }
        return false;



    }

    //--------- Metodos ---------
}
