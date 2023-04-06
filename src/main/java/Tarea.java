public class Tarea implements Actividad{
	private final String nombre;
	private final String description;
	private final boolean esDiaEntero;
	//private final LocalDateTime localDateTimeFinal;
	// private final ArrayList<LocalDateTime> alarm;
	private boolean estaCompletada;

	public Tarea(String nombre, String description, boolean esDiaEntero) {
		this.nombre = nombre;
		this.description = description;
		//this.localDateTimeFinal = localDateTimeFinal;
		// this.alarm = alarm;
		this.esDiaEntero = esDiaEntero;
	    }

	public String getTitulo() {
		return this.nombre;
	}

	public String getDescripcion(){
		return this.description;
	}

	public boolean esDiaEntero(){
		return this.esDiaEntero;
	}

	public void marcarCompleta(){
		this.estaCompletada = !this.estaCompletada;
	}

	public boolean estaCompleta(){
		return this.estaCompletada;
	}


}
