import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Vista {
    @FXML
    private FlowPane pantalla;
    @FXML
    private FlowPane escenarioVacioInicial;
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
    @FXML
    private Button botonCrearActividad;
    private final Calendario modelo;
    private final Scene escena;
    private final InterfazGrafica controlador;
    private VistaCalendario strategy;
    private final List<VistaCalendario> estrategiasCargadas = new ArrayList<>(3);
    public Vista(InterfazGrafica controller, Calendario modelo) throws IOException {
        //cargamos archivos
        FXMLLoader loader = new FXMLLoader(getClass().getResource("escenario1.fxml"));
        loader.setController(this);
        loader.load();
        //cargamos archivos

        //guardamos atributos
        controlador = controller;
        this.modelo = modelo;
        estrategiasCargadas.add(0,new VistaDiaria(pantalla, modelo));
        estrategiasCargadas.add(1,new VistaSemanal(pantalla, modelo));
        estrategiasCargadas.add(2,new VistaMensual(pantalla, modelo));
        strategy = estrategiasCargadas.get(1);
        strategy.ponermeAMi(escenarioVacioInicial);
        this.escena = new Scene(pantalla,854,480); //creamos la escena

        //metemos implementacion interna
        String[] elecciones = {"Dia", "Semana", "Mes"};
        tipoDeVisualizacion.getItems().addAll(elecciones);
        tipoDeVisualizacion.setValue("Semana");
        tipoDeVisualizacion.setOnAction(this::cambiarVistaCalendario);
        botonCrearActividad.setOnAction(this::crearActividad);
    }
    public Scene getScene() {
        return this.escena;
    }
    public String getVisualizacion() {
        return tipoDeVisualizacion.getValue();
    }
    public void actualizarVistaCalendario(LocalDateTime primerDia) {
        String months;
        months = strategy.actualizarVista(primerDia);
        LocalDateTime entre = primerDia.minusDays(1);
        LocalDateTime termina = null;
        switch (strategy.getTipo()) {
        case "Dia" -> {
                    termina = primerDia.plusDays(1);
                }
            case "Semana" -> {
                        termina = primerDia.plusDays(7);
                    }
                case "Mes" -> {
                            termina = primerDia.plusMonths(1);
                        }
                    }
        strategy.visualizarActividades(modelo.actividadesEnRango(entre,termina),primerDia);
        mesDelCalendario.setText(months);
    }
    public void cambiarVistaCalendario(ActionEvent event) {
        String visualizacion = tipoDeVisualizacion.getValue();
        if (!strategy.getTipo().equals(visualizacion)) {
            LocalDateTime hoy = LocalDateTime.now();
            switch (visualizacion) {
            case "Dia" -> {
                        estrategiasCargadas.get(0).ponermeAMi(strategy.getVista());
                        strategy = estrategiasCargadas.get(0);
                        controlador.setFechaActual(hoy);
                    }
                case "Semana" -> {
                            estrategiasCargadas.get(1).ponermeAMi(strategy.getVista());
                            strategy = estrategiasCargadas.get(1);
                            controlador.setFechaActual(InterfazGrafica.domingoAnteriorCercano(hoy));

                        }
                    case "Mes" -> {
                                estrategiasCargadas.get(2).ponermeAMi(strategy.getVista());
                                strategy = estrategiasCargadas.get(2);
                                controlador.setFechaActual(InterfazGrafica.primerDiaDelMes(hoy.getYear(), hoy.getMonth()));
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
        var crearActividad = new CreadorActividad(this.modelo);
        try {
            crearActividad.start();
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
