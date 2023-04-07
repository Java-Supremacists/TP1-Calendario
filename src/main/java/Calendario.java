import java.time.LocalDateTime;
import java.util.ArrayList;


public class Calendario {
	private String nombre;
	private ArrayList<Evento> listaEventos = new ArrayList<Evento>();
	private ArrayList<Tarea> listaTareas = new ArrayList<Tarea>();


	public Calendario(String nombreCalendario){ 
		this.nombre = nombreCalendario;
	}

	public void crearTarea(String nombre, String description, ArrayList<LocalDateTime> alarm,  boolean esDiaCompleto, LocalDateTime termina){
		var nuevaTarea = new Tarea(nombre, description, alarm,  esDiaCompleto, termina);
		this.listaTareas.add(nuevaTarea);
	}

	public Tarea obtenerTareaPorIndice(int ID){
		return this.listaTareas.get(ID);
	}
	
}
