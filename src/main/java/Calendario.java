public class Calendario {
	private String nombre;
	private Actividad[] listaActividades;


	public Calendario(String nombreCalendario, Actividad[] listaActividades) {
		this.nombre = nombreCalendario;
		this.listaActividades = listaActividades;
	}

	// public TAREOTA(String nombreCalendario) {
	// 	this.nombre = nombreCalendario;
		// this.listaActividades = listaActividades;
	// }
	
	public String tituloPrimeraActividad(){
		return this.listaActividades[0].getTitulo();
	}

	public String tituloSegundaActividad(){
		return this.listaActividades[1].getTitulo();
	}


}
