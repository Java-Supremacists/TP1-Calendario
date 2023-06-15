import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VisualizadorActividad {

    private Calendario modelo;

    @FXML
    private TextField espacioNombre;

    @FXML
    private TextField espacioDescripcion;

    @FXML
    private CheckBox espacioEsDiaCompleto;

    @FXML
    private DatePicker espacioElegirFecha;

    @FXML
    private Button botonCrear;

    @FXML
    private Text texto;
    @FXML
    private Text textoFrecuenciaDiaria;
    @FXML
    private Text separadorDos;
    @FXML
    private Text separadorUno;

    @FXML
    private TextField espacioFrecuencia;

    @FXML
    private TextField espacioHora;
    @FXML
    private TextField espacioMinuto;

    @FXML
    private TextField espacioHoraFin;
    @FXML
    private TextField espacioMinutoFin;

    @FXML
    private MenuButton espacioTipoActividad;

    private Activities act;

    @FXML
    private Button relojImagen;

    public VisualizadorActividad(Activities act) {
        this.act = act;
    }

    public void start() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("visualizarActividad.fxml"));
            loader.setController(this);
            Stage stageCrearEvento = loader.load();
            stageCrearEvento.setTitle("Mostrando evento");
            stageCrearEvento.show();

            this.espacioNombre.setText(this.act.getTitulo());
            this.espacioDescripcion.setText(this.act.getDescripcion());

            this.espacioHora.setText(String.valueOf(this.act.cuandoEmpieza().getHour()));
            this.espacioMinuto.setText(String.valueOf(this.act.cuandoEmpieza().getMinute()));
            this.espacioElegirFecha.setValue(this.act.cuandoTermina().toLocalDate());

            this.espacioHoraFin.setText(String.valueOf(this.act.cuandoTermina().getHour()));
            this.espacioMinutoFin.setText(String.valueOf(this.act.cuandoTermina().getMinute()));

            this.texto.setText("Evento");

            if (this.act.esDiaEntero()) {
                this.espacioEsDiaCompleto.setSelected(true);
            }

            if (this.act.cuandoEmpieza().equals(this.act.cuandoTermina())) {
                this.espacioHoraFin.setStyle("-fx-text-fill: white; -fx-background-color: white");
                this.espacioMinutoFin.setStyle("-fx-text-fill: white; -fx-background-color: white");
                // this.textoFrecuenciaDiaria.setStyle("-fx-text-fill: white; -fx-background-color: white");
                this.textoFrecuenciaDiaria.setFill(Color.WHITE);
                this.separadorUno.setFill(Color.WHITE);
                this.separadorDos.setFill(Color.WHITE);
                this.espacioFrecuencia.setStyle("-fx-text-fill: white; -fx-background-color: white");
                this.texto.setText("Tarea");
            }

        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace(System.out);
        }
        // this.relojImagen.setGraphic(new ImageView(new Image("alarma.png")));
    }


}
