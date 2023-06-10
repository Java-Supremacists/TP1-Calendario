import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

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

    @FXML
    private CheckBox espacioEsDiaCompleto;
    //Tiene como valor predeterminado falso. Si el usuario no dice nada
    //entonces asumimos que NO es de dia completo
    private Boolean esDiaCompletoEvento = false; 

    @FXML
    private DatePicker espacioElegirFecha;
    private LocalDate fechaEvento;

    @FXML
    private TextField espacioFrecuencia;
    private Integer frecuenciaDiariaEvento;





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

    @FXML
    public void ponerEsDiaCompleto(ActionEvent event) {
	this.esDiaCompletoEvento = espacioEsDiaCompleto.isSelected();
	System.out.println(this.esDiaCompletoEvento);
    }

    @FXML
    public void ponerFecha(ActionEvent event) {
	this.fechaEvento = espacioElegirFecha.getValue();
	System.out.println(this.fechaEvento);
    }

    @FXML
    public void ponerFrecuencia(ActionEvent event) {
	try  {
	    this.frecuenciaDiariaEvento = Integer.parseInt(espacioFrecuencia.getText());
	}
	catch (NumberFormatException e) {
	    System.out.println("POner un numero, no un string");
	}

	System.out.println(this.frecuenciaDiariaEvento);
    }

}
