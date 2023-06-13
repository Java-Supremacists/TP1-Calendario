import javafx.scene.layout.FlowPane;
import java.time.LocalDateTime;
import java.util.List;

public abstract class VistaCalendario {
    protected final FlowPane escenaActual;
    public VistaCalendario(FlowPane pantalla) {
        escenaActual = pantalla;
    }
    public abstract void ponermeAMi(FlowPane eliminar);
    public abstract String actualizarVista(LocalDateTime fecha);
    public abstract String getTipo();
    public abstract FlowPane getVista();
    public abstract void visualizarActividades(List<Activities> hacerVisual,LocalDateTime fecha);
}
