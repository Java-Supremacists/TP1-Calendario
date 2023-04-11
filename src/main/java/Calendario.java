import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;


public class Calendario {
    //--------- Atributos ---------
    private final HashMap<Integer,Activities> listaActividades = new HashMap<>();
    private int IDActual = 0;
    private final Function<LocalDateTime> f=(a1,a2) -> {
        if (a1.isAfter(a2)){
            return -1;
        } else if (a1.equals(a2)) {
            return 0;
        }else {
            return 1;
        }
    };
    private LocalDateTime maximaAlarmaActual;
    private final ArrayList<Integer> proximasAlarmas = new ArrayList<>();
    //si las alarmas colisionan van a haber más de una alarma, este guarda los ID en el array

    //--------- Atributos ---------

    //--------- Constructores ---------

    //--------- Constructores ---------

    //--------- Metodos ---------
    public int crearTarea(String nombre, String description, ArrayList<LocalDateTime> alarm,  boolean esDiaCompleto, LocalDateTime termina){
        Tarea nuevaTarea;
        if (alarm == null) {
            nuevaTarea = new Tarea(nombre, description, esDiaCompleto, termina);
        }else{
            Mergesort.mergesort(alarm,0,alarm.size()-1,f);
            nuevaTarea = new Tarea(nombre, description, alarm, esDiaCompleto, termina);
        }
        listaActividades.put(IDActual,nuevaTarea);
        int retorno = IDActual;
        IDActual++;
        return retorno;

    }

    public int crearEvento(String nombre, String description, ArrayList<LocalDateTime> alarm,  boolean esDiaCompleto,LocalDateTime arranque, LocalDateTime termina){
        Evento nuevoEvento;
        if (alarm == null){
            nuevoEvento = new Evento(nombre, description, esDiaCompleto, arranque, termina);
        }else {
            Mergesort.mergesort(alarm,0,alarm.size()-1,f);
            nuevoEvento = new Evento(nombre, description, alarm, esDiaCompleto, arranque, termina);
        }
        listaActividades.put(IDActual,nuevoEvento);
        int retorno = IDActual;
        IDActual++;
        return retorno;
    }
	public LocalDateTime proximasAlarmas(){
        if (proximasAlarmas.size() == 0){
            LocalDateTime maxAlarma = null;
            for (Activities act : listaActividades.values()) {
                if (act.quedanAlarmas()) {
                    var alarma = act.ultimaAlarma();
                    if (alarma.isBefore(maxAlarma)) {
                        maxAlarma = alarma;
                    }
                }
            }
            if (maxAlarma != null){
                maximaAlarmaActual = maxAlarma;
                for (int claves :listaActividades.keySet()){
                    if (listaActividades.get(claves).quedanAlarmas()){
                        var alarma = listaActividades.get(claves).ultimaAlarma();
                        if (alarma.equals(maxAlarma)){
                            proximasAlarmas.add(claves);
                        }
                    }
                }
            }

        }
        return maximaAlarmaActual;

    }
    public ArrayList<Activities> sonarAlarmas(){
        ArrayList<Activities> retorno = new ArrayList<>();
        for (int id: proximasAlarmas){
            retorno.add(listaActividades.get(id));
        }
        maximaAlarmaActual = null;
        return retorno;
    }
}
