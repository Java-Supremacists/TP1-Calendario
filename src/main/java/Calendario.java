import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;


public class Calendario {
    //--------- Atributos ---------
    private final HashMap<Integer,Activities> listaActividades = new HashMap<>();
    private int IDActual = 0; //En lo comentado digo de ni usar esto
    private LocalDateTime maximaAlarmaActual = null;
    //si las alarmas colisionan van a haber más de una alarma, este guarda los ID en el array

    //--------- Atributos ---------

    //--------- Constructores ---------

    //--------- Constructores ---------

    //--------- Metodos ---------
    /*
    public int crearTarea(LocalDateTime termina){
        Tarea nuevaTarea = new Tarea(termina);
        listaTareas.add(nuevaTarea);
        """int retorno = IDActual;//estas lineas borradas, ya que no vamos a usar un ID-ACTUAL
        IDActual++"""
        return nuevaTarea.hashCode();

    }
    public int crearEvento(LocalDateTime arranque, LocalDateTime termina){
        Evento nuevoEvento = new Evento( arranque,  termina);
        listaEventos.add(nuevoEvento); //que sea un array
        """int retorno = IDActual; //estas lineas borradas, ya que no vamos a usar un ID-ACTUAL
        IDActual++"""
        return nuevoEvento.hashCode();
    }
    public Activities obtenerActividad(int ID) throws IllegalAccessError{
        for (Evento e: listaEventos){
            if (e.hashCode() == ID)){
                return e
            }
        }
        for (Tarea t: listaEventos){
            if (t.hashCode() == ID)){
                return t
            }
        }
        return null
    }
    public void modificarActividadNombre(int ID,String nombre){
        var act = this.obtenerActividad(ID)
        act.setName(nombre)
    }
    public void modificarActividadDescripcion(int ID, String descripcion){
        var act = this.obtenerActividad(ID)
        act.setDescription(descripcion)
    }
    public void modificarActividadEsDiaEntero(int ID, boolean esDiaCompleto){
        var act = this.obtenerActividad(ID)
        act.setComplete(esDiaCompleto)
    }
    public void modificarActividadAgregarAlarma(int ID, String duracion){
        Duration tiempoAnterior = PlazoAnterior.elHorarioEstablecido(duracion)
        var act = this.obtenerActividad(ID)
        LocalDateTime arranca = act.cuandoEmpieza()
        LocalDateTime alarma = arranca.minus(tiempoAnterior)
        act.getAlarmas().agregarAlarma(alarma)
    }
     */
    public int crearTarea(String nombre, String description, ArrayList<LocalDateTime> alarm,  boolean esDiaCompleto, LocalDateTime termina){
        Tarea nuevaTarea = new Tarea(nombre, description, alarm, esDiaCompleto, termina);
        listaActividades.put(IDActual,nuevaTarea);
        int retorno = IDActual;
        IDActual++;
        return retorno;

    }
    public int crearEvento(String nombre, String description, ArrayList<LocalDateTime> alarm,  boolean esDiaCompleto,LocalDateTime arranque, LocalDateTime termina){
        Evento nuevoEvento = new Evento(nombre, description, alarm, esDiaCompleto, arranque, termina);
        listaActividades.put(IDActual,nuevoEvento);
        int retorno = IDActual;
        IDActual++;
        return retorno;
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
    public Activities obtenerActividad(int ID) throws IllegalAccessError{
        return listaActividades.get(ID);
    }
}
