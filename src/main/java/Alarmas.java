import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Alarmas {
    public Notificacion notificaciones;
    //--------- Atributos ---------

    private final Set<LocalDateTime> alarmas;
    private LocalDateTime alarmaMasTemprana;
    private final Set<LocalDateTime> alarmasYaSonadas;
    private boolean mantenerAlarmas = false;

    //--------- Atributos ---------

    //--------- Constructores ---------
    public Alarmas() {
        alarmaMasTemprana = null;
        alarmas = new HashSet<>();
        alarmasYaSonadas = new HashSet<>();
        notificaciones = null;
    }
    public Alarmas(boolean mantener,Notificacion notificacionesUsuario) {
        alarmaMasTemprana = null;
        alarmas = new HashSet<>();
        mantenerAlarmas = mantener;
        alarmasYaSonadas = new HashSet<>();
        notificaciones = notificacionesUsuario;
    }

    //--------- Constructores ---------

    //--------- Metodos ---------

    public int size() {
        return alarmas.size();
    }
    public void actualizarAlarmas(long cantidadDiasASumar) {
        if (cantidadDiasASumar != 0 && alarmas.size()==0 && mantenerAlarmas) {
            for (LocalDateTime alarm : alarmasYaSonadas) {
                LocalDateTime nuevaAlarm = alarm.plusDays(cantidadDiasASumar);
                alarmas.add(nuevaAlarm);
            }
            alarmasYaSonadas.clear();
            alarmasYaSonadas.addAll(alarmas);
        }
    }
    public void agregarAlarma(LocalDateTime alarmaParaAgregar) {
        if (alarmaParaAgregar== null) {
            return;
        }
        alarmas.add(alarmaParaAgregar);
        if (mantenerAlarmas) {
            alarmasYaSonadas.add(alarmaParaAgregar);
        }
        if ((alarmaMasTemprana != null) && alarmaMasTemprana.isAfter(alarmaParaAgregar)) {
            alarmaMasTemprana = alarmaParaAgregar;
        }
    }
    public void agregarAlarma(List<LocalDateTime> alarmasParaAgregar) {
        if (alarmasParaAgregar!=null) {
            for (LocalDateTime alarm : alarmasParaAgregar) {
                if (alarm== null) {
                    continue;
                }
                alarmas.add(alarm);
                if (mantenerAlarmas) {
                    alarmasYaSonadas.add(alarm);
                }
                if ((alarmaMasTemprana != null) && alarmaMasTemprana.isAfter(alarm)) {
                    alarmaMasTemprana = alarm;
                }
            }
        }


    }
    public boolean quedanAlarmas() {
        //devuelve true si todavia quedan
        return alarmas.size()!= 0;
    }
    public boolean existeAlarma(LocalDateTime a1) {
        return alarmas.contains(a1);
    }
    public void eliminarAlarma(LocalDateTime paraEliminar) {
        alarmas.remove(paraEliminar);
        alarmasYaSonadas.remove(paraEliminar);
        if (paraEliminar!= null && paraEliminar.equals(alarmaMasTemprana)) {
            alarmaMasTemprana = null;
        }
    }
    public LocalDateTime primerAlarmaASonar() {
        if (alarmaMasTemprana == null) {
            for (LocalDateTime alarm : alarmas) {
                if (alarmaMasTemprana == null) {
                    alarmaMasTemprana = alarm;
                } else if (alarmaMasTemprana.isAfter(alarm)) {
                    alarmaMasTemprana = alarm;
                }
            }
        }
        return alarmaMasTemprana;
    }
    public boolean repiteLasAlarmas() {
        return mantenerAlarmas;
    }
    public void mantenerAlarmas(boolean mantenerAlarmas) {
        this.mantenerAlarmas = mantenerAlarmas;
    }
    public void guardar(Element estructura, Document doc) {
        Element Alarmas = doc.createElement("Clase_Alarmas");

        Element MantenerAlarma = doc.createElement("MantenerAlarma");
        MantenerAlarma.appendChild(doc.createTextNode("%b".formatted(mantenerAlarmas)));
        Alarmas.appendChild(MantenerAlarma);

        Element SetAlarmas = doc.createElement("Alarmas");
        int i = 0;
        for (LocalDateTime alarm : alarmas){
            i+=1;
            Element alarma = doc.createElement("alarma%d".formatted(i));
            alarma.appendChild(doc.createTextNode(alarm.toString()));
            SetAlarmas.appendChild(alarma);
        }
        Alarmas.appendChild(SetAlarmas);

        if (mantenerAlarmas){
            Element SetAlarmasMantenidas = doc.createElement("AlarmasMantenidas");
            int j = 0;
            for (LocalDateTime alarm : alarmasYaSonadas){
                j+=1;
                Element alarma = doc.createElement("alarmaYaSonada%d".formatted(j));
                alarma.appendChild(doc.createTextNode(alarm.toString()));
                SetAlarmasMantenidas.appendChild(alarma);
            }
            Alarmas.appendChild(SetAlarmasMantenidas);
        }

        estructura.appendChild(Alarmas);
    }

    //--------- Metodos ---------
}
