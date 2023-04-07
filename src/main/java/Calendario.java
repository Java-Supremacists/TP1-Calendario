import java.time.LocalDateTime;
import java.util.ArrayList;


public class Calendario {
    //--------- Atributos ---------
    private final ArrayList<Activities> listaActividades = new ArrayList<>();

    //--------- Atributos ---------

    //--------- Constructores ---------

    //--------- Constructores ---------

    //--------- Metodos ---------
    public void crearTarea(String nombre, String description, ArrayList<LocalDateTime> alarm,  boolean esDiaCompleto, LocalDateTime termina){
        Tarea nuevaTarea;
        if (alarm == null) {
            nuevaTarea = new Tarea(nombre, description, esDiaCompleto, termina);
        }else{
            //mergesort(alarm);
            nuevaTarea = new Tarea(nombre, description, alarm, esDiaCompleto, termina);
        }
        this.listaActividades.add(nuevaTarea);

    }

    public void crearEvento(String nombre, String description, ArrayList<LocalDateTime> alarm,  boolean esDiaCompleto,LocalDateTime arranque, LocalDateTime termina){
        Evento nuevoEvento;
        if (alarm == null){
            nuevoEvento = new Evento(nombre, description, esDiaCompleto, arranque, termina);
        }else {
            //mergesort(alarm);
            nuevoEvento = new Evento(nombre, description, alarm, esDiaCompleto, arranque, termina);
        }
        this.listaActividades.add(nuevoEvento);
    }
	
}
