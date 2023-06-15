import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public class VistaSemanal extends VistaCalendario {
    @FXML
    private FlowPane escenaPorSemana;
    @FXML
    private GridPane grillaDeDiasFijos;
    @FXML
    private GridPane grillaDiasxHorarios;
    public VistaSemanal(FlowPane pantalla, Calendario modelo) throws IOException {
        super(pantalla, modelo);
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("semana.fxml"));
        loader1.setController(this);
        loader1.load();
    }
    @Override
    public void ponermeAMi(FlowPane eliminar) {
        escenaActual.getChildren().remove(eliminar);
        escenaActual.getChildren().add(escenaPorSemana);
    }
    @Override
    public String actualizarVista(LocalDateTime fecha) {
        ObservableList<Node> hijos = grillaDeDiasFijos.getChildren();
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
    public FlowPane getVista() {
        return escenaPorSemana;
    }
    @Override
    public void visualizarActividades(List<Activities> hacerVisual,LocalDateTime fechaInicioSemana) {
        vaciarGrilla();
        for (int i = 1; i < 8; i++) {
            for (int k = 0; k < 24; k++) {
                var celda = new HBox();
                celda.setAlignment(Pos.CENTER_RIGHT);
                grillaDiasxHorarios.add(celda, i, k);
            }

        }
        if (hacerVisual == null) {
            return;
        }
        for (int j = 1; j < 8; j++) {
            int finalJ = j;
            var listaPorColumna = hacerVisual.stream().filter(c -> c.cuandoEmpieza().getDayOfWeek().getValue() % 7 + 1 == finalJ).toList();
            for (Activities act : listaPorColumna) {
                var inicia = act.cuandoEmpieza().getHour();
                var termina = act.cuandoTermina().getHour();
                Paint color = Color.web(GeneradorDeColores.getColorFromId(act.getID()));
                for (Node e : grillaDiasxHorarios.getChildren()) {
                    if (!e.getClass().equals(HBox.class)) {continue;}
                    Integer row = GridPane.getRowIndex(e);
                    Integer column = GridPane.getColumnIndex(e);
                    if (column != null && column == finalJ){
                        var hijo = (HBox) e;
                        Rectangle rectangulo;
                        if (row != null && inicia <= row && row <= termina) {
                            rectangulo = new Rectangle((double) 115 / listaPorColumna.size(), 40, color);
                            rectangulo.setOnMouseClicked(mouseEvent -> {
                                var visualizador = new VisualizadorActividad(act);
                                visualizador.start();
                            });
                        } else {
                            rectangulo = new Rectangle((double) 115 / listaPorColumna.size(), 40, Color.WHITE);
                        }
                        hijo.getChildren().add(rectangulo);
                    }

                }
            }
        }
    }
    private void vaciarGrilla() {
        grillaDiasxHorarios.getChildren().removeIf(e -> !e.getClass().equals(Label.class));
    }
}
