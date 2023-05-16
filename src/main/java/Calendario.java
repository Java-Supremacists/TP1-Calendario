import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.time.temporal.ChronoUnit; //Libreria para formatear días en LocalDateTime
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Calendario {
    //--------- Atributos ---------

    private final HashMap<Tarea,Alarmas> listaTareas = new HashMap<>();
    private final HashMap<Evento,Alarmas> listaEventos = new HashMap<>();
    private LocalDateTime maximaAlarmaActual = null;

    //--------- Atributos ---------

    //--------- Constructores ---------

    //--------- Constructores ---------

    //--------- Métodos ---------

    public int crearTarea(LocalDateTime termina) {
        Tarea nuevaTarea = new Tarea(termina);
        listaTareas.put(nuevaTarea,new Alarmas());
        return nuevaTarea.hashCode();
    }
    public int crearEvento(LocalDateTime arranque, LocalDateTime termina) {
        Evento nuevoEvento = new Evento(arranque,termina);
        listaEventos.put(nuevoEvento,new Alarmas());
        return nuevoEvento.hashCode();
    }
    public int crearTarea(String nombre, String descripcion, boolean esCompleto,LocalDateTime termina) {
        Tarea nuevaTarea = new Tarea(nombre,descripcion,esCompleto,termina);
        listaTareas.put(nuevaTarea,new Alarmas());
        return nuevaTarea.hashCode();
    }
    public int crearEvento(String nombre, String descripcion, boolean esCompleto,LocalDateTime arranque, LocalDateTime termina) {
        Evento nuevoEvento = new Evento(nombre,descripcion,esCompleto,arranque,termina);
        listaEventos.put(nuevoEvento,new Alarmas());
        return nuevoEvento.hashCode();
    }
    public LocalDateTime proximaAlarma() {
        if (maximaAlarmaActual == null) {
            for (Alarmas r : listaEventos.values()) {
                if (!r.quedanAlarmas()){
                    continue;
                }
                LocalDateTime alarm = r.primerAlarmaASonar();
                if (maximaAlarmaActual==null) {
                    maximaAlarmaActual = alarm;
                } else if (maximaAlarmaActual.isAfter(alarm)) {
                    maximaAlarmaActual = alarm;
                }

            }
            for (Alarmas r : listaTareas.values()) {
                if (!r.quedanAlarmas()){
                    continue;
                }
                LocalDateTime alarm = r.primerAlarmaASonar();
                if (maximaAlarmaActual==null) {
                    maximaAlarmaActual = alarm;
                } else if (maximaAlarmaActual.isAfter(alarm)) {
                    maximaAlarmaActual = alarm;
                }
            }
        }
        return maximaAlarmaActual;

    }
    public ArrayList<Activities> sonarAlarmas() {
        ArrayList<Activities> retorno = new ArrayList<>();
        if (maximaAlarmaActual != null) {
            for (Evento ev : listaEventos.keySet()) {
                Alarmas r = listaEventos.get(ev);
                LocalDateTime alarmaProxima = r.primerAlarmaASonar();
                if (alarmaProxima.equals(maximaAlarmaActual)) {
                    retorno.add(ev);
                    r.eliminarAlarma(maximaAlarmaActual);
                }
            }
            for (Tarea t : listaTareas.keySet()) {
                Alarmas r = listaTareas.get(t);
                LocalDateTime alarmaProxima = r.primerAlarmaASonar();
                if (alarmaProxima.equals(maximaAlarmaActual)) {
                    retorno.add(t);
                    r.eliminarAlarma(maximaAlarmaActual);
                }
            }
            maximaAlarmaActual = null;
        }
        return retorno;
    }
    public Activities obtenerActividad(int ID) {
        for (Evento e: listaEventos.keySet()) {
            if (e.hashCode() == ID) {
                return e;
            }
        }
        for (Tarea t: listaTareas.keySet()) {
            if (t.hashCode() == ID) {
                return t;
            }
        }
        return null;
    }
    public Evento obtenerEvento(int ID) {
        for (Evento e: listaEventos.keySet()) {
            if (e.hashCode() == ID) {
                return e;
            }
        }
        return null;
    }
    public Tarea obtenerTarea(int ID) {
        for (Tarea t: listaTareas.keySet()) {
            if (t.hashCode() == ID) {
                return t;
            }
        }
        return null;
    }
    public void modificarActividadNombre(int ID,String nombre) {
        var act = this.obtenerActividad(ID);
        if (act!= null) {
            act.setName(nombre);
        }
    }
    public void modificarActividadDescripcion(int ID, String descripcion) {
        var act = this.obtenerActividad(ID);
        if (act!= null) {
            act.setDescription(descripcion);
        }
    }
    public void modificarActividadEsDiaEntero(int ID, boolean esDiaCompleto) {
        var act = this.obtenerActividad(ID);
        if (act!= null) {
            act.setEsDiaCompleto(esDiaCompleto);
        }
    }
    public void modificarEventoPlazoTemporal(int ID, LocalDateTime arrancaNuevo, LocalDateTime terminaNuevo) {
        var ev = this.obtenerEvento(ID);
        if (ev!= null) {
            ev.setArranque(arrancaNuevo);
            ev.setTermina(terminaNuevo);
        }
    }
    public void modificarEventoFrecuencia(int ID, Frecuencia frecuenciaNueva) {
        var ev = this.obtenerEvento(ID);
        if (ev!= null) {
            ev.setFrecuencia(frecuenciaNueva);
            listaEventos.get(ev).mantenerAlarmas(frecuenciaNueva != null);
        }
    }
    public void modificarTareaPlazoTemporal(int ID,LocalDateTime terminaNuevo) {
        var t = this.obtenerTarea(ID);
        if (t != null) {
            t.setTermina(terminaNuevo);
        }
    }
    public void modificarTareaCompletarODescompletar(int ID) {
        var t = this.obtenerTarea(ID);
        if (t != null) {
            t.marcarCompleta();
        }
    }
    public void modificarActividadAgregarAlarma(int ID, Plazo s) {
        var act = this.obtenerActividad(ID);
        if (act!= null && s!= null) {
            var alarma = act.cuandoEmpieza().minus(s.elHorarioEstablecido());
            if (listaEventos.containsKey(act)){
                listaEventos.get(act).agregarAlarma(alarma);
            }else {
                listaTareas.get(act).agregarAlarma(alarma);
            }
        }
    }
    public void modificarActividadAgregarAlarma(int ID, LocalDateTime alarma) {
        var act = this.obtenerActividad(ID);
        if (act!= null) {
            if (listaEventos.containsKey(act)){
                listaEventos.get(act).agregarAlarma(alarma);
            }else {
                listaTareas.get(act).agregarAlarma(alarma);
            }
        }

    }
    public void modificarActividadAgregarAlarma(int ID, List<LocalDateTime> alarma) {
        var act = this.obtenerActividad(ID);
        if (act!= null) {
            if (listaEventos.containsKey(act)){
                listaEventos.get(act).agregarAlarma(alarma);
            }else {
                listaTareas.get(act).agregarAlarma(alarma);
            }
        }
    }
    public void modificarActividadEliminarAlarma(int ID, LocalDateTime alarma) {
        var act = this.obtenerActividad(ID);
        if (act != null) {
            if (listaEventos.containsKey(act)){
                listaEventos.get(act).eliminarAlarma(alarma);
            }else {
                listaTareas.get(act).eliminarAlarma(alarma);
            }
        }
    }
    public void modificarActividadEliminarAlarma(int ID, Plazo s) {
        var act = this.obtenerActividad(ID);
        if (act!= null && s!= null) {
            var alarma = act.cuandoEmpieza().minus(s.elHorarioEstablecido());
            if (listaEventos.containsKey(act)){
                listaEventos.get(act).eliminarAlarma(alarma);
            }else {
                listaTareas.get(act).eliminarAlarma(alarma);
            }
        }
    }
    public void eliminarEvento(int ID) {
        var ev = obtenerEvento(ID);
        if (ev!=null) {
            listaEventos.remove(ev);
        }
    }
    public void eliminarTarea(int ID) {
        var t = obtenerTarea(ID);
        if (t!=null) {
            listaTareas.remove(t);
        }
    }

    //Esta funcion va a devolver un array de ints que representan el ID
    //de todos los eventos que están dentro del rango
    //Esta funcion no es la más eficiente del mundo, es un doble for
    //se podria implementar una funcion llamada "está en el rango" parecida a
    //"cae el día"
    public ArrayList<Evento> eventosEnRango(LocalDateTime comienzo, LocalDateTime fin) {
        var listaEventosEnRango = new ArrayList<Evento>();

        long cantDias = comienzo.until(fin, ChronoUnit.DAYS);
        for (Evento e :listaEventos.keySet()) {
            LocalDateTime diaAChequear = comienzo;
            //Chequeo todos los días que hay entre comienzo y fin
            for (int i = 0 ; i < cantDias ; i++ ) {
                diaAChequear = diaAChequear.plusDays(1);
                if (e.caeElDia(diaAChequear)) {
                    listaEventosEnRango.add(e);
                    break;
                }
            }
        }
        return listaEventosEnRango;
    }
    public void guardado(String direccionDeArchivoConNombre){
        DocumentBuilderFactory creador = DocumentBuilderFactory.newInstance();


        try {
            // Create a DocumentBuilder
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Create a new XML document
            Document doc = docBuilder.newDocument();
            Element calendario = doc.createElement("Calendario");

            for (Evento ev : listaEventos.keySet()){
                Element eventElement = doc.createElement("Evento");
                Alarmas r = listaEventos.get(ev);
                ev.guardar(eventElement,doc);
                r.guardar(eventElement,doc);
                calendario.appendChild(eventElement);
            }

            //aca va la implementacion de guardado de Tarea

            doc.appendChild(calendario);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(direccionDeArchivoConNombre );
            transformer.transform(source, result);

            System.out.println("XML file created successfully.");
        } catch (TransformerException | DOMException | ParserConfigurationException e) {
            //algo salió mal en la creacion o en la transformacion
            e.printStackTrace();
        }
    }

}
