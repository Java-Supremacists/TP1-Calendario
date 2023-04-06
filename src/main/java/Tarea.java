public class Tarea implements Actividad{
	private final String nombre;
	private final String description;
	private final boolean esDiaCompleto;
	//private final LocalDateTime localDateTimeFinal;
	// private final ArrayList<LocalDateTime> alarm;

	public Tarea(String nombre, String description, boolean esDiaCompleto) {
		this.nombre = nombre;
		this.description = description;
		//this.localDateTimeFinal = localDateTimeFinal;
		// this.alarm = alarm;
		this.esDiaCompleto = esDiaCompleto;
	    }

	public String getTitulo() {
		return this.nombre;
	}

	public String getDescripcion(){
		return this.description;
	}

	public boolean esDiaCompleto(){
		return this.esDiaCompleto;
	}


}
