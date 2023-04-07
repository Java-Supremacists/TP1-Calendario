import java.time.LocalDateTime;
import java.util.ArrayList;

public class Tarea extends Activities {
    private final LocalDateTime termina;
    public Tarea(String name, String description, ArrayList<LocalDateTime> alarm, boolean isComplete, LocalDateTime termina) {
        super(name, description, alarm, isComplete);
        this.termina = termina;
    }

    @Override
    public LocalDateTime primeraAlarma() {
        LocalDateTime maxAlarm = alarm.get(alarm.size()-1);
        return termina.minus(maxAlarm);
    }

    @Override
    public void sonarPrimerAlarma() {
        alarm.remove(alarm.size()-1);
    }

    @Override
    public tipo type() {
        return tipo.TAREA;
    }
}
