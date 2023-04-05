import java.time.LocalDateTime;
import java.util.ArrayList;

public class Activities {
    private final String name;
    private final String description;
    //private final LocalDateTime localDateTimeFinal;
    private final ArrayList<LocalDateTime> alarm;
    private final boolean isComplete;

    public Activities(String name, String description, ArrayList<LocalDateTime> alarm, boolean isComplete) {
        this.name = name;
        this.description = description;
        //this.localDateTimeFinal = localDateTimeFinal;
        this.alarm = alarm;
        this.isComplete = isComplete;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<LocalDateTime> getAlarm() {
        return alarm;
    }

    public boolean isComplete() {
        return isComplete;
    }
}
