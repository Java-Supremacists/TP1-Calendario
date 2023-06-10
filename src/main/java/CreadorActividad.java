import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class CreadorActividad {

    // private Scene escena;

    // private InterfazGrafica controlador;

    @FXML
    private TextField espacioNombre;
    private String nombreEvento;

    @FXML
    private TextField espacioDescripcion;
    private String descripcionEvento;

    public CreadorActividad(InterfazGrafica controller) {
    }

    // public Scene getScene() {
    //     return this.escena;
    // }

    public void start() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("crearActividad.fxml"));
        loader.setController(this);
        Stage stageCrearEvento = loader.load();
        stageCrearEvento.setTitle("Creando evento");
        stageCrearEvento.show();
    }

    @FXML
    public void ponerNombre(ActionEvent event) {
	this.nombreEvento = espacioNombre.getText();
	System.out.println(this.nombreEvento);

    }

    @FXML
    public void ponerDescripcion(ActionEvent event) {
	this.descripcionEvento = espacioDescripcion.getText();
	System.out.println(this.descripcionEvento);

    }

}
