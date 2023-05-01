public class Notificacion {
    private final Usuario remitente;
    public Notificacion(Usuario usuario){
        remitente = usuario;
    }
    public void send(String nombre,String descripcion){
            remitente.alertar(nombre, descripcion);
            //Notificacion por mensaje en pantalla de bloqueo
    }
}
