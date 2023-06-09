import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;

public class InterfazGrafica extends Application {
    private final HashMap<Usuario,Calendario> modelo = new HashMap<>();
    private LocalDateTime fechaActual;
    @Override
    public void start(Stage stage) throws Exception {
        var vista = new Vista(this); //por defecto viene con una vista semanal
        fechaActual = domingoAnteriorCercano(LocalDateTime.now());
        vista.actualizarVistaCalendario(fechaActual);
        Scene calendario = vista.getScene();
        stage.setScene(calendario);
        stage.setResizable(false);
        stage.show();
	    stage.setTitle("Calendario Gerez - Orsi");
        vista.visualizacionAnteriorActividad(event -> {
            switch (vista.getVisualizacion()) {
                case "Dia" -> fechaActual = fechaActual.minusDays(1);
                case "Semana" -> fechaActual = domingoAnteriorCercano(fechaActual.minusDays(7));
                case "Mes" -> {
                    while (fechaActual.getDayOfMonth()!= 1){
                        fechaActual = fechaActual.plusDays(1);
                    }
                    fechaActual = fechaActual.minusMonths(1);
                    fechaActual = primerDomingoCercanoAlMes(fechaActual.getYear(),fechaActual.getMonth());
                }
            }
            vista.actualizarVistaCalendario(fechaActual);
        });
        vista.visualizacionPosteriorActividad(event -> {
            switch (vista.getVisualizacion()) {
                case "Dia" -> fechaActual = fechaActual.plusDays(1);
                case "Semana" -> fechaActual = domingoAnteriorCercano(fechaActual.plusDays(7));
                case "Mes" -> {
                    while (fechaActual.getDayOfMonth()!= 1){
                        fechaActual = fechaActual.plusDays(1);
                    }
                    fechaActual = fechaActual.plusMonths(1);
                    fechaActual = primerDomingoCercanoAlMes(fechaActual.getYear(),fechaActual.getMonth());
                }
            }
            vista.actualizarVistaCalendario(fechaActual);
        });
        vista.botonDeHoyActividad(event -> {
            var nueva = LocalDateTime.now();
            switch (vista.getVisualizacion()) {
                case "Dia" -> fechaActual = nueva;
                case "Semana" -> fechaActual = domingoAnteriorCercano(nueva);
                case "Mes" -> fechaActual = primerDomingoCercanoAlMes(nueva.getYear(),nueva.getMonth());
            }
            vista.actualizarVistaCalendario(fechaActual);
        });
    }
    public void setFechaActual(LocalDateTime fechaActual) {
        this.fechaActual = fechaActual;
    }
    public LocalDateTime getFechaActual() {
        return fechaActual;
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
