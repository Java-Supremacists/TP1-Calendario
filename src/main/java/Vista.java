import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.time.LocalDateTime;



public class Vista {
    /*
    *
        @FXML
        private HBox barraDeHerramientas;

        @FXML
        private VBox barraLateralIzquierda;

        @FXML
        private ListView listaPosibleParaUsar;

        @FXML
        private ScrollPane escroleoDeGrilla;

        @FXML
        private Pane barraSuperiorDiaria;

        @FXML
        private ScrollPane ScrollGrillaDiaxHora;
     */
    @FXML
    private FlowPane pantalla;
    @FXML
    private Button botonDeHoy;
    @FXML
    private Button visualizacionAnterior;
    @FXML
    private Button visualizacionPosterior;
    @FXML
    private Label mesDelCalendario;
    @FXML
    private ChoiceBox<String> tipoDeVisualizacion;
    private final String[] elecciones = {"Dia","Semana","Mes"};
    @FXML
    private Button botonCrearActividad;
    // private ChoiceBox<String> botonCrearActividad;
    // private final String[] actividades = {"Evento","Tarea"};

    //--------- Semanal ---------
    @FXML
    private FlowPane escenaPorSemana;
    @FXML
    private GridPane grillaDeDiasFijos;
    @FXML
    private GridPane grillaDiasxHorarios;
    //--------- Semanal ---------

    //--------- Diario ---------
    @FXML
    private FlowPane escenaPorDia;
    @FXML
    private GridPane grillaDiaxHora;
    @FXML
    private GridPane grillaConElDia;
    //--------- Diario ---------

    //--------- Mensual ---------
    @FXML
    private FlowPane escenaPorMes;
    @FXML
    private GridPane grillaDelMes;

    private Calendario modelo;

    //--------- Mensual ---------
    private final Scene escena;
    private final InterfazGrafica controlador;
    private VistaCalendario strategy;
    public Vista(InterfazGrafica controller, Calendario modelo) throws IOException {
        //cargamos archivos
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("escenario1.fxml"));
        loader1.setController(this);
        FlowPane pantalla1 = loader1.load();
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("escenario2.fxml"));
        loader2.setController(this);
        loader2.load();
        //cargamos archivos

        //guardamos atributos
        controlador = controller;
        this.modelo = modelo;
        strategy = new VistaSemanal(escenaPorSemana,pantalla);
        this.escena = new Scene(pantalla1,854,480); //creamos la escena

        //metemos implementacion interna
        tipoDeVisualizacion.getItems().addAll(elecciones);
        tipoDeVisualizacion.setValue("Semana");

        tipoDeVisualizacion.setOnAction(this::cambiarVistaCalendario);

        botonCrearActividad.setOnAction(this::crearActividad);

        // botonCrearActividad.getItems().addAll(actividades);
    }
    public Scene getScene() {
        return this.escena;
    }
    public String getVisualizacion() {
        return tipoDeVisualizacion.getValue();
    }
    public void actualizarVistaCalendario(LocalDateTime primerDia) {
        //HAY UN ERROR PARA ARREGLAR ACA EN STRATEGY
        //Actualizar las grillas segun si va una semana/día/mes antes o después del actual
        //implementar aca para facilitar la actualizacion de los días facilmente
        String months = "";
        switch (strategy.getTipo()) {
        case "Dia" -> months = strategy.actualizarVista(primerDia,grillaConElDia);
        case "Semana" -> months = strategy.actualizarVista(primerDia,grillaDeDiasFijos);
        case "Mes" -> months = strategy.actualizarVista(primerDia,grillaDelMes);
        }
        mesDelCalendario.setText(months);
    }
    public void cambiarVistaCalendario(ActionEvent event) {
        String visualizacion = tipoDeVisualizacion.getValue();
        if (!strategy.getTipo().equals(visualizacion)) {
            LocalDateTime hoy = LocalDateTime.now();
            switch (visualizacion) {
            case "Dia" -> {
                        strategy = new VistaDiaria(escenaPorDia,pantalla,strategy.getVista());
                        controlador.setFechaActual(hoy);
                        break;
                    }
                case "Semana" -> {
                            strategy = new VistaSemanal(escenaPorSemana,pantalla,strategy.getVista());
                            controlador.setFechaActual(InterfazGrafica.domingoAnteriorCercano(hoy));
                            break;
                        }
                    case "Mes" -> {
                                strategy = new VistaMensual(escenaPorMes,pantalla,strategy.getVista());
                                controlador.setFechaActual(InterfazGrafica.primerDiaDelMes(hoy.getYear(), hoy.getMonth()));
                                break;
                            }
                        }
            this.actualizarVistaCalendario(controlador.getFechaActual()); // Esto es momentaneo para que sea visible
        }
    }
    public void botonDeHoyActividad(EventHandler<ActionEvent> evento) {
        botonDeHoy.setOnAction(evento);
    }
    public void visualizacionAnteriorActividad(EventHandler<ActionEvent> evento) {
        visualizacionAnterior.setOnAction(evento);
    }
    public void visualizacionPosteriorActividad(EventHandler<ActionEvent> evento) {
        visualizacionPosterior.setOnAction(evento);
    }
    public void crearActividad(ActionEvent evento) {
        var crearActividad = new CreadorActividad(this.modelo, this.controlador);
        try {
            crearActividad.start();
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
