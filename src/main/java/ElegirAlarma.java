import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitMenuButton;
import javafx.stage.Stage;

/**
 * ElegirAlarma
 */
public class ElegirAlarma {

    @FXML
    private SplitMenuButton elegirAlarma; 

    public ElegirAlarma() {

    }

    public void start() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("elegirAlarma.fxml"));
        loader.setController(this);
        Stage stageCrearEvento = loader.load();
        stageCrearEvento.setTitle("Creando evento");
        stageCrearEvento.show();

    }
}

