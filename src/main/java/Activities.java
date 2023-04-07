import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Activities {
    enum tipo {TAREA,EVENTO}
    protected final String name;
    protected final String description;
    //private final LocalDateTime localDateTimeFinal;
    protected final ArrayList<LocalDateTime> alarm;
    protected final boolean isComplete;
    public Activities(String name, String description, ArrayList<LocalDateTime> alarm, boolean isComplete) {
        this.name = name;
        this.description = description;
        //this.localDateTimeFinal = localDateTimeFinal;
        this.alarm = alarm;
        this.isComplete = isComplete;
    }
    public abstract LocalDateTime primeraAlarma();
    public abstract void sonarPrimerAlarma();
    public abstract tipo type();
    public abstract LocalDateTime termina();
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public boolean isComplete() {
        return isComplete;
    }
}
