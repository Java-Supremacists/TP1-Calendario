import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;


public class CreadorActividad {

    private Scene escena;

    private InterfazGrafica controlador;
    
    public CreadorActividad(InterfazGrafica controller) throws IOException {
        FXMLLoader crearActividad = new FXMLLoader(getClass().getResource("crearActividad.fxml"));
        crearActividad.setController(this);
        Parent pantalla1 = crearActividad.load();
        //cargamos archivos

        //guardamos atributos
        // this.controlador = controller;
        this.escena = new Scene(pantalla1,854,480); //creamos la escena

        //metemos implementacion interna
        // botonCrearActividad.getItems().addAll(actividades);
    }
    
    public Scene getScene(){
        return this.escena;
    }
	
}
