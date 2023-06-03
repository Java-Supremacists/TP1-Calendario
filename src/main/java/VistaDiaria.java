import javafx.scene.layout.FlowPane;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public class VistaDiaria extends VistaCalendario {
    public VistaDiaria(FlowPane visualizacion, FlowPane pantalla, FlowPane eliminar) {
        super(visualizacion, pantalla, eliminar);
    }

    @Override
    public List<Month> actualizarVista(LocalDateTime fecha) {
        return null;
    }
}
