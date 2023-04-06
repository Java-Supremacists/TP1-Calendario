// import Calendario;

public class Main {
    public static void main(String[] args) {
	var tarea = new Tarea("Esto es una tarea","descripcion",false);
	var evento = new Tarea("Esto es un evento","descripcion",false);

	Actividad[] array = new Actividad[2];
	array[0] = tarea;
	array[1] = evento;

	// var cal = new Calendario("Nombre",array);
	// System.out.println(cal.tituloPrimeraActividad());
	// System.out.println(cal.tituloSegundaActividad());
	

    }
}
