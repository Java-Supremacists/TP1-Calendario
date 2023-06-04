import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class VistaMensual extends VistaCalendario {
    public VistaMensual(FlowPane visualizacion, FlowPane pantalla, FlowPane eliminar) {
        super(visualizacion, pantalla, eliminar);
    }
    /*public VistaMensual(FlowPane visualizacion, FlowPane pantalla) {
        super(visualizacion, pantalla);
    }*/
    @Override
    public List<Month> actualizarVista(LocalDateTime fecha, GridPane grillaMes) {
        ObservableList<Node> hijos = grillaMes.getChildren();
        Month mes = null;
        long i = 0;
        Label Dia;
        Label Numero;
        for (Node e : hijos) {
            if (e.getClass().equals(VBox.class)) {
                LocalDateTime dia = fecha.plusDays(i);
                VBox diaDelMes = (VBox) e;
                hijos = diaDelMes.getChildren();
                if (dia.getDayOfMonth()==1 && i<14){
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
        ArrayList<Month> months = new ArrayList<>(1);
        months.add(0,mes);
        return months;
    }
    @Override
    public String getTipo() {
        return "Mes";
    }
}