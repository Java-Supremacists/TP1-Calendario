import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

public class InterfazGrafica extends Application {
    private final HashMap<Usuario,Calendario> modelo = new HashMap<>();
    private Scene calendario;
    @Override
    public void start(Stage stage) throws Exception {
        var vista = new Vista(this); //por defecto viene con una vista semanal
        calendario = vista.getScene();
        stage.setScene(calendario);
        stage.setResizable(false);
        stage.show();
    }
}
