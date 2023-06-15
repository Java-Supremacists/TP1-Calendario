import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public class InterfazGrafica extends Application {
    private final Calendario modelo = new Calendario();
    private final ControlerXml xmlManejador = new ControlerXml();
    private final String archivoGuardado = System.getProperty("user.dir") + "/archivoGuardado";
    private LocalDateTime fechaActual;
    @Override
    public void start(Stage stage) throws Exception {

        var archivoExiste = new File(this.archivoGuardado);
        if (archivoExiste.exists()) {
            this.xmlManejador.cargarXml(this.modelo, new FileInputStream(archivoGuardado));
        }

        var vista = new Vista(this, this.modelo); //por defecto viene con una vista semanal
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
                    fechaActual = fechaActual.minusMonths(1);
                    fechaActual = primerDiaDelMes(fechaActual.getYear(),fechaActual.getMonth());
                }
            }
            vista.actualizarVistaCalendario(fechaActual);
        });
        vista.visualizacionPosteriorActividad(event -> {
            switch (vista.getVisualizacion()) {
            case "Dia" -> fechaActual = fechaActual.plusDays(1);
            case "Semana" -> fechaActual = domingoAnteriorCercano(fechaActual.plusDays(7));
            case "Mes" -> {
                    fechaActual = fechaActual.plusMonths(1);
                    fechaActual = primerDiaDelMes(fechaActual.getYear(),fechaActual.getMonth());
                }
            }
            vista.actualizarVistaCalendario(fechaActual);
        });
        vista.botonDeHoyActividad(event -> {
            var nueva = LocalDateTime.now();
            switch (vista.getVisualizacion()) {
            case "Dia" -> fechaActual = nueva;
            case "Semana" -> fechaActual = domingoAnteriorCercano(nueva);
            case "Mes" -> fechaActual = primerDiaDelMes(nueva.getYear(),nueva.getMonth());
            }
            vista.actualizarVistaCalendario(fechaActual);
        });
        var timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                var horaActual = LocalDateTime.now();
                var siguienteAlarma = modelo.proximaAlarma();
                if (siguienteAlarma == null) {
                    return;
                }
                if (horaActual.getYear()== siguienteAlarma.getYear() && horaActual.getMonth()== siguienteAlarma.getMonth() && horaActual.getDayOfMonth()== siguienteAlarma.getDayOfMonth() && horaActual.getHour()== siguienteAlarma.getHour() && horaActual.getMinute()== siguienteAlarma.getMinute() ) {
                    //supergroncho para que entre JAJJAJAJA
                    //if (horaActual.equals(siguienteAlarma)) {
                    //funciona pero no logra entrar aca porque tienen q ser exactamente iguales y no lo aguanta
                    var alerta = new Alert(Alert.AlertType.INFORMATION);
                    List<Activities> actividadesSonando = modelo.sonarAlarmas();
                    StringBuilder texto = new StringBuilder("La/s Actividades siguientes estan sonando por las siguientes alarmas:\n");
                    for (Activities act : actividadesSonando) {
                        texto.append(act.getTitulo());
                        texto.append(": \n");
                        for (LocalDateTime alarmas : modelo.alarmasDeActividad(act.getID())) {
                            texto.append(alarmas.toString());
                            texto.append(" - ");
                        }
                        texto.append("\n");
                    }

                    alerta.setContentText(texto.toString());
                    alerta.show();
                }
            }
        };
        timer.start();
    }
    public void setFechaActual(LocalDateTime fechaActual) {
        this.fechaActual = fechaActual;
    }
    public LocalDateTime getFechaActual() {
        return fechaActual;
    }
    public static LocalDateTime domingoAnteriorCercano(LocalDateTime dia) {
        //para semanas
        LocalDateTime devolver = dia;
        while (devolver.getDayOfWeek()!= DayOfWeek.SUNDAY) {
            devolver = devolver.minusDays(1);
        }
        return devolver;
    }
    public static LocalDateTime primerDiaDelMes(int year, Month mes) {
        return LocalDateTime.of(year,mes,1,0,0);
    }
    @Override
    public void stop () throws FileNotFoundException {
        System.out.println("Generando xml");
        this.xmlManejador.generateXml(this.modelo, "Calendario", new FileOutputStream(archivoGuardado));

    }

}
