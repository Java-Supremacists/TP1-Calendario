import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Activities {
    //--------- Atributos ---------

    protected String name;
    protected String description;
    protected boolean esDiaCompleto;

    //--------- Atributos ---------

    //--------- Constructores ---------

    public Activities() {
        this.name = "";
        this.description = "";
        this.esDiaCompleto = false;
    }
    public Activities(String nombre, String descripcion, boolean esCompleto) {
        this.name = nombre;
        this.description = descripcion;
        this.esDiaCompleto = esCompleto;
    }

    //--------- Constructores ---------

    //--------- Metodos ---------

    public abstract LocalDateTime cuandoTermina();
    public abstract LocalDateTime cuandoEmpieza();
    public String getTitulo() {
        return name;
    }
    public String getDescripcion() {
        return description;
    }
    public boolean esDiaEntero() {
        return esDiaCompleto;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setEsDiaCompleto(boolean esDiaCompleto) {
        this.esDiaCompleto = esDiaCompleto;
    }
    public void guardar(Element estructura, Document doc){
        Element nombreActividad = doc.createElement("Nombre");
        nombreActividad.appendChild(doc.createTextNode(name));
        estructura.appendChild(nombreActividad);

        Element descripcionActividad = doc.createElement("Descripcion");
        descripcionActividad.appendChild(doc.createTextNode(description));
        estructura.appendChild(descripcionActividad);

        Element esDiaCompletoLaActividad = doc.createElement("DeDiaCOmpleto");
        esDiaCompletoLaActividad.appendChild(doc.createTextNode("%b".formatted(esDiaCompleto)));
        estructura.appendChild(esDiaCompletoLaActividad);
    }

    //--------- Metodos ---------
}
