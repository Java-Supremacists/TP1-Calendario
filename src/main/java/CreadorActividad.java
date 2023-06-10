import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.function.Function;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class CreadorActividad {

    // private Scene escena;

    private Calendario modelo;

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
    private Button botonCrear;

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

    public CreadorActividad(Calendario modelo) {
        this.modelo = modelo;
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

        this.espacioHora.setText(String.valueOf(LocalTime.now().getHour()));
        this.espacioMinuto.setText(String.valueOf(LocalTime.now().getMinute()));
        this.espacioSegundo.setText(String.valueOf(LocalTime.now().getSecond()));
    }

    public void ponerNombre(ActionEvent event) {
        this.nombreEvento = this.espacioNombre.getText();
        System.out.println(this.nombreEvento);
    }

    public void ponerDescripcion(ActionEvent event) {
        this.descripcionEvento = this.espacioDescripcion.getText();
        System.out.println(this.descripcionEvento);
    }

    public void ponerEsDiaCompleto(ActionEvent event) {
        this.esDiaCompletoEvento = this.espacioEsDiaCompleto.isSelected();
        System.out.println(this.esDiaCompletoEvento);
    }

    public void ponerFecha(ActionEvent event) {
        this.fechaEvento = this.espacioElegirFecha.getValue();
        System.out.println(this.fechaEvento);
    }

    public void ponerFrecuencia(ActionEvent event) {
        var numeroBoton = new EstadoBoton(this.espacioFrecuencia);
        this.espacioFrecuencia.setStyle(numeroBoton.getColorBoton());

        this.frecuenciaDiariaEvento = numeroBoton.getNumeroBoton();
        System.out.println(this.frecuenciaDiariaEvento);
    }

    private Integer noTeMePases(Integer horaAChequear, Integer horaMaxima) {
        if (horaAChequear > horaMaxima) {
            return horaMaxima;
        }
        else if (horaAChequear < 0) {
            return 0;
        }
        else {
            return horaAChequear;
        }
    }

    // No es ideal que esta funcion tenga TANTOS efectos secundarios, sin embargo
    // java no nos permite hacerlo de una forma mas elegante/funcional
    // a menos que creemos clases animica
    private Integer configurarUnidadDeTiempo(TextField espacioUnidad, Integer horaMaxima) {
        var numeroBoton = new EstadoBoton(espacioUnidad);
        espacioUnidad.setStyle(numeroBoton.getColorBoton());

        Integer horaEstablecida = noTeMePases(numeroBoton.getNumeroBoton(), horaMaxima);
        espacioUnidad.setText(String.valueOf(horaEstablecida));

        return horaEstablecida;
    }

    public void ponerHora(ActionEvent event) {
        var horaFinal = this.configurarUnidadDeTiempo(this.espacioHora, 23);
        this.horaEvento = this.horaEvento.withHour(horaFinal);

        System.out.println(this.horaEvento);
    }


    public void ponerMinuto(ActionEvent event) {
        var horaFinal = this.configurarUnidadDeTiempo(this.espacioMinuto, 59);
        this.horaEvento = this.horaEvento.withMinute(horaFinal);

        System.out.println(this.horaEvento);
    }

    public void ponerSegundo(ActionEvent event) {
        var horaFinal = this.configurarUnidadDeTiempo(this.espacioSegundo, 59);
        this.horaEvento = this.horaEvento.withSecond(horaFinal);

        System.out.println(this.horaEvento);
    }

    public void crearEvento(ActionEvent event) {
	LocalDateTime fechaFinal;
	fechaFinal = this.horaEvento.atDate(this.fechaEvento);
	this.modelo.crearTarea(this.nombreEvento, this.descripcionEvento, this.esDiaCompletoEvento, fechaFinal);
	this.modelo.longTareasYEventos();
    }

}
