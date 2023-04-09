import java.time.LocalDateTime;
import java.util.ArrayList;


public class Calendario {
    //--------- Atributos ---------
    private final ArrayList<Activities> listaActividades = new ArrayList<>();
    private final Function<LocalDateTime> f=(a1,a2) -> {
        if (a1.isAfter(a2)){
            return -1;
        } else if (a1.equals(a2)) {
            return 0;
        }else {
            return 1;
        }
    };

    //--------- Atributos ---------

    //--------- Constructores ---------

    //--------- Constructores ---------

    //--------- Metodos ---------
    public void crearTarea(String nombre, String description, ArrayList<LocalDateTime> alarm,  boolean esDiaCompleto, LocalDateTime termina){
        Tarea nuevaTarea;
        if (alarm == null) {
            nuevaTarea = new Tarea(nombre, description, esDiaCompleto, termina);
        }else{
            Mergesort.mergesort(alarm,0,alarm.size()-1,f);
            nuevaTarea = new Tarea(nombre, description, alarm, esDiaCompleto, termina);
        }
        this.listaActividades.add(nuevaTarea);

    }

    public void crearEvento(String nombre, String description, ArrayList<LocalDateTime> alarm,  boolean esDiaCompleto,LocalDateTime arranque, LocalDateTime termina){
        Evento nuevoEvento;
        if (alarm == null){
            nuevoEvento = new Evento(nombre, description, esDiaCompleto, arranque, termina);
        }else {
            Mergesort.mergesort(alarm,0,alarm.size()-1,f);
            nuevoEvento = new Evento(nombre, description, alarm, esDiaCompleto, arranque, termina);
        }
        this.listaActividades.add(nuevoEvento);
    }
	
}
