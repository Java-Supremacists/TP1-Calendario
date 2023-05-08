import java.time.LocalDateTime;
/**
 * RepeticionFecha
 */
public class RepeticionFecha implements Repeticion {
    private LocalDateTime fechaFinRepeticion;

    public RepeticionFecha(LocalDateTime fechaFinRepeticion) {
        this.fechaFinRepeticion = fechaFinRepeticion;
    }

    @Override
    public boolean estaDentroDeRepeticiones(LocalDateTime fechaPedida) {
        boolean estaDespuesDelLimite = fechaPedida.isBefore(this.fechaFinRepeticion);
        boolean esJustoElLimite = fechaPedida.isEqual(this.fechaFinRepeticion);
        boolean estaDentro = (estaDespuesDelLimite || esJustoElLimite);
        return estaDentro;
    }

}
