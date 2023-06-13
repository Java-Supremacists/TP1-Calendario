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
    public VistaMensual(FlowPane pantalla) throws IOException {
        super(pantalla);
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
        var dia = fechaInicio;
        var mes = fechaInicio.getMonth();
        for (Node e : grillaDelMes.getChildren()) {
            if (!dia.getMonth().equals(mes)) {
                break;
            }
            if (e.getClass().equals(VBox.class)) {
                //e siempre va a ser una VBox
                var hijo = (VBox) e;
                var listaHijos = hijo.getChildren();//primer elemento el dia y el segundo es la fecha y sino solo unicamente la fecha
                listaHijos.removeIf(i -> !i.getClass().equals(Label.class)); //remuevo lo de adentro del Vbox excepto las fechas
                ListView<String> agregar = listaFormal();//creo la lista formal
                if (hacerVisual!=null) {
                    for (Activities act : hacerVisual) {
                        try {
                            var casteado = (Tarea) act;
                            var diaActividadComienza = casteado.cuandoEmpieza().getDayOfMonth();
                            if (dia.getDayOfMonth() == diaActividadComienza) {

                                agregar.getItems().add(casteado.getTitulo());
				// agregar.getItems().add(new Button());
                            }
                        }
                        catch (ClassCastException errorJavaXD) {
                            var casteado = (Evento) act;
                            var diaActividadComienza = casteado.cuandoEmpieza().getDayOfMonth();
                            if (dia.getDayOfMonth() == diaActividadComienza) {

                                agregar.getItems().add(casteado.getTitulo());
                            }
                        }
                    }
                }
                listaHijos.add(agregar); //añado las listas formales
		// listaHijos.setAll(this::sus);j
		// listaHijos.setAll(System::out.println("SUS"));
		// listaHijos.setAll();

		// listaHijos.set
                dia = dia.plusDays(1);
            }
        }

    }
    public static String getRandomColor() {
        double red = Math.random();
        double green = Math.random();
        double blue = Math.random();
        return String.format("#%02X%02X%02X",
                             (int) (red * 255),
                             (int) (green * 255),
                             (int) (blue * 255));
    }
    public static ListView<String> listaFormal() {
        ListView<String> listView = new ListView<>();
        listView.setCellFactory(new Callback<>() {
            public ListCell<String> call(ListView<String> listView) {
                return new ListCell<>() {
                    protected void updateItem(String texto, boolean celdaVacia) {
                        super.updateItem(texto, celdaVacia);
                        if (celdaVacia || texto == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            // Crear un punto de color a la izquierda del texto
                            Circle bulletPoint = new Circle(9, Color.web(getRandomColor()));

                            // Crear un contenedor para el punto de color y el texto
			    var nombre = new Label(texto);
			    nombre.setOnMouseClicked(VistaMensual::sus);
                            HBox hbox = new HBox(bulletPoint, nombre);
                            hbox.setSpacing(10);
			    // hbox.buildEventDispatchChain

                            setGraphic(hbox);
                            setText("");
			    System.out.println("EYEYEYEYYE");
                        }
                    }
                };
            }
        });
        return listView;
    }

    public static void sus(MouseEvent event){
	System.out.println("SUSU");
    }

}
