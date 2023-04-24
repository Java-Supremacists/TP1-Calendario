import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Activities {
    //--------- Atributos ---------

    enum tipo {TAREA,EVENTO} //nisiquiera usariamos Type
    protected String name;
    protected String description;
    protected final Alarmas alarm = new Alarmas();
    protected boolean isComplete;

    //--------- Atributos ---------

    //--------- Constructores ---------

    public Activities() {
        this.name = "";
        this.description = "";
        this.isComplete = false;
    }

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
    public void agregarAlarma(LocalDateTime alarmaNueva) {
	this.alarm.agregarAlarma(alarmaNueva);
    }
    public void agregarAlarmas(List<LocalDateTime> alarmasNuevas) {
	this.alarm.agregarAlarma(alarmasNuevas);
    }

    //--------- Metodos ---------
}
