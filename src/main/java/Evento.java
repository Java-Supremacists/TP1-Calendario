import java.time.LocalDateTime;
import java.util.ArrayList;

public class Evento extends Activities {
	//--------- Atributos ---------

	private LocalDateTime arranquePrincipio;
	private LocalDateTime terminaPrincipio;
	private LocalDateTime arranqueActual;
	private LocalDateTime terminaActual;
	private Frecuencia frecuencia;

	//--------- Atributos ---------

	//--------- Constructores ---------
	public Evento(String name, String description, ArrayList<LocalDateTime> alarm, boolean isComplete, LocalDateTime arranque, LocalDateTime termina, Frecuencia frecuencia) {
		super(name, description, alarm, isComplete);
		this.arranquePrincipio = arranque;
		this.arranqueActual = arranque;
		this.terminaPrincipio = termina;
		this.terminaActual = termina;
		this.frecuencia = frecuencia;

		// this.ultimaDiaDelEvento = this.frecuencia.finDeLasRepeticionesDadaFecha(this.arranque);
	}

	//--------- Metodos ---------

	@Override
	public tipo type() {
		return tipo.EVENTO;
	}
	@Override
	public LocalDateTime cuandoEmpieza(){
		return this.arranqueActual;
	}
	@Override
	public LocalDateTime cuandoTermina() {
		return this.terminaActual;
	}
	public boolean caeElDia(LocalDateTime diaEspecifico){
		//Averiguo si la frecuencia hace que el evento caiga el dia pedido
		boolean caeElDiaPedido = this.frecuencia.dadoComienzoCaeElDia(this.arranquePrincipio, diaEspecifico);
		return caeElDiaPedido;
	}
	public LocalDateTime proximoEventoMasCercanoAFechaEspecifica(LocalDateTime diaEspecifico) {
		LocalDateTime proximoEvento;
		proximoEvento = this.frecuencia.proximoEventoMasCercanoAFechaEspecifica(this.arranquePrincipio, diaEspecifico);
		return proximoEvento;
	}
	public void actualizarEvento(){
		if (LocalDateTime.now().isAfter(this.terminaActual)){
			if (alarm.quedanAlarmas()){
				while (alarm.quedanAlarmas()){
					alarm.sonarAlarma();
				}
			}
			var fechaAnteriorInicio = this.arranqueActual;
			var fechaAnteriorFinal = this.terminaActual;
			this.arranqueActual = this.proximoEventoMasCercanoAFechaEspecifica(fechaAnteriorInicio);
			this.terminaActual = this.proximoEventoMasCercanoAFechaEspecifica(fechaAnteriorFinal);
			/*if (){
				alarm.actualizarAlarmas();
			}else{*/
			LocalDateTime diferenciaArranques = arranqueActual.minusYears(fechaAnteriorInicio.getYear()).minusMonths(fechaAnteriorInicio.getMonthValue()).minusDays(fechaAnteriorInicio.getDayOfMonth()).minusHours(fechaAnteriorInicio.getHour()).minusMinutes(fechaAnteriorInicio.getMinute());
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

	//--------- Metodos ---------
}
