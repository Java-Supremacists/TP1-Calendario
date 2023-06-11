import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.SplitMenuButton;
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
    // private InterfazGrafica interfazGrafica;

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

    @FXML
    private TextField espacioHoraFin;
    @FXML
    private TextField espacioMinutoFin;
    @FXML
    private TextField espacioSegundoFin;

    @FXML
    private MenuButton espacioTipoActividad;
    private String tipoActividad = "Evento";

    //Le pongo un valor por defecto. Esto tambien es asi en google calendar
    private LocalTime comienzoEvento = LocalTime.now();
    private LocalTime finEvento = comienzoEvento.plusHours(1);

    public CreadorActividad(Calendario modelo, InterfazGrafica interfazGrafica) {
        this.modelo = modelo;
        // this.interfazGrafica = interfazGrafica;
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

        this.espacioHora.setText(String.valueOf(comienzoEvento.getHour()));
        this.espacioMinuto.setText(String.valueOf(comienzoEvento.getMinute()));
        this.espacioSegundo.setText(String.valueOf(comienzoEvento.getSecond()));

        this.espacioHoraFin.setText(String.valueOf(finEvento.getHour()));
        this.espacioMinutoFin.setText(String.valueOf(finEvento.getMinute()));
        this.espacioSegundoFin.setText(String.valueOf(finEvento.getSecond()));

        this.espacioElegirFecha.setValue(LocalDate.now());
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
        this.espacioFrecuencia.setText(String.valueOf(this.frecuenciaDiariaEvento));
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
    private Integer configurarUnidadDeTiempo(TextField espacioUnidad, Integer horaMaxima, Integer horaMinima) {
        var numeroBoton = new EstadoBoton(espacioUnidad);
        espacioUnidad.setStyle(numeroBoton.getColorBoton());

        Integer horaEstablecida = noTeMePases(numeroBoton.getNumeroBoton(), horaMaxima);
        //No podemos tener eventos que finalicen antes de que empiecen
        if (horaEstablecida < horaMinima) {
            horaEstablecida = horaMinima;
        }
        espacioUnidad.setText(String.valueOf(horaEstablecida));

        return horaEstablecida;
    }

    public void ponerHora(ActionEvent event) {
        var horaFinal = this.configurarUnidadDeTiempo(this.espacioHora, 23, 0);
        this.comienzoEvento = this.comienzoEvento.withHour(horaFinal);

        System.out.println(this.comienzoEvento);
    }

    public void ponerHoraFin(ActionEvent event) {
        var horaFinal = this.configurarUnidadDeTiempo(this.espacioHoraFin, 23, this.comienzoEvento.getHour());
        this.finEvento = this.finEvento.withHour(horaFinal);

        System.out.println(this.comienzoEvento);
    }

    public void ponerMinuto(ActionEvent event) {
        var horaFinal = this.configurarUnidadDeTiempo(this.espacioMinuto, 59, 0);
        this.comienzoEvento = this.comienzoEvento.withMinute(horaFinal);

        System.out.println(this.comienzoEvento);
    }

    public void ponerMinutoFin(ActionEvent event) {
        var horaFinal = this.configurarUnidadDeTiempo(this.espacioMinutoFin, 59, this.comienzoEvento.getMinute());
        this.finEvento = this.finEvento.withMinute(horaFinal);

        System.out.println(this.comienzoEvento);
    }

    public void ponerSegundo(ActionEvent event) {
        var horaFinal = this.configurarUnidadDeTiempo(this.espacioSegundo, 59, 0);
        this.comienzoEvento = this.comienzoEvento.withSecond(horaFinal);

        System.out.println(this.comienzoEvento);
    }

    public void ponerSegundoFin(ActionEvent event) {
        var horaFinal = this.configurarUnidadDeTiempo(this.espacioSegundoFin, 59, this.comienzoEvento.getSecond());
        this.finEvento = this.finEvento.withSecond(horaFinal);

        System.out.println(this.comienzoEvento);
    }

    public void ponerTipoActividadTarea(ActionEvent event) {
        this.tipoActividad = "Tarea";
        this.espacioTipoActividad.setText("Tarea");
        this.espacioHoraFin.setDisable(true);
        this.espacioMinutoFin.setDisable(true);
        this.espacioSegundoFin.setDisable(true);
        this.espacioFrecuencia.setDisable(true);
    }

    public void ponerTipoActividadEvento(ActionEvent event) {
        this.tipoActividad = "Evento";
        this.espacioTipoActividad.setText("Evento");
        this.espacioHoraFin.setDisable(false);
        this.espacioMinutoFin.setDisable(false);
        this.espacioSegundoFin.setDisable(false);
        this.espacioFrecuencia.setDisable(false);
    }

    public void crearEvento(ActionEvent event) {
        LocalDateTime fechaComienzo = this.comienzoEvento.atDate(this.fechaEvento);

        int idEvento;
        if (this.tipoActividad == "Tarea") {
            idEvento = this.modelo.crearTarea(this.nombreEvento, this.descripcionEvento, this.esDiaCompletoEvento, fechaComienzo);
        }
        else {
            LocalDateTime fechaFin = this.finEvento.atDate(this.fechaEvento);
            idEvento = this.modelo.crearEvento(this.nombreEvento, this.descripcionEvento, this.esDiaCompletoEvento, fechaComienzo, fechaFin);
        }
        System.out.println(idEvento);


        // var tareaGrafica = new TareaGui(idEvento);
        // this.interfazGrafica.anadirTarea(idEvento, tareaGrafica);

        this.modelo.longTareasYEventos();
    }

}
