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
        for (int i = 0; i <24; i++){
            var celda = new HBox();
            celda.setAlignment(Pos.CENTER_LEFT);
            grillaDiaxHora.add(celda,1, i);
        }
        if (hacerVisual== null){return;}
        for (Activities act : hacerVisual){
            Paint color = Color.web(GeneradorDeColores.getColorFromId(act.getID()));
            var inicia = act.cuandoEmpieza().getHour();
            var termina = act.cuandoTermina().getHour();
            for (Node e : grillaDiaxHora.getChildren()) {
                if (!e.getClass().equals(HBox.class)){continue;}
                Integer row = GridPane.getRowIndex(e);
                var hijo = (HBox) e;
                Rectangle rectangulo;
                if (row != null &&  inicia <= row && row <= termina) {
                    rectangulo = new Rectangle((double) 800 / hacerVisual.size(), 40, color);
                    rectangulo.setOnMouseClicked(mouseEvent -> {
                        var visualizador = new VisualizadorActividad(act);
                        visualizador.start();
                    });
                }else {
                    rectangulo = new Rectangle((double) 800 / hacerVisual.size(), 40, Color.WHITE);
                }
                hijo.getChildren().add(rectangulo);
            }
        }


    }
    private void vaciarGrilla() {
        grillaDiaxHora.getChildren().removeIf(e -> !e.getClass().equals(Label.class)); //significa que e es un elemento de visualizacion del evento o tarea
    }

}
