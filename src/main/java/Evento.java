import java.time.LocalDateTime;
import java.util.ArrayList;

public class Evento extends Activities {
	//--------- Atributos ---------

	private LocalDateTime arranque;
	private LocalDateTime termina;
<<<<<<< HEAD
	// private LocalDateTime ultimaDiaDelEvento;
	private Frecuencia frecuencia;
	
	//--------- Atributos ---------

	//--------- Constructores ---------
	public Evento(String name, String description, ArrayList<LocalDateTime> alarm, boolean isComplete, LocalDateTime arranque, LocalDateTime termina, Frecuencia frecuencia) {
	    super(name, description, alarm, isComplete);
	    this.arranque = arranque;
	    this.termina = termina;
	    this.frecuencia = frecuencia;

	    // this.ultimaDiaDelEvento = this.frecuencia.finDeLasRepeticionesDadaFecha(this.arranque);
	}

=======

	//--------- Atributos ---------

	//--------- Constructores ---------
	/*
	* public Evento(LocalDateTime arranque, LocalDateTime termina) {
		this.arranque = arranque;
		this.termina = termina;
	}
	 */
	public Evento(String name, String description, ArrayList<LocalDateTime> alarm, boolean isComplete, LocalDateTime arranque, LocalDateTime termina) {
		super(name, description, alarm, isComplete);
		this.arranque = arranque;
		this.termina = termina;
	}

	//--------- Constructores ---------

>>>>>>> pre-main
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
<<<<<<< HEAD

	public boolean caeElDia(LocalDateTime diaEspecifico){
	    //if (diaEspecifico.isAfter(this.ultimaDiaDelEvento) == true) {
		//return false; //Si cae DESPUES del ultimo dia, entonces ni nos 
			      ////molestamos en calcular si  la frecuencia hace 
			      ////que caiga el dia que me piden
		//}

	    //Averiguo si la frecuencia hace que el evento caiga el dia pedido
	    boolean caeElDiaPedido = this.frecuencia.dadoComienzoCaeElDia(this.arranque, diaEspecifico); 
	    return caeElDiaPedido;
=======
	public void setArranque(LocalDateTime arranque) {
		this.arranque = arranque;
	}
	public void setTermina(LocalDateTime termina) {
		this.termina = termina;
>>>>>>> pre-main
	}

	//--------- Metodos ---------
}
