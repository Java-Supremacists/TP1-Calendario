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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("escenario1.fxml"));
        FlowPane pantalla1 = loader.load();
        escena1 = new Scene(pantalla1, 640, 480);

        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("escenario2.fxml"));
        FlowPane pantalla2 = loader2.load();
        escena2 = new Scene(pantalla2, 640, 480);

        stage.setScene(escena1);
        stage.show();
    }
}
