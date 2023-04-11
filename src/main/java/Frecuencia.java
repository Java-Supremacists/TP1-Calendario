import java.time.LocalDateTime;
/**
 * Frecuencia
 */
public interface Frecuencia{

    // enum FinRepeticion{
	// INFINITA,
	// FECHA,
	// CANTIDAD
    // }

    // LocalDateTime finDeLasRepeticionesDadaFecha(LocalDateTime fechaComienzo);

    boolean dadoComienzoCaeElDia(LocalDateTime inicioEvento, LocalDateTime diaEspecifico);

    
}
