public class EmailNotificacion extends BaseDecoradaNotificacion {
    public EmailNotificacion(Usuario usuario, Notificacion siguienteNotificacion) {
        super(usuario, siguienteNotificacion);
    }
    @Override
    public void send(String nombre, String descripcion) {
        //implementar mandarMensaje por email aca
        super.send(nombre, descripcion);
    }
}
