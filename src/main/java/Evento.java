import java.time.LocalDateTime;
import java.util.ArrayList;

public class Evento extends Activities {
	private final ArrayList<LocalDateTime> intervalo = new ArrayList<>();
	public Evento(String name, String description, ArrayList<LocalDateTime> alarm, boolean isComplete, LocalDateTime arranque, LocalDateTime termina) {
		super(name, description, alarm, isComplete);
		this.intervalo.add(arranque);
		this.intervalo.add(termina);
	}
	//private final LocalDateTime localDateTimeFinal;
	@Override
	public LocalDateTime sonarPrimeraAlarma() {
		LocalDateTime maxAlarm = alarm.get(alarm.size());
		return this.intervalo.get(0).minus(maxAlarm);
	}

	@Override
	public ArrayList<LocalDateTime> horariosAlarmas() {
		return null;
	}
}
