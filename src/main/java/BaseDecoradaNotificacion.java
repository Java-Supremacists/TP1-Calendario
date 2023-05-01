public class BaseDecoradaNotificacion extends Notificacion{
    public Notificacion wrapper;
    public BaseDecoradaNotificacion(Usuario usuario,Notificacion siguienteNotificacion) {
        super(usuario);
        wrapper = siguienteNotificacion;
    }
    @Override
    public void send(String nombre, String descripcion) {
        //implementacion Para mandar mensaje aca por lugar específico
        wrapper.send(nombre,descripcion);
    }
}
