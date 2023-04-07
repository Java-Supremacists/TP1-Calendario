import java.time.LocalDateTime;
import java.util.ArrayList;


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

    public void crearEvento(String nombre, String description, ArrayList<LocalDateTime> alarm,  boolean esDiaCompleto,LocalDateTime arranque, LocalDateTime termina){
	    var nuevoEvento = new Evento(nombre, description, alarm,  esDiaCompleto, arranque, termina);
	    this.listaEventos.add(nuevoEvento);
    }

    public Tarea obtenerTareaPorIndice(int ID){
	    return this.listaTareas.get(ID);
    }
	
}
