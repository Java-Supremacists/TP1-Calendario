public class Tarea implements Actividad{
    private final String name;
    private final String description;
    //private final LocalDateTime localDateTimeFinal;
    // private final ArrayList<LocalDateTime> alarm;
    private final boolean isComplete;

    public Tarea(String name, String description, boolean isComplete) {
        this.name = name;
        this.description = description;
        //this.localDateTimeFinal = localDateTimeFinal;
        // this.alarm = alarm;
        this.isComplete = isComplete;
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
