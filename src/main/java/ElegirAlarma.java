import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * ElegirAlarma
 */
public class ElegirAlarma {

    @FXML
    private SplitMenuButton elegirAlarma; 

    @FXML
    private ImageView imagen;

    public ElegirAlarma() {
    }

    public void start() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("elegirAlarma.fxml"));
        loader.setController(this);
        Stage stageCrearEvento = loader.load();
        stageCrearEvento.setTitle("Creando evento");
        stageCrearEvento.show();

	this.imagen.setImage(new Image("alarma.png"));
    }

    public void elegirAlarmaDeTipo(ActionEvent event) {
	System.out.println(event);
	System.out.println(event.getClass());
	System.out.println(event.toString());
	System.out.println(event.getSource());
    }
}

