import java.time.LocalDateTime;
/**
 * Frecuencia
 */
public interface Frecuencia{

    LocalDateTime finDeLasRepeticionesDadaFecha(LocalDateTime fechaComienzo);

    boolean dadoComienzoCaeElDia(LocalDateTime inicioEvento, LocalDateTime diaEspecifico);

    
}
