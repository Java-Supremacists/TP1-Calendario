import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class VisualizadorActividad {

    // private Scene escena;

    private Calendario modelo;
    // private InterfazGrafica interfazGrafica;

    @FXML
    private TextField espacioNombre;
    private String nombreEvento = "Nombre default";

    @FXML
    private TextField espacioDescripcion;
    private String descripcionEvento = "Descripcion Default";

    @FXML
    private CheckBox espacioEsDiaCompleto;
    //Tiene como valor predeterminado falso. Si el usuario no dice nada
    //entonces asumimos que NO es de dia completo
    private Boolean esDiaCompletoEvento = false;

    @FXML
    private DatePicker espacioElegirFecha;
    private LocalDate fechaEvento = LocalDate.now();

    @FXML
    private Button botonCrear;

    @FXML
    private TextField espacioFrecuencia;
    private Integer frecuenciaDiariaEvento = 0;

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

    private Activities act;

    @FXML
    private Button relojImagen;

    //Le pongo un valor por defecto. Esto tambien es asi en google calendar
    private LocalTime comienzoEvento = LocalTime.now();
    private LocalTime finEvento = comienzoEvento.plusHours(1);

    private ArrayList<Plazo> listaPlazos;

    public VisualizadorActividad(Activities act) {
	this.act = act;
    }

    // public Scene getScene() {
    //     return this.escena;
    // }

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
        this.espacioSegundo.setText(String.valueOf(this.act.cuandoEmpieza().getSecond()));

        this.espacioHoraFin.setText(String.valueOf(this.act.cuandoTermina().getHour()));
        this.espacioMinutoFin.setText(String.valueOf(this.act.cuandoTermina().getMinute()));
        this.espacioSegundoFin.setText(String.valueOf(this.act.cuandoTermina().getSecond()));
	this.espacioSegundoFin.setDisable(true);
	// this.espacioSegundoFin.setStyle("-fx-text-fill: white; -fx-background-color: white");

        this.espacioElegirFecha.setValue(this.act.cuandoTermina().toLocalDate());
	}
	catch (Exception e) {
	    System.out.println(e);
	    e.printStackTrace(System.out);
	}
        // this.relojImagen.setGraphic(new ImageView(new Image("alarma.png")));
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

        System.out.println(this.finEvento);
    }

    public void ponerMinuto(ActionEvent event) {
        var horaFinal = this.configurarUnidadDeTiempo(this.espacioMinuto, 59, 0);
        this.comienzoEvento = this.comienzoEvento.withMinute(horaFinal);

        System.out.println(this.comienzoEvento);
    }

    public void ponerMinutoFin(ActionEvent event) {
        var horaFinal = this.configurarUnidadDeTiempo(this.espacioMinutoFin, 59, this.comienzoEvento.getMinute());
        this.finEvento = this.finEvento.withMinute(horaFinal);

        System.out.println(this.finEvento);
    }

    public void ponerSegundo(ActionEvent event) {
        var horaFinal = this.configurarUnidadDeTiempo(this.espacioSegundo, 59, 0);
        this.comienzoEvento = this.comienzoEvento.withSecond(horaFinal);

        System.out.println(this.comienzoEvento);
    }

    public void ponerSegundoFin(ActionEvent event) {
        var horaFinal = this.configurarUnidadDeTiempo(this.espacioSegundoFin, 59, this.comienzoEvento.getSecond());
        this.finEvento = this.finEvento.withSecond(horaFinal);

        System.out.println(this.finEvento);
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
            for (Plazo plazo : this.listaPlazos) {
                this.modelo.modificarActividadAgregarAlarma(idEvento, plazo);
            }
        }
        else {
            LocalDateTime fechaFin = this.finEvento.atDate(this.fechaEvento);
            idEvento = this.modelo.crearEvento(this.nombreEvento, this.descripcionEvento, this.esDiaCompletoEvento, fechaComienzo, fechaFin);
            this.modelo.modificarEventoFrecuencia(idEvento, new FrecuenciaDiaria(this.frecuenciaDiariaEvento, new RepeticionInfinita()));
            for (Plazo plazo : this.listaPlazos) {
                this.modelo.modificarActividadAgregarAlarma(idEvento, plazo);
            }
        }
        System.out.println(idEvento);


        // var tareaGrafica = new TareaGui(idEvento);
        // this.interfazGrafica.anadirTarea(idEvento, tareaGrafica);

        this.modelo.longTareasYEventos();
    }

    // public void elegirAlarma() {
    //     System.out.println("clicl");
    //     var elegirAlarma = new ElegirAlarma(this);

    //     try {
    //         elegirAlarma.start();
    //     }
    //     catch (Exception e) {
    //         e.printStackTrace(System.out);
    //     }

    // }

    public void anadirAlarma(String plazoElegidoPorUsuario) {
        Plazo plazoParaAnadir;

        //Pongo un plazo por defecto porque sino java se pone triste de que
        //puede ser que haya una chance de que tal vez no sea el caso de la
        //posible posibilidad de que dado el caso mi variable no sea
        //inicializada
        plazoParaAnadir = Plazo.CINCOMINUTOSANTES;

        switch (plazoElegidoPorUsuario) {
        case "1 dia antes":
            System.out.println("1 dia antes");
            plazoParaAnadir = Plazo.DIAANTES;
            break;
        case "1 hora antes":
            System.out.println("1 hora antes");
            plazoParaAnadir = Plazo.HORAANTES;
            break;

        case "30 mins antes":
            System.out.println("30 mins antes");
            plazoParaAnadir = Plazo.MEDIAHORAANTES;
            break;

        case "15 mins antes":
            System.out.println("15 mins antes");
            plazoParaAnadir = Plazo.QUINCEMINUTOSANTES;
            break;

        case "10 mins antes":
            System.out.println("10 mins antes");
            plazoParaAnadir = Plazo.DIEZMINUTOSANTES;
            break;

        case "5 mins antes":
            System.out.println("5 mins antes");
            plazoParaAnadir = Plazo.CINCOMINUTOSANTES;
            break;
        }
        if (this.listaPlazos.contains(plazoParaAnadir) == true) {
            //Si ya lo tiene, no lo vuelvo a guardar
            return;
        }
        this.listaPlazos.add(plazoParaAnadir);
        System.out.println(this.listaPlazos.size());
    }
}
