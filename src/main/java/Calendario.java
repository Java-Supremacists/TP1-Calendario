import java.time.LocalDateTime;
import java.util.ArrayList;
// import java.time.DayOfWeek;
import java.util.HashMap;


public class Calendario {
    //--------- Atributos ---------

    private final ArrayList<Tarea> listaTareas = new ArrayList<>();
    private final ArrayList<Evento> listaEventos = new ArrayList<>();
    private final HashMap<Integer,Activities> listaActividades = new HashMap<>();
    private int IDActual = 0; //En lo comentado digo de ni usar esto
    private LocalDateTime maximaAlarmaActual = null;
    //si las alarmas colisionan van a haber más de una alarma, este guarda los ID en el array

    //--------- Atributos ---------

    //--------- Constructores ---------

    //--------- Constructores ---------

    //--------- Metodos ---------

    /*

    *public void modificarActividadAgregarAlarma(int ID, LocalDateTime alarma){
        var act = this.obtenerActividad(ID);
        act.getAlarmas().agregarAlarma(alarma);
    }

    public void modificarActividadAgregarAlarma(int ID, LocalDateTime alarma){
        var act = this.obtenerActividad(ID);
        act.getAlarmas().agregarAlarma(alarma);
    }

    //
    //Constructores con repeticion semanal
    public void crearEvento(String nombre, String description, ArrayList<LocalDateTime> alarm,  boolean esDiaCompleto,LocalDateTime arranque, LocalDateTime termina, Frecuencia frecuencia){
	var nuevoEvento = new Evento(nombre, description, alarm,  esDiaCompleto, arranque, termina, frecuencia);
	this.listaEventos.add(nuevoEvento);
    }

     */

    public int crearTarea(LocalDateTime termina){
        Tarea nuevaTarea = new Tarea(termina);
        listaTareas.add(nuevaTarea);
        return nuevaTarea.hashCode();
    }
    public int crearEvento(LocalDateTime arranque, LocalDateTime termina){
        Evento nuevoEvento = new Evento(arranque,termina);
        listaEventos.add(nuevoEvento);
        return nuevoEvento.hashCode();
    }
    public LocalDateTime proximaAlarma(){
        if (maximaAlarmaActual== null){
            for (Activities actividad : listaActividades.values()){
                LocalDateTime alarmaProxima = actividad.ultimaAlarma();
                if (maximaAlarmaActual.isAfter(alarmaProxima)){
                    maximaAlarmaActual = alarmaProxima;
                }
            }
        }
        return maximaAlarmaActual;

    }
    public ArrayList<Activities> sonarAlarmas(){
        ArrayList<Activities> retorno = new ArrayList<>();
        for (Activities act : listaActividades.values()){
            LocalDateTime alarmaProxima = act.ultimaAlarma();
            if (alarmaProxima.equals(maximaAlarmaActual)){
                retorno.add(act);
            }
        }
        return retorno;
    }
    public Activities obtenerActividad(int ID){
        for (Evento e: listaEventos){
            if (e.hashCode() == ID){
                return e;
            }
        }
        for (Tarea t: listaTareas){
            if (t.hashCode() == ID){
                return t;
            }
        }
        return null;
    }
    public Evento obtenerEvento(int ID){
        for (Evento e: listaEventos){
            if (e.hashCode() == ID){
                return e;
            }
        }
        return null;
    }
    public Tarea obtenerTarea(int ID){
        for (Tarea t: listaTareas){
            if (t.hashCode() == ID){
                return t;
            }
        }
        return null;
    }
    public void modificarActividadNombre(int ID,String nombre){
        var act = this.obtenerActividad(ID);
        act.setName(nombre);
    }
    public void modificarActividadDescripcion(int ID, String descripcion){
        var act = this.obtenerActividad(ID);
        act.setDescription(descripcion);
    }
    public void modificarActividadEsDiaEntero(int ID, boolean esDiaCompleto){
        var act = this.obtenerActividad(ID);
        act.setComplete(esDiaCompleto);
    }
    public void modificarEventoPlazoTemporal(int ID, LocalDateTime arrancaNuevo, LocalDateTime terminaNuevo){
        var ev = this.obtenerEvento(ID);
        if (ev!= null){
            ev.setArranque(arrancaNuevo);
            ev.setTermina(terminaNuevo);
        }
    }
    public void modificarEventoFrecuencia(int ID, Frecuencia frecuenciaNueva){
        var ev = this.obtenerEvento(ID);
        if (ev!= null){
            ev.setFrecuencia(frecuenciaNueva);
        }
    }
    public void modificarTareaPlazoTemporal(int ID,LocalDateTime terminaNuevo){
        var t = this.obtenerTarea(ID);
        if (t != null){
            t.setTermina(terminaNuevo);
        }
    }
}
