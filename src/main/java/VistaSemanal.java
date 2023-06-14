import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
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
        for (int i = 1; i<8;i++){
            for (int j = 0; j<24;j++){
                var celda = new VBox();
                grillaDiasxHorarios.add(celda,i,j);
                if (hacerVisual== null){continue;}
                for (Activities act : hacerVisual){
                    var comienza = act.cuandoEmpieza();
                    var termina = act.cuandoTermina();
                    var inicio = comienza.getHour();
                    var finaliza = comienza.getHour();
                    var dia = comienza.getDayOfWeek().getValue() % 7 + 1;
                    if (dia == i && inicio <= j){
                        Paint color = Color.web(VistaMensual.getRandomColor());
                        var rectanguloColorido = new Rectangle(115,40,color);
                        if (comienza.equals(termina) && inicio == j){
                            //tarea
                            celda.getChildren().add(rectanguloColorido);
                        } else if (!comienza.equals(termina)) {
                            //evento
                            celda.getChildren().add(rectanguloColorido);
                        }
                    }

                }
            }
        }
    }
    private void vaciarGrilla() {
        grillaDiasxHorarios.getChildren().removeIf(e -> !e.getClass().equals(Label.class));
    }
}
