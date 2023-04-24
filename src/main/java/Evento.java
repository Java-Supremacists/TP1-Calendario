import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Evento extends Activities {
	//--------- Atributos ---------

	private LocalDateTime arranquePrincipio;
	private LocalDateTime terminaPrincipio;
	private LocalDateTime arranqueActual;
	private LocalDateTime terminaActual;
	private Frecuencia frecuencia;

	//--------- Atributos ---------

	//--------- Constructores ---------
	public Evento(LocalDateTime arranque, LocalDateTime termina ) {
		super();
		this.arranquePrincipio = arranque;
		this.arranqueActual = arranque;
		this.terminaPrincipio = termina;
		this.terminaActual = termina;
	}

	//--------- Metodos ---------

	@Override
	public LocalDateTime cuandoEmpieza(){
		return this.arranqueActual;
	}
	@Override
	public LocalDateTime cuandoTermina() {
		return this.terminaActual;
	}
	public boolean caeElDia(LocalDateTime diaEspecifico){
		//Averiguo si la frecuencia hace que el evento caiga el día pedido
		boolean caeElDiaPedido = this.frecuencia.dadoComienzoCaeElDia(this.arranquePrincipio, diaEspecifico);
		return caeElDiaPedido;
	}
	public LocalDateTime proximoEventoMasCercanoAFechaEspecifica(LocalDateTime diaEspecifico) {
		LocalDateTime proximoEvento;
		proximoEvento = this.frecuencia.proximoEventoMasCercanoAFechaEspecifica(this.arranquePrincipio, diaEspecifico);
		return proximoEvento;
	}
	public void actualizarEvento(){
		if (LocalDateTime.now().isAfter(this.terminaActual)&& alarm.repiteLasAlarmas()){
			if (alarm.quedanAlarmas()){
				while (alarm.quedanAlarmas()){
					alarm.sonarAlarma();
				}
			}
			var fechaAnteriorInicio = this.arranqueActual;
			var fechaAnteriorFinal = this.terminaActual;
			this.arranqueActual = this.proximoEventoMasCercanoAFechaEspecifica(fechaAnteriorInicio);
			this.terminaActual = this.proximoEventoMasCercanoAFechaEspecifica(fechaAnteriorFinal);
			long diferenciaArranques = fechaAnteriorInicio.until(arranqueActual, ChronoUnit.DAYS);
			alarm.actualizarAlarmas(diferenciaArranques);
		}
	}
	public void setArranque(LocalDateTime arranque) {
		this.arranquePrincipio = arranque;
		this.arranqueActual = arranque;
	}
	public void setTermina(LocalDateTime termina) {
		this.terminaPrincipio = termina;
		this.terminaActual = termina;
	}
	public void setFrecuencia(Frecuencia frecuenciaNueva){
		frecuencia = frecuenciaNueva;
		alarm.mantenerAlarmas(frecuenciaNueva != null);
	}

	//--------- Metodos ---------
}
