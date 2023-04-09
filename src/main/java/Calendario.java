import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.DayOfWeek;


public class Calendario {
    //--------- Atributos ---------

    private String nombre;
    private ArrayList<Evento> listaEventos = new ArrayList<Evento>();
    private ArrayList<Tarea> listaTareas = new ArrayList<Tarea>();

    //--------- Atributos ---------

    //--------- Constructores ---------

    public Calendario(String nombreCalendario){ 
	    this.nombre = nombreCalendario;
    }

    //--------- Constructores ---------

    //--------- Metodos ---------
    public void crearTarea(String nombre, String description, ArrayList<LocalDateTime> alarm,  boolean esDiaCompleto, LocalDateTime termina){
	    var nuevaTarea = new Tarea(nombre, description, alarm,  esDiaCompleto, termina);
	    this.listaTareas.add(nuevaTarea);
    }

    //Constructores con repeticion semanal
    public void crearEvento(String nombre, String description, ArrayList<LocalDateTime> alarm,  boolean esDiaCompleto,LocalDateTime arranque, LocalDateTime termina, Frecuencia frecuencia){
	var nuevoEvento = new Evento(nombre, description, alarm,  esDiaCompleto, arranque, termina, frecuencia);
	this.listaEventos.add(nuevoEvento);
    }

    public Tarea obtenerTareaPorIndice(int ID){
	    return this.listaTareas.get(ID);
    }

    // public Evento hayEventosElDia(LocalDateTime diaEspecificio){
	// for (int evento = 0; evento < this.listaEventos.size(); evento ++) {
	    // dee
	// }

    // }
	
}
