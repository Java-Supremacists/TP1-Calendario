import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class InterfazGrafica extends Application {
    private Scene escena1;
    private Scene escena2;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("escenario1.fxml"));
        loader1.setController(this);
        FlowPane pantalla1 = loader1.load();
        escena1 = new Scene(pantalla1, 854, 480);

        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("escenario2.fxml"));
        loader2.setController(this);
        FlowPane pantalla2 = loader2.load();
        escena2 = new Scene(pantalla2, 754, 860);

        stage.setScene(escena1);
        stage.show();
    }
}
