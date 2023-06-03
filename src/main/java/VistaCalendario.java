import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public abstract class VistaCalendario {
    protected final FlowPane vista;
    protected final FlowPane escenaActual;
    public VistaCalendario(FlowPane visualizacion, FlowPane pantalla, FlowPane eliminar){
        vista = visualizacion;
        escenaActual = pantalla;
        pantalla.getChildren().remove(eliminar);
        pantalla.getChildren().add(visualizacion);
    }

    public abstract List<Month> actualizarVista(LocalDateTime fecha);
}
