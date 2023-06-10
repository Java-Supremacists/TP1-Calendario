import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.LocalTime;

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

    @FXML
    private TextField espacioHora;
    @FXML
    private TextField espacioMinuto;
    @FXML
    private TextField espacioSegundo;

    //Le pongo un valor por defecto. Esto tambien es asi en google calendar
    private LocalTime horaEvento = LocalTime.now(); 





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
	this.nombreEvento = this.espacioNombre.getText();
	System.out.println(this.nombreEvento);
    }

    @FXML
    public void ponerDescripcion(ActionEvent event) {
	this.descripcionEvento = this.espacioDescripcion.getText();
	System.out.println(this.descripcionEvento);
    }

    @FXML
    public void ponerEsDiaCompleto(ActionEvent event) {
	this.esDiaCompletoEvento = this.espacioEsDiaCompleto.isSelected();
	System.out.println(this.esDiaCompletoEvento);
    }

    @FXML
    public void ponerFecha(ActionEvent event) {
	this.fechaEvento = this.espacioElegirFecha.getValue();
	System.out.println(this.fechaEvento);
    }

    @FXML
    public void ponerFrecuencia(ActionEvent event) {
	var numeroBoton = new EstadoBoton(this.espacioFrecuencia);
	this.espacioFrecuencia.setStyle(numeroBoton.getColorBoton());

	this.frecuenciaDiariaEvento = numeroBoton.getNumeroBoton();
	System.out.println(this.frecuenciaDiariaEvento);
	// System.out.println(numeroBoton.getNumeroBoton());
    }

    @FXML
    public void ponerHora(ActionEvent event) {
	var numeroBoton = new EstadoBoton(this.espacioHora);
	this.espacioHora.setStyle(numeroBoton.getColorBoton());

	this.horaEvento = this.horaEvento.withHour(numeroBoton.getNumeroBoton());
	System.out.println(this.horaEvento);
    }

    @FXML
    public void ponerMinuto(ActionEvent event) {
	var numeroBoton = new EstadoBoton(this.espacioMinuto);
	this.espacioMinuto.setStyle(numeroBoton.getColorBoton());

	this.horaEvento = this.horaEvento.withMinute(numeroBoton.getNumeroBoton());
	System.out.println(this.horaEvento);
    }

    @FXML
    public void ponerSegundo(ActionEvent event) {
	var numeroBoton = new EstadoBoton(this.espacioSegundo);
	this.espacioSegundo.setStyle(numeroBoton.getColorBoton());

	this.horaEvento = this.horaEvento.withSecond(numeroBoton.getNumeroBoton());
	System.out.println(this.horaEvento);
    }

}
