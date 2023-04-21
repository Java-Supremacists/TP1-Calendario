import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Activities {
    //--------- Atributos ---------

    enum tipo {TAREA,EVENTO} //nisiquiera usariamos Type
    protected String name;
    protected String description;
    protected final Alarmas alarm = new Alarmas();
    protected boolean isComplete;

    //--------- Atributos ---------

    //--------- Constructores ---------
    /*
    * public Activities() {
        this.name = "";
        this.description = "";
        this.isComplete = false;
    }*/

    public Activities(String name, String description, ArrayList<LocalDateTime> alarm, boolean isComplete) {
        this.name = name;
        this.description = description;
        this.alarm.agregarAlarma(alarm);
        this.isComplete = isComplete;
    }
    /*
    * public Activities(String name, String description, boolean isComplete) {
        this.name = name;
        this.description = description;
        this.isComplete = isComplete;
    }
     */

    //--------- Constructores ---------

    //--------- Metodos ---------

    public abstract tipo type(); //ni tampoco esta funcion
    public abstract LocalDateTime cuandoTermina();
    public abstract LocalDateTime cuandoEmpieza();
    public LocalDateTime ultimaAlarma(){
        if (alarm.quedanAlarmas()){
            return alarm.primerAlarmaASonar();
        }
        return null;
    }
    public void sonarUltimaAlarma(){
        if (alarm.quedanAlarmas()){
            alarm.sonarAlarma();
        }//else
            //error
        //}
    }
    public Alarmas getAlarm() {
        return alarm;
    }
    public String getTitulo() {
        return name;
    }
    public String getDescripcion() {
        return description;
    }
    public boolean esDiaEntero() {
        return isComplete;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    //--------- Metodos ---------
}
