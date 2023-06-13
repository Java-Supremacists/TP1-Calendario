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
import java.time.Month;
import java.util.List;

public class VistaMensual extends VistaCalendario {
    @FXML
    private FlowPane escenaPorMes;
    @FXML
    private GridPane grillaDelMes;
    public VistaMensual(FlowPane pantalla) throws IOException {
        super(pantalla);
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("mensual.fxml"));
        loader1.setController(this);
        loader1.load();
    }
    @Override
    public void ponermeAMi(FlowPane eliminar) {
        escenaActual.getChildren().remove(eliminar);
        escenaActual.getChildren().add(escenaPorMes);
    }
    @Override
    public String actualizarVista(LocalDateTime fecha) {
        ObservableList<Node> hijos = grillaDelMes.getChildren();
        Month mes = fecha.getMonth();
        long i = 0;
        Label Dia;
        Label Numero;
        for (Node e : hijos) {
            if (e.getClass().equals(VBox.class)) {
                LocalDateTime dia = fecha.plusDays(i);
                VBox diaDelMes = (VBox) e;
                hijos = diaDelMes.getChildren();
                if (dia.getDayOfMonth()==1 && i<14) {
                    mes = dia.getMonth();
                }
                if (i < 7) {
                    Dia = (Label) hijos.get(0);
                    Numero = (Label) hijos.get(1);
                    Dia.setText(dia.getDayOfWeek().toString());
                    Numero.setText("%d".formatted(dia.getDayOfMonth()));
                } else {
                    Numero = (Label) hijos.get(0);
                    Numero.setText("%d".formatted(dia.getDayOfMonth()));
                }
                i++;
            }
        }
        return mes.toString();
    }
    @Override
    public String getTipo() {
        return "Mes";
    }
    @Override
    public FlowPane getVista() {
        return escenaPorMes;
    }
    @Override
    public void visualizarActividades(List<Activities> hacerVisual) {
        vaciarGrilla();
    }
    private void vaciarGrilla() {
        for (Node e : grillaDelMes.getChildren()) {
            if (e.getClass().equals(VBox.class)){
                //e siempre va a ser una VBox
                var hijo = (VBox) e;
                hijo.getChildren().removeIf(i -> !i.getClass().equals(Label.class)); // remuevo lo de adentro del Vbox excepto las fechas
            }

        }
    }
}
