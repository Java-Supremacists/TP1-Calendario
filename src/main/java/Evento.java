import java.time.LocalDateTime;
// import java.time.temporal.ChronoUnit; //Libreria para formatear dias en LocalDateTime
import java.util.ArrayList;

public class Evento extends Activities {
	//--------- Atributos ---------
	private LocalDateTime arranque;
	private LocalDateTime termina;
	private LocalDateTime ultimaDiaDelEvento;
	// private final int frecuencia;

	private Frecuencia frecuencia;
	// private Repeticion repeticion;
	//--------- Atributos ---------

	//--------- Constructores ---------
	public Evento(String name, String description, ArrayList<LocalDateTime> alarm, boolean isComplete, LocalDateTime arranque, LocalDateTime termina, Frecuencia frecuencia, Repeticion repeticion) {
	    super(name, description, alarm, isComplete);
	    this.arranque = arranque;
	    this.termina = termina;
	    this.frecuencia = frecuencia;
	}


	//Constructor con frecuencia
	// public Evento(String name, String description, ArrayList<LocalDateTime> alarm, boolean isComplete, LocalDateTime arranque, LocalDateTime termina, String frecuencia) {
	// 	super(name, description, alarm, isComplete);
	// 	this.arranque = arranque;
	// 	this.termina = termina;
	// 	this.frecuencia = repeticion;
	// }
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

	public boolean caeElDia(LocalDateTime diaEspecifico){
	    return this.frecuencia.dadoComienzoCaeElDia(this.arranque, diaEspecifico);
	}

	//--------- Metodos ---------
}
