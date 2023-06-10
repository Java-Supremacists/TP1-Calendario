/**
 * EstadoBoton
 * Esta clase solo existe porque java no nos deja devolver dos valores de distinto tipo. 
 * Gracias por nada POO >:(
 */
public class EstadoBoton {
    private String colorBoton;
    private Integer numeroBoton;

    public EstadoBoton (String colorBoton, Integer numeroBoton) {
	this.colorBoton = colorBoton;
	this.numeroBoton = numeroBoton;
    }

    public String getColorBoton() {
	return this.colorBoton;
    }

    public Integer getNumeroBoton() {
	return this.numeroBoton;
    }
	
}
