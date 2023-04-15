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
	public Evento(String name, String description, ArrayList<LocalDateTime> alarm, boolean isComplete, LocalDateTime arranque, LocalDateTime termina, Frecuencia frecuencia) {
	    super(name, description, alarm, isComplete);
	    this.arranque = arranque;
	    this.termina = termina;
	    this.frecuencia = frecuencia;

	    this.ultimaDiaDelEvento = this.frecuencia.finDeLasRepeticionesDadaFecha(this.arranque);
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
	    if (diaEspecifico.isAfter(this.ultimaDiaDelEvento) == true) {
		return false; //Si cae DESPUES del ultimo dia, entonces ni nos molestamos en calcular si  la frecuencia hace que caiga el dia que me piden
		}

	    boolean caeElDiaPedido = this.frecuencia.dadoComienzoCaeElDia(this.arranque, diaEspecifico); //Averiguo si la frecuencia hace que el evento caiga el dia pedido
	    return caeElDiaPedido;
	}

	//--------- Metodos ---------
}
