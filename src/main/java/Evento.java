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
	public Evento(String name, String description, boolean isComplete, LocalDateTime arranque, LocalDateTime termina) {
		super(name, description, isComplete);
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
	public void setArranque(LocalDateTime arranque) {
		this.arranque = arranque;
	}
	public void setTermina(LocalDateTime termina) {
		this.termina = termina;
	}

	//--------- Metodos ---------
}
