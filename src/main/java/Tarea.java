import java.time.LocalDateTime;
import java.util.ArrayList;

public class Tarea extends Activities {
    private final LocalDateTime termina;
    public Tarea(String name, String description, ArrayList<LocalDateTime> alarm, boolean isComplete, LocalDateTime termina) {
        super(name, description, alarm, isComplete);
        this.termina = termina;
    }

    @Override
    public LocalDateTime sonarPrimeraAlarma() {
        LocalDateTime maxAlarm = alarm.get(alarm.size());
        return termina.minus(maxAlarm);
    }

    @Override
    public ArrayList<LocalDateTime> horariosAlarmas() {
        return null;
    }
}
