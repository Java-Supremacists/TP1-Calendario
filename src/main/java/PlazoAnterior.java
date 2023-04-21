import java.time.Duration;

public enum PlazoAnterior {
    DIAANTES("1 Dia Antes",Duration.ofDays(1L)),
    HORAANTES("1 Hora Antes",Duration.ofHours(1L)),
    MEDIAHORAANTES("30 minutos Antes",Duration.ofMinutes(30L)),
    QUINCEMINUTOSANTES("15 minutos Antes",Duration.ofMinutes(15L)),
    DIEZMINUTOSANTES("10 minutos Antes",Duration.ofMinutes(10L)),
    CINCOMINUTOSANTES("5 Dia Antes",Duration.ofMinutes(5L)),
    ;
    private final String horarios;
    private final Duration tiempo;
    private PlazoAnterior(String s,Duration t) {
        horarios = s;
        tiempo = t;
    }


    public Duration elHorarioEstablecido(String s){
        Duration devolver = null;
        for (PlazoAnterior p: PlazoAnterior.values()){
            if (s.equals(p.horarios)){
                devolver = p.tiempo;
            }
        }
        return devolver;
    }
}
