import java.time.LocalDateTime;
import java.util.ArrayList;

public class Tarea extends Activities {
    private final LocalDateTime termina;
    public Tarea(String name, String description, ArrayList<LocalDateTime> alarm, boolean isComplete, LocalDateTime termina) {
        super(name, description, alarm, isComplete);
        this.termina = termina;
    }
    @Override
    public tipo type() {
        return tipo.TAREA;
    }
    @Override
    public LocalDateTime cuandoTermina() {
        return termina;
    }
    @Override
    public LocalDateTime cuandoEmpieza() {
        return this.cuandoTermina();
    }
}
