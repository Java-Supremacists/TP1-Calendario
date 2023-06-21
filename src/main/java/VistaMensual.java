import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Callback;

import java.awt.Button;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public class VistaMensual extends VistaCalendario {
    @FXML
    private FlowPane escenaPorMes;
    @FXML
    private GridPane grillaDelMes;
    public VistaMensual(FlowPane pantalla, Calendario modelo) throws IOException {
        super(pantalla, modelo);
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("mensual.fxml"));
        loader1.setController(this);
        loader1.load();
    }
    @Override
    public void ponermeAMi(FlowPane eliminar) {
        escenaActual.getChildren().remove(eliminar);
        escenaActual.getChildren().add(escenaPorMes);
    }
    @Override
    public String actualizarVista(LocalDateTime fecha) {
        ObservableList<Node> hijos = grillaDelMes.getChildren();
        Month mes = fecha.getMonth();
        long i = 0;
        Label Dia;
        Label Numero;
        for (Node e : hijos) {
            if (e.getClass().equals(VBox.class)) {
                LocalDateTime dia = fecha.plusDays(i);
                VBox diaDelMes = (VBox) e;
                hijos = diaDelMes.getChildren();
                if (dia.getDayOfMonth()==1 && i<14) {
                    mes = dia.getMonth();
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
        return mes.toString();
    }
    @Override
    public String getTipo() {
        return "Mes";
    }
    @Override
    public FlowPane getVista() {
        return escenaPorMes;
    }
    @Override
    public void visualizarActividades(List<Activities> hacerVisual,LocalDateTime fechaInicio) {
	// System.out.println("Empieza el mes");
	// System.out.println(fechaInicio);
        var dia = fechaInicio;
	dia = dia.withHour(23);
        var mes = fechaInicio.getMonth();
        for (Node e : grillaDelMes.getChildren()) {
            if (!dia.getMonth().equals(mes)) {
		// System.out.println("I BREAK");
                break;
            }
            if (e.getClass().equals(VBox.class)) {
		// System.out.println("Entro al if");
                //e siempre va a ser una VBox
                var hijo = (VBox) e;
                var listaHijos = hijo.getChildren();//primer elemento el dia y el segundo es la fecha y sino solo unicamente la fecha
                listaHijos.removeIf(i -> !i.getClass().equals(Label.class)); //remuevo lo de adentro del Vbox excepto las fechas
                ListView<Activities> agregar = listaFormal();//creo la lista formal
                if (hacerVisual!=null) {
		    // System.out.println("entro al segundo if");
                    for (Activities act : hacerVisual) {
			// System.out.println("Entro al for");
                        try {
                            var casteado = (Tarea) act;
                            var diaActividadComienza = casteado.cuandoEmpieza().getDayOfMonth();
                            if (dia.getDayOfMonth() == diaActividadComienza) {

                                agregar.getItems().add((Activities)casteado);
                            }
                        }
                        catch (ClassCastException errorJavaXD) {
			    // System.out.println();
			    // System.out.println("ESTE ES EL DIA DEL MES");
			    // System.out.println(dia);
                            var casteado = (Evento) act;
			    var caeONoCae = casteado.caeElDia(dia);
			    // System.out.println(caeONoCae);
                            if (caeONoCae == true) {
                                agregar.getItems().add((Activities)casteado);
                            }
                        }
                    }
                }
                listaHijos.add(agregar); //añado las listas formales
                dia = dia.plusDays(1);
            }
        }

    }

    public ListView<Activities> listaFormal() {
        ListView<Activities> listView = new ListView<>();
        listView.setCellFactory(new Callback<>() {
            public ListCell<Activities> call(ListView<Activities> listView) {
                return new ListCell<>() {
                    protected void updateItem(Activities texto, boolean celdaVacia) {
                        super.updateItem(texto, celdaVacia);
                        if (celdaVacia || texto == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            // Crear un punto de color a la izquierda del texto
                            Circle bulletPoint = new Circle(9, Color.web(GeneradorDeColores.getColorFromId(texto.getID())));

                            // Crear un contenedor para el punto de color y el texto
                            var nombre = new Label(texto.getTitulo());
                            HBox hbox = new HBox(bulletPoint, nombre);
                            hbox.setSpacing(10);

                            // En este stack esta el invicible
                            var id = new Label(String.valueOf(texto.toString()));
                            id.setOpacity(0);
                            var stack = new StackPane(hbox, id);
                            var visualizador = new VisualizadorActividad(texto);
                            stack.setOnMouseClicked(x -> visualizador.start());
                            setGraphic(stack);
                        }
                    }
                };
            }
        });
        return listView;
    }
}
