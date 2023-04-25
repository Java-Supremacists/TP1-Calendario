import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Calendario {
    //--------- Atributos ---------

    private final ArrayList<Tarea> listaTareas = new ArrayList<>();
    private final ArrayList<Evento> listaEventos = new ArrayList<>();
    private LocalDateTime maximaAlarmaActual = null;

    //--------- Atributos ---------

    //--------- Constructores ---------

    //--------- Constructores ---------

    //--------- Métodos ---------

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
        if (maximaAlarmaActual == null){
            for (Evento ev : listaEventos){
                LocalDateTime alarm = ev.ultimaAlarma();
                if (maximaAlarmaActual==null){
                    maximaAlarmaActual = alarm;
                } else if (maximaAlarmaActual.isAfter(alarm)) {
                    maximaAlarmaActual = alarm;
                }

            }
            for (Tarea t : listaTareas){
                LocalDateTime alarm = t.ultimaAlarma();
                if (maximaAlarmaActual==null){
                    maximaAlarmaActual = alarm;
                } else if (maximaAlarmaActual.isAfter(alarm)) {
                    maximaAlarmaActual = alarm;
                }
            }
        }
        return maximaAlarmaActual;

    }
    public ArrayList<Activities> sonarAlarmas(){
        ArrayList<Activities> retorno = new ArrayList<>();
        if (maximaAlarmaActual != null){
            for (Evento ev : listaEventos){
                LocalDateTime alarmaProxima = ev.ultimaAlarma();
                if (alarmaProxima.equals(maximaAlarmaActual)){
                    retorno.add(ev);
                    ev.sonarUltimaAlarma();
                }
            }
            for (Tarea t : listaTareas){
                LocalDateTime alarmaProxima = t.ultimaAlarma();
                if (alarmaProxima.equals(maximaAlarmaActual)){
                    retorno.add(t);
                    t.sonarUltimaAlarma();
                }
            }
            maximaAlarmaActual = null;
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
        if (act!= null) {
            act.setName(nombre);
        }
    }
    public void modificarActividadDescripcion(int ID, String descripcion){
        var act = this.obtenerActividad(ID);
        if (act!= null) {
            act.setDescription(descripcion);
        }
    }
    public void modificarActividadEsDiaEntero(int ID, boolean esDiaCompleto){
        var act = this.obtenerActividad(ID);
        if (act!= null) {
            act.setComplete(esDiaCompleto);
        }
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
    public void modificarTareaCompletarODescompletar(int ID){
        var t = this.obtenerTarea(ID);
        if (t != null){
            t.marcarCompleta();
        }
    }
    public void modificarActividadAgregarAlarma(int ID, String s){
        var act = this.obtenerActividad(ID);
        var duracionAnterior = PlazoAnterior.compararHorariosDescriptos(s);
        if (act!= null && duracionAnterior!= null){
            var alarma = act.cuandoEmpieza().minus(duracionAnterior.elHorarioEstablecido());
            act.agregarAlarma(alarma);
        }
    }
    public void modificarActividadAgregarAlarma(int ID, LocalDateTime alarma){
        var act = this.obtenerActividad(ID);
        if (act!= null){
            act.agregarAlarma(alarma);
        }

    }
    public void modificarActividadAgregarAlarma(int ID, List<LocalDateTime> alarma){
        var act = this.obtenerActividad(ID);
        if (act!= null){
            act.agregarAlarmas(alarma);
        }
    }
    public void modificarActividadEliminarAlarma(int ID, LocalDateTime alarma){
        var act = this.obtenerActividad(ID);
        if (act != null){
            act.eliminarAlarma(alarma);
        }
    }
    public void modificarActividadEliminarAlarma(int ID, String s){
        var act = this.obtenerActividad(ID);
        var duracionAnterior = PlazoAnterior.compararHorariosDescriptos(s);
        if (act!= null && duracionAnterior!= null){
            var alarma = act.cuandoEmpieza().minus(duracionAnterior.elHorarioEstablecido());
            act.eliminarAlarma(alarma);
        }
    }
    public void eliminarEvento(int ID){
        var ev = obtenerEvento(ID);
        if (ev!=null){
            listaEventos.remove(ev);
        }
    }
    public void eliminarTarea(int ID){
        var t = obtenerTarea(ID);
        if (t!=null){
            listaTareas.remove(t);
        }
    }

}
