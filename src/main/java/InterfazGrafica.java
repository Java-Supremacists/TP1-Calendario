import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashMap;

public class InterfazGrafica extends Application {
    @FXML
    private FlowPane pantalla;
/*
*   @FXML
    private HBox barraDeHerramientas;

    @FXML
    private VBox barraLateralIzquierda;

    @FXML
    private ListView listaPosibleParaUsar;

    @FXML
    private ScrollPane escroleoDeGrilla;

    @FXML
    private Pane barraSuperiorDiaria;

    @FXML
    private ScrollPane ScrollGrillaDiaxHora;
 */
    @FXML
    private Button botonDeHoy;
    @FXML
    private Button visualizacionAnterior;
    @FXML
    private Button visualizacionPosterior;
    @FXML
    private ChoiceBox tipoDeVisualizacion;
    @FXML
    private ChoiceBox usuario;
    @FXML
    private ChoiceBox botonCrearActividad;
    @FXML
    private FlowPane escenaPorSemana;
    @FXML
    private GridPane grillaDeDiasFijos;
    @FXML
    private GridPane grillaDiasxHorarios;
    @FXML
    private FlowPane escenaPorDia;
    @FXML
    private GridPane grillaDiaxHora;
    @FXML
    private FlowPane escenaPorMes;
    @FXML
    private GridPane grillaDelMes;
    private final HashMap<Usuario,Calendario> listadoUsuarios = new HashMap<>();
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("escenario1.fxml"));
        loader1.setController(this);
        FlowPane pantalla1 = loader1.load();
        Scene escena = new Scene(pantalla1,854,480);

        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("escenario2.fxml"));
        loader2.setController(this);

        stage.setScene(escena);
        stage.setResizable(false);
        stage.show();
    }
}
