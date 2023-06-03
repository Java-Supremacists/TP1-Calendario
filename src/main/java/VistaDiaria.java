import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class VistaDiaria extends VistaCalendario {
    public VistaDiaria(FlowPane visualizacion, FlowPane pantalla, FlowPane eliminar) {
        super(visualizacion, pantalla, eliminar);
    }
    /*public VistaDiaria(FlowPane visualizacion, FlowPane pantalla) {
        super(visualizacion, pantalla);
    }*/
    @Override
    public List<Month> actualizarVista(LocalDateTime fecha, GridPane grillaSuperior) {
        ObservableList<Node> hijos = grillaSuperior.getChildren();
        for (Node e : hijos) {
            if (e.getClass().equals(VBox.class)) {
                VBox panelDeDia = (VBox) e;
                hijos = panelDeDia.getChildren();
                var Dia = (Label) hijos.get(0);
                var Numero = (Label) hijos.get(1);
                Dia.setText(fecha.getDayOfWeek().toString());
                Numero.setText("%d".formatted(fecha.getDayOfMonth()));
            }
        }

        ArrayList<Month> months = new ArrayList<>(1);
        months.add(0,fecha.getMonth());
        return months;
    }
    @Override
    public String getTipo() {
        return "Dia";
    }
}
