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

    //--------- Metodos ---------
}
