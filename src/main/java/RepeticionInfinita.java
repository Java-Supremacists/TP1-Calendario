import java.time.LocalDateTime;
/**
 * RepeticionInfinita
 */
public class RepeticionInfinita implements Repeticion {

    public RepeticionInfinita() {
    }

    @Override
    public boolean estaDentroDeRepeticiones(LocalDateTime fechaPedida) {
        return true;
    }
}
