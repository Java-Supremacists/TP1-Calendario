import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public abstract class VistaCalendario {
    protected final FlowPane vista;
    protected final FlowPane escenaActual;
    public VistaCalendario(FlowPane visualizacion, FlowPane pantalla, FlowPane eliminar) {
        vista = visualizacion;
        escenaActual = pantalla;
        pantalla.getChildren().remove(eliminar);
        pantalla.getChildren().add(visualizacion);
    }
    public VistaCalendario(FlowPane visualizacion, FlowPane pantalla) {
        vista = visualizacion;
        escenaActual = pantalla;
    }
    public abstract String actualizarVista(LocalDateTime fecha, GridPane grillaSuperior);
    public abstract String getTipo();
    public FlowPane getVista() {
        return vista;
    }
    public abstract void visualizarActividades(List<Activities> hacerVisual,GridPane grilla);
}
