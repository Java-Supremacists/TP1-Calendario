import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;

public class InterfazGrafica extends Application {
    private final HashMap<Usuario,Calendario> modelo = new HashMap<>();
    private Scene calendario;
    @Override
    public void start(Stage stage) throws Exception {
        var vista = new Vista(this); //por defecto viene con una vista semanal
        //Aca hay q actualizar la vista para la semana con un localdatetime especial
        calendario = vista.getScene();
        stage.setScene(calendario);
        stage.setResizable(false);
        stage.show();
    }
    public static LocalDateTime domingoAnteriorCercano(LocalDateTime dia){
        //para semanas
        LocalDateTime devolver = dia;
        while (devolver.getDayOfWeek()!= DayOfWeek.SUNDAY){
            devolver = devolver.minusDays(1);
        }
        return devolver;
    }
    public static LocalDateTime primerDomingoCercanoAlMes(int year, Month mes){
        LocalDateTime primerDiaDelMes = LocalDateTime.of(year,mes,1,0,0);
        return domingoAnteriorCercano(primerDiaDelMes);
    }
}
