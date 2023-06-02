import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
    private ChoiceBox<String> botonCrearActividad;
    private final String[] actividades = {"Evento","Tarea"};

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
    private VBox panelConElDia;
    //--------- Diario ---------

    //--------- Mensual ---------
    @FXML
    private FlowPane escenaPorMes;
    @FXML
    private GridPane grillaDelMes;

    //--------- Mensual ---------
    private Scene escena;
    private final InterfazGrafica controlador;
    private FlowPane elementoActual;
    public Vista(InterfazGrafica controller) throws IOException {
        controlador = controller;

        //cargamos archivos
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("escenario1.fxml"));
        loader1.setController(this);
        FlowPane pantalla1 = loader1.load();
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("escenario2.fxml"));
        loader2.setController(this);
        loader2.load();
        //cargamos archivos

        this.escena = new Scene(pantalla1,854,480); //creamos la escena

        //metemos implementacion interna
        tipoDeVisualizacion.getItems().addAll(elecciones);
        tipoDeVisualizacion.setValue("Semana");
        elementoActual = escenaPorSemana;
        tipoDeVisualizacion.setOnAction(this::cambiarVistaCalendario);
        botonCrearActividad.getItems().addAll(actividades);
    }
    public Scene getScene(){
        return this.escena;
    }
    public String getVisualizacion(){
        return tipoDeVisualizacion.getValue();
    }
    public void actualizarVistaCalendario(LocalDateTime primerDia){
        //Actualizar las grillas segun si va una semana/día/mes antes o después del actual
        //implementar aca para facilitar la actualizacion de los días facilmente
        ObservableList<Node> hijos;
        Label Dia;
        Label Numero;
        long i = 0;
        switch (tipoDeVisualizacion.getValue()) {
            case "Dia" -> {
                hijos = panelConElDia.getChildren();
                Dia = (Label) hijos.get(0);
                Numero = (Label) hijos.get(1);
                Dia.setText(primerDia.getDayOfWeek().toString());
                Numero.setText("%d".formatted(primerDia.getDayOfMonth()));
                mesDelCalendario.setText(primerDia.getMonth().toString());
            }
            case "Semana" -> {
                hijos = grillaDeDiasFijos.getChildren();
                String mesDesde = primerDia.getMonth().toString();
                String mesHasta = "";
                for (Node e : hijos) {
                    if (e.getClass().equals(VBox.class)) {
                        LocalDateTime dia = primerDia.plusDays(i);
                        VBox apartado = (VBox) e;
                        hijos = apartado.getChildren();
                        Dia = (Label) hijos.get(0);
                        Numero = (Label) hijos.get(1);
                        Dia.setText(dia.getDayOfWeek().toString());
                        Numero.setText("%d".formatted(dia.getDayOfMonth()));
                        mesHasta = dia.getMonth().toString();
                        i++;
                    }
                }
                mesDelCalendario.setText(mesDesde + "-" + mesHasta);
            }
            case "Mes" -> {
                hijos = grillaDelMes.getChildren();
                String mes = "";
                for (Node e : hijos) {
                    if (e.getClass().equals(VBox.class)) {
                        LocalDateTime dia = primerDia.plusDays(i);
                        VBox apartado = (VBox) e;
                        hijos = apartado.getChildren();
                        if (dia.getDayOfMonth()==1){
                            mes = dia.getMonth().toString();
                        }
                        if (i < 7) {
                            Dia = (Label) hijos.get(0);
                            Numero = (Label) hijos.get(1);
                            Dia.setText(dia.getDayOfWeek().toString());
                            Numero.setText("%d".formatted(dia.getDayOfMonth()));
                        } else {
                            Numero = (Label) hijos.get(0);
                            Numero.setText("%d".formatted(dia.getDayOfMonth()));
                        }
                        i++;
                    }
                }
                mesDelCalendario.setText(mes);
            }
        }
    }
    public void cambiarVistaCalendario(ActionEvent event){
        String visualizacion = tipoDeVisualizacion.getValue();
        FlowPane nuevo = null;
        LocalDateTime hoy = LocalDateTime.now();
        switch (visualizacion) {
            case "Dia" -> {
                nuevo = escenaPorDia;
                controlador.setFechaActual(hoy);
            }
            case "Semana" -> {
                nuevo = escenaPorSemana;
                controlador.setFechaActual(InterfazGrafica.domingoAnteriorCercano(hoy));
            }
            case "Mes" -> {
                nuevo = escenaPorMes;
                controlador.setFechaActual(InterfazGrafica.primerDomingoCercanoAlMes(hoy.getYear(), hoy.getMonth()));
            }
        }
        pantalla.getChildren().remove(elementoActual);
        pantalla.getChildren().add(nuevo);
        elementoActual = nuevo;
        this.actualizarVistaCalendario(controlador.getFechaActual()); // Esto es momentaneo para que sea visible
    }
    public void botonDeHoyActividad(EventHandler<ActionEvent> evento){
        botonDeHoy.setOnAction(evento);
    }
    public void visualizacionAnteriorActividad(EventHandler<ActionEvent> evento){
        visualizacionAnterior.setOnAction(evento);
    }
    public void visualizacionPosteriorActividad(EventHandler<ActionEvent> evento){
        visualizacionPosterior.setOnAction(evento);
    }
    public void botonCrearActividadActividad(EventHandler<ActionEvent> evento){
        botonCrearActividad.setOnAction(evento);
    }
}
