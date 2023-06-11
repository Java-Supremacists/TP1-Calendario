import javafx.scene.control.TextField;

/**
 * EstadoBoton
 * Esta clase solo existe porque java no nos deja devolver dos valores de distinto tipo.
 * Gracias por nada POO >:(
 */
public class EstadoBoton {
    private String colorBoton;
    private Integer numeroBoton;

    public EstadoBoton (TextField numeroBoton) {
        try {
            this.numeroBoton = Integer.parseInt(numeroBoton.getText());
            this.numeroBoton = (this.numeroBoton < 0 ? 0 : this.numeroBoton);
            this.colorBoton = "-fx-control-inner-background: white";
        }
        catch (NumberFormatException e) {
            this.numeroBoton = 0;
            this.colorBoton = "-fx-control-inner-background: crimson";
        }
    }

    public String getColorBoton() {
        return this.colorBoton;
    }

    public Integer getNumeroBoton() {
        return this.numeroBoton;
    }

}
