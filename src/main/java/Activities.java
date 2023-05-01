import java.time.LocalDateTime;
import java.util.List;

public abstract class Activities {
    //--------- Atributos ---------

    protected String name;
    protected String description;
    protected final Alarmas alarm = new Alarmas();
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
    public LocalDateTime ultimaAlarma() {
        if (alarm.quedanAlarmas()) {
            return alarm.primerAlarmaASonar();
        }
        return null;
    }
    public void sonarUltimaAlarma() {
        if (alarm.quedanAlarmas()) {
            alarm.sonarAlarma();
        }//else
        //error
        //}
    }
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
    public void agregarAlarma(LocalDateTime alarmaNueva) {
        this.alarm.agregarAlarma(alarmaNueva);
    }
    public void agregarAlarmas(List<LocalDateTime> alarmasNuevas) {
        this.alarm.agregarAlarma(alarmasNuevas);
    }
    public void eliminarAlarma(LocalDateTime alarmaNueva) {
        this.alarm.eliminarAlarma(alarmaNueva);
    }

    //--------- Metodos ---------
}
