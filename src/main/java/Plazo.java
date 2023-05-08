import java.time.Duration;

public enum Plazo {
    DIAANTES("1 Dia Antes",Duration.ofDays(1L)),
    HORAANTES("1 Hora Antes",Duration.ofHours(1L)),
    MEDIAHORAANTES("30 Minutos Antes",Duration.ofMinutes(30L)),
    QUINCEMINUTOSANTES("15 Minutos Antes",Duration.ofMinutes(15L)),
    DIEZMINUTOSANTES("10 Minutos Antes",Duration.ofMinutes(10L)),
    CINCOMINUTOSANTES("5 Minutos Antes",Duration.ofMinutes(5L)),
    CINCOMINUTOSDESPUES("5 Minutos Despues",Duration.ofMinutes(-5L)),
    DIEZMINUTOSDESPUES("10 Minutos Despues",Duration.ofMinutes(-10L)),
    QUINCEMINUTODESPUES("15 Minutos Despues",Duration.ofMinutes(-15L)),
    MEDIAHORADESPUES("30 Minutos Despues",Duration.ofMinutes(-30L)),
    ;
    private final String horarios;
    private final Duration tiempo;
    Plazo(String s, Duration t) {
        horarios = s;
        tiempo = t;
    }
    public Duration elHorarioEstablecido() {
        return tiempo;
    }
    public static Plazo compararHorariosDescriptos(String s) {
        for (Plazo p: Plazo.values()) {
            if (s.equals(p.horarios)) {
                return p;
            }
        }
        return null;
    }
}
