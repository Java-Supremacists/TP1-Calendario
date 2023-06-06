import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;


public class CreadorActividad {

    private Scene escena;

    private InterfazGrafica controlador;
    
    public CreadorActividad(InterfazGrafica controller) {
    }
    
    public Scene getScene(){
        return this.escena;
    }

    public void start() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("crearActividad.fxml"));
	loader.setController(this);
	Stage stageCrearEvento = loader.load();
	stageCrearEvento.setTitle("Creando evento");
	stageCrearEvento.show();
	}
	
}
