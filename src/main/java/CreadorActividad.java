import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class CreadorActividad {

    private Calendario modelo;

    @FXML
    private Stage stage;

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
    private Boolean esDiaCompletoEvento;

    @FXML
    private DatePicker espacioElegirFecha;
    private LocalDate fechaEvento;

    @FXML
    private Button botonCrear;

    @FXML
    private Button borrador;

    @FXML
    private TextField espacioFrecuencia;
    private Integer frecuenciaDiariaEvento;

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
    private String tipoActividad;

    @FXML
    private Button relojImagen;

    @FXML
    private CheckBox tareaTerminado;

    //Le pongo un valor por defecto. Esto tambien es asi en google calendar
    private LocalTime comienzoEvento;
    private LocalTime finEvento;

    private ArrayList<Plazo> listaPlazos;

    private Activities act;

    private Vista vista;
    private LocalDateTime fechaActual;

    private VistaCalendario vistaCalendario;

    public CreadorActividad(Calendario modelo, Vista vista, LocalDateTime fechaActual) {
        this.nombreEvento = "Nombre default";
        this.descripcionEvento = "Descripcion Default";
        this.esDiaCompletoEvento = false;
        this.fechaEvento = LocalDate.now();
        this.frecuenciaDiariaEvento = 0;
        this.tipoActividad = "Evento";
        this.comienzoEvento = LocalTime.now();
        this.finEvento = comienzoEvento.plusHours(1);


	this.vista = vista;
	this.fechaActual = fechaActual;

        this.modelo = modelo;

        this.listaPlazos = new ArrayList<>();
    }

    public CreadorActividad(Activities act, Calendario modelo) {
        this.nombreEvento = act.getTitulo();
        this.descripcionEvento = act.getDescripcion();
        this.esDiaCompletoEvento = act.esDiaEntero();
        this.fechaEvento = act.cuandoEmpieza().toLocalDate();
        this.frecuenciaDiariaEvento = 0;
        if (act.cuandoEmpieza().equals(act.cuandoTermina())) {
            this.tipoActividad = "Tarea";
        }
        else {
            this.tipoActividad = "Evento";
        }
        this.comienzoEvento = act.cuandoEmpieza().toLocalTime();
        this.finEvento = act.cuandoTermina().toLocalTime();

        this.modelo = modelo;
        this.act = act;

        this.listaPlazos = new ArrayList<>();
    }

    public void start() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("crearActividad.fxml"));
            loader.setController(this);
            Stage stageCrearEvento = loader.load();
            this.stage = stageCrearEvento;
            stageCrearEvento.setTitle("Creando evento");
            stageCrearEvento.show();

            this.espacioHora.setText(String.valueOf(comienzoEvento.getHour()));
            this.espacioMinuto.setText(String.valueOf(comienzoEvento.getMinute()));

            this.espacioHoraFin.setText(String.valueOf(finEvento.getHour()));
            this.espacioMinutoFin.setText(String.valueOf(finEvento.getMinute()));

            this.espacioElegirFecha.setValue(LocalDate.now());

            if (this.act == null) {
                this.tareaTerminado.setStyle("-fx-text-fill: white; -fx-background-color: white");
                this.tareaTerminado.setDisable(true);
                this.tareaTerminado.setOpacity(0);
                this.borrador.setStyle("-fx-text-fill: white; -fx-background-color: white");
                this.borrador.setDisable(true);
                this.borrador.setOpacity(0);
            }

            //Estamos modificando una actividad ya existente
            else {

                //Queremos sacar la ventanita de visualizacion en ambas
                this.espacioTipoActividad.setStyle("-fx-text-fill: white; -fx-background-color: white");
                this.espacioTipoActividad.setDisable(true);
                this.espacioTipoActividad.setOpacity(0);

                //Ponemos la infomacion que ya sabemos de la actividad
                this.espacioNombre.setText(this.act.getTitulo());
                this.espacioDescripcion.setText(this.act.getDescripcion());
                this.espacioEsDiaCompleto.setSelected(this.esDiaCompletoEvento);
                this.espacioElegirFecha.setValue(this.act.cuandoEmpieza().toLocalDate());

                this.espacioHora.setText(String.valueOf(this.act.cuandoEmpieza().getHour()));
                this.espacioMinuto.setText(String.valueOf(this.act.cuandoEmpieza().getMinute()));

                this.espacioHoraFin.setText(String.valueOf(this.act.cuandoTermina().getHour()));
                this.espacioMinutoFin.setText(String.valueOf(this.act.cuandoTermina().getMinute()));

                // Evento
                if (this.act.cuandoEmpieza() != this.act.cuandoTermina()) {
                    this.tareaTerminado.setStyle("-fx-text-fill: white; -fx-background-color: white");
                    this.tareaTerminado.setDisable(true);
                    this.tareaTerminado.setOpacity(0);
                }
                //Tarea
                else {
                    if (((Tarea)act).estaCompleta() == true) {
                        this.tareaTerminado.setSelected(true);
                    }

                }
            }
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }


    public void ponerNombre(ActionEvent event) {
        this.nombreEvento = this.espacioNombre.getText();
    }

    public void ponerDescripcion(ActionEvent event) {
        this.descripcionEvento = this.espacioDescripcion.getText();
    }

    public void ponerEsDiaCompleto(ActionEvent event) {
        this.esDiaCompletoEvento = this.espacioEsDiaCompleto.isSelected();
    }

    public void ponerFecha(ActionEvent event) {
        this.fechaEvento = this.espacioElegirFecha.getValue();
    }

    public void ponerFrecuencia(ActionEvent event) {
        var numeroBoton = new EstadoBoton(this.espacioFrecuencia);
        this.espacioFrecuencia.setStyle(numeroBoton.getColorBoton());

        this.frecuenciaDiariaEvento = numeroBoton.getNumeroBoton();
        this.espacioFrecuencia.setText(String.valueOf(this.frecuenciaDiariaEvento));
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

        // No podemos tener eventos que finalicen antes de que empiecen
        if (horaEstablecida < horaMinima) {
            horaEstablecida = horaMinima;
        }
        espacioUnidad.setText(String.valueOf(horaEstablecida));

        return horaEstablecida;
    }

    public void ponerHora(ActionEvent event) {
        var horaFinal = this.configurarUnidadDeTiempo(this.espacioHora, 23, 0);
        this.comienzoEvento = this.comienzoEvento.withHour(horaFinal);

    }

    public void ponerHoraFin(ActionEvent event) {
        var horaFinal = this.configurarUnidadDeTiempo(this.espacioHoraFin, 23, this.comienzoEvento.getHour());
        this.finEvento = this.finEvento.withHour(horaFinal);

    }

    public void ponerMinuto(ActionEvent event) {
        var horaFinal = this.configurarUnidadDeTiempo(this.espacioMinuto, 59, 0);
        this.comienzoEvento = this.comienzoEvento.withMinute(horaFinal);

    }

    public void ponerMinutoFin(ActionEvent event) {
        var horaFinal = this.configurarUnidadDeTiempo(this.espacioMinutoFin, 59, 0);
        this.finEvento = this.finEvento.withMinute(horaFinal);

    }

    public void ponerTipoActividadTarea(ActionEvent event) {
        this.tipoActividad = "Tarea";
        this.espacioTipoActividad.setText("Tarea");
        this.espacioHoraFin.setDisable(true);
        this.espacioMinutoFin.setDisable(true);
    }

    public void ponerTipoActividadEvento(ActionEvent event) {
        this.tipoActividad = "Evento";
        this.espacioTipoActividad.setText("Evento");
        this.espacioHoraFin.setDisable(false);
        this.espacioMinutoFin.setDisable(false);
    }

    public void crearEvento(ActionEvent event) {
        if (this.act == null) {
            this.guardarEventoNuevo(event);
        }
        else {
            actualizarEvento(event);
        }
    }

    public void guardarEventoNuevo(ActionEvent event) {
        //Le ponemos todos los segundos y milisegundos a 0 para evitar problemas
        //con las alarmas y tener todo mas redondo
        //Google calendar tampoco te deja configurar segundos
        this.comienzoEvento = this.comienzoEvento.withSecond(0);
        this.comienzoEvento = this.comienzoEvento.withNano(0);

        LocalDateTime fechaComienzo = this.comienzoEvento.atDate(this.fechaEvento);

        int idEvento;
        if (this.tipoActividad == "Tarea") {
            idEvento = this.modelo.crearTarea(this.nombreEvento, this.descripcionEvento, this.esDiaCompletoEvento, fechaComienzo);
            for (Plazo plazo : this.listaPlazos) {
                this.modelo.modificarActividadAgregarAlarma(idEvento, plazo);
            }
        }
        else {
            this.finEvento = this.finEvento.withSecond(0);
            this.finEvento = this.finEvento.withNano(0);

            LocalDateTime fechaFin = this.finEvento.atDate(this.fechaEvento);

            idEvento = this.modelo.crearEvento(this.nombreEvento, this.descripcionEvento, this.esDiaCompletoEvento, fechaComienzo, fechaFin);

            this.modelo.modificarEventoFrecuencia(idEvento, new FrecuenciaDiaria(this.frecuenciaDiariaEvento, new RepeticionInfinita()));

            for (Plazo plazo : this.listaPlazos) {
                this.modelo.modificarActividadAgregarAlarma(idEvento, plazo);
            }
        }

	this.vista.actualizarVistaCalendario(this.fechaActual);

    }

    public void actualizarEvento(ActionEvent event) {
        var id = this.act.getID();
        this.modelo.modificarActividadNombre(id, this.nombreEvento);
        this.modelo.modificarActividadDescripcion(id, this.descripcionEvento);
        this.modelo.modificarEventoPlazoTemporal(id, this.comienzoEvento.atDate(this.fechaEvento), this.finEvento.atDate(this.fechaEvento));

    }

    public void elegirAlarma() {
        var elegirAlarma = new ElegirAlarma(this);

        try {
            elegirAlarma.start();
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }

    public void anadirAlarma(String plazoElegidoPorUsuario) {
        Plazo plazoParaAnadir;

        //Pongo un plazo por defecto porque sino java se pone triste de que
        //puede ser que haya una chance de que tal vez no sea el caso de la
        //posible posibilidad de que dado el caso mi variable no sea
        //inicializada
        plazoParaAnadir = Plazo.CINCOMINUTOSANTES;

        switch (plazoElegidoPorUsuario) {
        case "1 dia antes":
            plazoParaAnadir = Plazo.DIAANTES;
            break;
        case "1 hora antes":
            plazoParaAnadir = Plazo.HORAANTES;
            break;

        case "30 mins antes":
            plazoParaAnadir = Plazo.MEDIAHORAANTES;
            break;

        case "15 mins antes":
            plazoParaAnadir = Plazo.QUINCEMINUTOSANTES;
            break;

        case "10 mins antes":
            plazoParaAnadir = Plazo.DIEZMINUTOSANTES;
            break;

        case "5 mins antes":
            plazoParaAnadir = Plazo.CINCOMINUTOSANTES;
            break;
        }
        if (this.listaPlazos.contains(plazoParaAnadir) == true) {
            //Si ya lo tiene, no lo vuelvo a guardar
            return;
        }
        this.listaPlazos.add(plazoParaAnadir);
    }

    public void marcarTareaCompleta(ActionEvent event) {
        this.modelo.modificarTareaCompletarODescompletar(this.act.getID());
    }

    public void borrar(ActionEvent event) {

        // Evento
        if (this.act.cuandoEmpieza() != this.act.cuandoTermina()) {
            this.modelo.eliminarEvento(this.act.getID());
        }
        //Tarea
        else {
            this.modelo.eliminarTarea(this.act.getID());
        }
        this.stage.close();
    }

}
