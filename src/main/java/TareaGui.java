/**
 * TareaGui
 */
public class TareaGui {

    private int idTarea;

    private String color;

    public TareaGui(int idTarea) {
        this.idTarea = idTarea;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
	return this.idTarea;
    }
}
