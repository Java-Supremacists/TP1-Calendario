import java.time.LocalDateTime;

public class Usuario {
    private final String name;
    private final String email;
    private final String password;
    private final Notificacion notificacion;
    public Usuario(String name, String email, String password, Notificacion notificacion) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.notificacion = notificacion;
    }
    public boolean verificacion(String password1){
        if (password1 == null){
            return false;
        }
        return password1.equals(password);
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public void alertar(LocalDateTime horaActual){
        notificacion.send("",""); //Todavia se tiene q arreglar bien
    }

}
