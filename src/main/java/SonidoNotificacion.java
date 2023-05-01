public class SonidoNotificacion extends BaseDecoradaNotificacion{
    public SonidoNotificacion(Usuario usuario, Notificacion siguienteNotificacion) {
        super(usuario, siguienteNotificacion);
    }
    @Override
    public void send(String nombre, String descripcion) {
        //Implementar alarma Sonora aca
        super.send(nombre, descripcion);
    }
}
