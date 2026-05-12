import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Record {
    public int minutes;
    public int seconds;
    private LocalDateTime dateTime;
    public Record(int minutes, int seconds){
        this.seconds = seconds;
        this.minutes = minutes;
        this.dateTime= LocalDateTime.now();
    }
    public Record(int minutes, int seconds, LocalDateTime dateTime){
        this.seconds = seconds;
        this.minutes = minutes;
        this.dateTime= dateTime;
    }

    public int getMinutes(){
        return minutes;
    }
    public int getSeconds(){
        return seconds;
    }
    public String getFormattedDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return dateTime.format(formatter);
    }
    @Override
    public String toString() {
        return String.format("%02d:%02d | %s", minutes, seconds, getFormattedDate());
    }
}
