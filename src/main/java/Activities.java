import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Activities {
    //--------- Atributos ---------

    enum tipo {TAREA,EVENTO}
    protected final String name;
    protected final String description;
    protected ArrayList<LocalDateTime> alarm;
    protected final boolean isComplete;

    //--------- Atributos ---------

    //--------- Constructores ---------

    public Activities(String name, String description, ArrayList<LocalDateTime> alarm, boolean isComplete) {
        this.name = name;
        this.description = description;
        this.alarm = alarm;
        this.isComplete = isComplete;
    }
    public Activities(String name, String description, boolean isComplete) {
        this.name = name;
        this.description = description;
        this.isComplete = isComplete;
    }

    //--------- Constructores ---------

    //--------- Metodos ---------

    public abstract tipo type();
    public abstract LocalDateTime cuandoTermina();
    public abstract LocalDateTime cuandoEmpieza();
    public LocalDateTime ultimaAlarma(){
        return alarm.get(alarm.size()-1);
    }
    public void sonarUltimaAlarma(){
        alarm.remove(alarm.size()-1);
    }
    public String getTitulo() {
        return name;
    }
    public String getDescripcion() {
        return description;
    }
    public boolean esDiaEntero() {
        return isComplete;
    }

    //--------- Metodos ---------
}
