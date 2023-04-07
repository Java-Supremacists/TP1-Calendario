import java.time.LocalDateTime;
import java.util.ArrayList;

public class Evento extends Activities {
	// private final ArrayList<LocalDateTime> intervalo = new ArrayList<>();

	private LocalDateTime arranque;
	private LocalDateTime termina;


	public Evento(String name, String description, ArrayList<LocalDateTime> alarm, boolean isComplete, LocalDateTime arranque, LocalDateTime termina) {
		super(name, description, alarm, isComplete);
		this.arranque = arranque;
		this.termina = termina;
	}
	//private final LocalDateTime localDateTimeFinal;

	// @Override
	// public LocalDateTime primeraAlarma() {
	// 	LocalDateTime maxAlarm = alarm.get(alarm.size()-1);
	// 	return this.intervalo.get(0).minus(maxAlarm);
	// }

	// @Override
	// public void sonarPrimerAlarma() {
	// 	alarm.remove(alarm.size()-1);
	// }

	@Override
	public tipo type() {
		return tipo.EVENTO;
	}

	@Override
	public LocalDateTime cuandoEmpieza(){
		return this.arranque;
	}
	@Override
	public LocalDateTime cuandoTermina() {
		return this.termina;
	}
}
