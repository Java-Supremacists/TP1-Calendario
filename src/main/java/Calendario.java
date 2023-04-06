import java.util.ArrayList;


public class Calendario {
	private String nombre;
	private ArrayList<Actividad> listaActividades = new ArrayList<Actividad>();
	    // private int[] lanzamientos = new int[21];


	public Calendario(String nombreCalendario){ 
		this.nombre = nombreCalendario;
	}

	

	public void crearTarea(String nombre, String description, boolean esDiaCompleto){
		var nuevaTarea = new Tarea(nombre, description, esDiaCompleto);
		this.listaActividades.add(nuevaTarea);
	}

	public Actividad obtenerActividadPorIndice(int ID){
		return this.listaActividades.get(ID);
	}
	
}
