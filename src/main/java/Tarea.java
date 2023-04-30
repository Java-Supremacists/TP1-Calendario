import java.time.LocalDateTime;

public class Tarea extends Activities {
    //--------- Atributos ---------

    private LocalDateTime termina;
    private boolean estaCompletada;

    //--------- Atributos ---------

    //--------- Constructores ---------

    public Tarea(LocalDateTime termina) {
        super();
        this.termina = termina;
        estaCompletada = false;
    }
    public Tarea(String nombre, String descripcion, boolean esCompleto,LocalDateTime termina) {
        super(nombre, descripcion, esCompleto);
        this.termina = termina;
        estaCompletada = false;
    }
    //--------- Constructores ---------

    //--------- Metodos ---------

    public boolean estaCompleta() {
        return this.estaCompletada;
    }
    public void marcarCompleta() {
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
