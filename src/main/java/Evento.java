import java.time.LocalDateTime;
import java.util.ArrayList;

public class Evento extends Activities {
	//--------- Atributos ---------
	private LocalDateTime arranque;
	private LocalDateTime termina;
	//--------- Atributos ---------
	//--------- Constructores ---------
	public Evento(String name, String description, ArrayList<LocalDateTime> alarm, boolean isComplete, LocalDateTime arranque, LocalDateTime termina) {
		super(name, description, alarm, isComplete);
		this.arranque = arranque;
		this.termina = termina;
	}
	//--------- Constructores ---------
	//--------- Metodos ---------
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
	//--------- Metodos ---------
}
