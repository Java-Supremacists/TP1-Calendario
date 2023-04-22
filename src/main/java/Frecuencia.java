import java.time.LocalDateTime;
/**
 * Frecuencia
 */
public interface Frecuencia{

    boolean dadoComienzoCaeElDia(LocalDateTime inicioEvento, LocalDateTime diaEspecifico);

    // LocalDateTime finDeLasRepeticionesDadaFecha(LocalDateTime fechaComienzo);

    
}
