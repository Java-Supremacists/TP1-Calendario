import java.time.LocalDateTime;
import java.util.ArrayList;

public class Tarea extends Activities {
    //--------- Atributos ---------

    private LocalDateTime termina;
    private boolean estaCompletada;

    //--------- Atributos ---------

    //--------- Constructores ---------
    /*
    * public Tarea(LocalDateTime termina) {
        this.termina = termina;
    }
     */
    public Tarea(LocalDateTime termina) {
        super();
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
    public void setTermina(LocalDateTime termina) {
        this.termina = termina;
    }

    //--------- Metodos ---------
}
