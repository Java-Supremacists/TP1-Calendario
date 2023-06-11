import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public class VistaSemanal extends VistaCalendario {
    public VistaSemanal(FlowPane visualizacion, FlowPane pantalla, FlowPane eliminar) {
        super(visualizacion, pantalla, eliminar);
    }
    public VistaSemanal(FlowPane visualizacion, FlowPane pantalla) {
        super(visualizacion, pantalla);
    }
    @Override
    public String actualizarVista(LocalDateTime fecha, GridPane grillaSuperior) {
        ObservableList<Node> hijos = grillaSuperior.getChildren();
        Month mesDesde = fecha.getMonth();
        Month mesHasta = null;
        long i = 0;
        for (Node e : hijos) {
            if (e.getClass().equals(VBox.class)) {
                LocalDateTime dia = fecha.plusDays(i);
                VBox panelDeDias = (VBox) e;
                hijos = panelDeDias.getChildren();
                var Dia = (Label) hijos.get(0);
                var Numero = (Label) hijos.get(1);
                Dia.setText(dia.getDayOfWeek().toString());
                Numero.setText("%d".formatted(dia.getDayOfMonth()));
                mesHasta = dia.getMonth();
                i++;
            }
        }
        if (mesDesde != mesHasta && mesHasta!= null) {
            return mesDesde.toString() + "-" + mesHasta;
        }
        return mesDesde.toString();
    }
    @Override
    public String getTipo() {
        return "Semana";
    }
    @Override
    public void visualizarActividades(List<Activities> hacerVisual, GridPane grilla) {
        vaciarGrilla(grilla);
    }
    private void vaciarGrilla(GridPane grillaDiasxHorarios){
        grillaDiasxHorarios.getChildren().removeIf(e -> !e.getClass().equals(Label.class));
    }
}
