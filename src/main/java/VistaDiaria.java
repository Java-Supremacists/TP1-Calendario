import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class VistaDiaria extends VistaCalendario {
    @FXML
    private FlowPane escenaPorDia;
    @FXML
    private GridPane grillaDiaxHora;
    @FXML
    private GridPane grillaConElDia;
    public VistaDiaria(FlowPane pantalla, Calendario modelo) throws IOException {
        super(pantalla, modelo);
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("diaria.fxml"));
        loader1.setController(this);
        loader1.load();
    }
    @Override
    public void ponermeAMi(FlowPane eliminar) {
        escenaActual.getChildren().remove(eliminar);
        escenaActual.getChildren().add(escenaPorDia);
    }
    @Override
    public String actualizarVista(LocalDateTime fecha) {
        ObservableList<Node> hijos = grillaConElDia.getChildren();
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
        return fecha.getMonth().toString();
    }
    @Override
    public String getTipo() {
        return "Dia";
    }
    @Override
    public FlowPane getVista() {
        return escenaPorDia;
    }
    @Override
    public void visualizarActividades(List<Activities> hacerVisual,LocalDateTime fechaHoy) {
        vaciarGrilla();
    }
    private void vaciarGrilla() {
        grillaDiaxHora.getChildren().removeIf(e -> !e.getClass().equals(Label.class)); //significa que e es un elemento de visualizacion del evento o tarea
    }

}
