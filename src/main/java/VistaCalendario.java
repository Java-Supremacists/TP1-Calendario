import javafx.scene.layout.FlowPane;
import java.time.LocalDateTime;
import java.util.List;

public abstract class VistaCalendario {
    protected final FlowPane escenaActual;
    protected Calendario modelo;
    public VistaCalendario(FlowPane pantalla, Calendario modelo) {
        escenaActual = pantalla;
	this.modelo = modelo;
    }
    public abstract void ponermeAMi(FlowPane eliminar);
    public abstract String actualizarVista(LocalDateTime fecha);
    public abstract String getTipo();
    public abstract FlowPane getVista();
    public abstract void visualizarActividades(List<Activities> hacerVisual,LocalDateTime fecha);
}
