import java.time.LocalDateTime;
import java.util.ArrayList;

public class Tarea extends Activities {
    //--------- Atributos ---------

    private LocalDateTime termina;
    private boolean estaCompletada;

    //--------- Atributos ---------

    //--------- Constructores ---------
    public Tarea(String name, String description, ArrayList<LocalDateTime> alarm, boolean isComplete, LocalDateTime termina) {
        super(name, description, alarm, isComplete);
        this.termina = termina;
    }

    public Tarea(String name, String description, boolean isComplete, LocalDateTime termina) {
        super(name, description, isComplete);
        this.termina = termina;
    }
    //--------- Constructores ---------

    //--------- Metodos ---------
    @Override
    public tipo type() {
        return tipo.TAREA;
    }
    
    public boolean estaCompleta(){
	return this.estaCompletada;
    }


    public void marcarCompleta(){
	this.estaCompletada = !this.estaCompletada; 
    }

    @Override
    public LocalDateTime cuandoTermina() {
        return termina;
    }

    @Override
    public LocalDateTime cuandoEmpieza() {
        return this.cuandoTermina();
    }
}
