import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Activities {
    // enum tipo {TAREA,EVENTO}


    protected final String name;
    protected final String description;
    //private final LocalDateTime localDateTimeFinal;
    protected ArrayList<LocalDateTime> alarm; //Le saque el final porque el array no es constante
    protected final boolean isComplete;
    public Activities(String name, String description, ArrayList<LocalDateTime> alarm, boolean isComplete) {
        this.name = name;
        this.description = description;
        //this.localDateTimeFinal = localDateTimeFinal;
        this.alarm = alarm;
        this.isComplete = isComplete;
    }
    //Esta era tu idea original Facu, lo puse para poder tener un constructor sin alarmas por default. Se puede llegar a sacar
    public Activities(String name, String description, boolean isComplete) {
        this.name = name;
        this.description = description;
        //this.localDateTimeFinal = localDateTimeFinal;
        this.isComplete = isComplete;
    }




    // public abstract LocalDateTime primeraAlarma(); //Los comento para que compile de todas formas
    // public abstract void sonarPrimerAlarma();      //creo que seria igual para los dos, tal vez
    // 						      //no hace falta que sea abstracto
    public abstract tipo type();
    public String getTitulo() {
        return name;
    }
    public String getDescripcion() {
        return description;
    }
    public boolean esDiaEntero() {
        return isComplete;
    }
}
