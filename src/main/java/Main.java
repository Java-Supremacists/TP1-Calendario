// import Calendario;

import java.time.LocalDateTime;
import java.time.Month;

public class Main {
    public static void main(String[] args) {
        LocalDateTime a1 = LocalDateTime.of(0, Month.JANUARY,1,0,0);
        LocalDateTime a2 = LocalDateTime.of(0,Month.JANUARY,1,5,0);
        LocalDateTime a3 = LocalDateTime.of(0,Month.JANUARY,1,0,30);
        LocalDateTime a4 = LocalDateTime.of(0,Month.JANUARY,3,4,12);
        LocalDateTime a5 = LocalDateTime.of(0,Month.JANUARY,21,10,29);
        LocalDateTime fecha = LocalDateTime.of(2023,Month.JULY,20,10,30);
        System.out.println(fecha.minusDays(a1.getDayOfMonth()).minusHours(a1.getHour()).minusMinutes(a1.getMinute()));
        System.out.println(fecha.minusDays(a2.getDayOfMonth()).minusHours(a2.getHour()).minusMinutes(a2.getMinute()));
        System.out.println(fecha.minusDays(a3.getDayOfMonth()).minusHours(a3.getHour()).minusMinutes(a3.getMinute()));
        System.out.println(fecha.minusDays(a4.getDayOfMonth()).minusHours(a4.getHour()).minusMinutes(a4.getMinute()));
        System.out.println(fecha.minusDays(a5.getDayOfMonth()).minusHours(a5.getHour()).minusMinutes(a5.getMinute()));
    }
}
