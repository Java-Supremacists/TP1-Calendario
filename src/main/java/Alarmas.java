import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Alarmas {
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
    }
    public Alarmas(boolean mantener) {
        alarmaMasTemprana = null;
        alarmas = new HashSet<>();
        mantenerAlarmas = mantener;
        alarmasYaSonadas = new HashSet<>();
    }

    //--------- Constructores ---------

    //--------- Metodos ---------
    public void agregarAlarma(LocalDateTime alarmaParaAgregar){
        alarmas.add(alarmaParaAgregar);
        if (mantenerAlarmas){
            alarmasYaSonadas.add(alarmaParaAgregar);
        }
        if ((alarmaMasTemprana != null) && alarmaMasTemprana.isAfter(alarmaParaAgregar)) {
            alarmaMasTemprana = alarmaParaAgregar;
        }
    }
    public void agregarAlarma(List<LocalDateTime> alarmasParaAgregar){
        if (alarmasParaAgregar!=null){
            for (LocalDateTime alarm : alarmasParaAgregar){
                alarmas.add(alarm);
                if (mantenerAlarmas){
                    alarmasYaSonadas.add(alarm);
                }
                if ((alarmaMasTemprana != null) && alarmaMasTemprana.isAfter(alarm)) {
                    alarmaMasTemprana = alarm;
                }
            }
        }


    }
    public boolean quedanAlarmas(){
        //devuelve true si todavia quedan
        return alarmas.size()!= 0;
    }
    public boolean existeAlarma(LocalDateTime a1){
        return alarmas.contains(a1) || alarmasYaSonadas.contains(a1);
    }
    public void eliminarAlarma(LocalDateTime paraEliminar){
        alarmas.remove(paraEliminar);
        alarmasYaSonadas.remove(paraEliminar);
    }
    public LocalDateTime primerAlarmaASonar(){
        if (alarmaMasTemprana == null){
            for (LocalDateTime alarm : alarmas){
                if (alarmaMasTemprana == null){
                    alarmaMasTemprana = alarm;
                } else if (alarmaMasTemprana.isAfter(alarm)) {
                    alarmaMasTemprana = alarm;
                }
            }
        }
        return alarmaMasTemprana;
    }
    public void sonarAlarma(){
        if (alarmaMasTemprana != null){
            alarmas.remove(alarmaMasTemprana);
            alarmaMasTemprana = null;
            if (alarmas.size()==0 && mantenerAlarmas){
                alarmas.addAll(alarmasYaSonadas);
            }
        }//else {
            //error
        //}
    }

    //--------- Metodos ---------
}
