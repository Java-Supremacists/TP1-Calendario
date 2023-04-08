import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit; //Libreria para formatear dias en LocalDateTime
import java.util.ArrayList;

public class Evento extends Activities {
	//--------- Atributos ---------
	private LocalDateTime arranque;
	private LocalDateTime termina;
	private final int repeticion;
	//--------- Atributos ---------
	//--------- Constructores ---------
	public Evento(String name, String description, ArrayList<LocalDateTime> alarm, boolean isComplete, LocalDateTime arranque, LocalDateTime termina) {
		super(name, description, alarm, isComplete);
		this.arranque = arranque;
		this.termina = termina;
		this.repeticion = 3;
	}


	//Constructor con frecuencia
	// public Evento(String name, String description, ArrayList<LocalDateTime> alarm, boolean isComplete, LocalDateTime arranque, LocalDateTime termina, String frecuencia) {
	// 	super(name, description, alarm, isComplete);
	// 	this.arranque = arranque;
	// 	this.termina = termina;
	// 	this.frecuencia = frecuencia;
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
	    long cantDiasHastaDiaPedido = this.arranque.until(diaEspecifico, ChronoUnit.DAYS);
	    boolean eventoCaeElDiaPedidio = (cantDiasHastaDiaPedido % this.repeticion == 0);
	    return eventoCaeElDiaPedidio;

	}
	//--------- Metodos ---------
}
