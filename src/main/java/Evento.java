public class Evento implements Actividad{
    private final String name;
    private final String description;
    //private final LocalDateTime localDateTimeFinal;
    // private final ArrayList<LocalDateTime> alarm;

    public Evento(String name, String description) {
        this.name = name;
        this.description = description;
        //this.localDateTimeFinal = localDateTimeFinal;
        // this.alarm = alarm;
    }

	public String getTitulo() {
		return this.name;
	}

	public String getDescripcion(){
		return this.description;
	}

	public boolean esDiaCompleto(){
		return false;
	}


}
