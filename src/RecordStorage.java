import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.prefs.Preferences;
import java.time.format.DateTimeFormatter;

public class RecordStorage {
    private PriorityQueue<Record> highTime;
    private Preferences prefs;
    private int maxSize = 5;
    private static final String DATE_FORMAT = "dd.MM.yyyy HH:mm:ss";
    public RecordStorage(){
        highTime = new PriorityQueue<>(Comparator.comparingInt((Record r) -> r.getMinutes() * 60 + r.getSeconds()));
        prefs = Preferences.userNodeForPackage(RecordStorage.class);
        loadRecordsFromPrefs();
    }
    public void addRecord(int minutes, int seconds){
        Record newRecord = new Record(minutes, seconds);
        highTime.add(newRecord);
        if(highTime.size() > maxSize){
            highTime.poll();
        }
        saveRecordsToPrefs();
    }
    public List<Record> getAllRecords(){
        List<Record> recordList = new ArrayList<>();
        PriorityQueue<Record> temp = new PriorityQueue<>(highTime);
        while(!temp.isEmpty()){
            recordList.add(temp.poll());
        }
        return recordList;
    }
    private void saveRecordsToPrefs(){
        List<Record> newList = new ArrayList<>(getAllRecords());
        prefs.putInt("record_count", newList.size());
        int i = 0;
        for (Record temp : newList) {
            prefs.putInt("minutes" + i, temp.getMinutes());
            prefs.putInt("seconds" + i, temp.getSeconds());
            prefs.put("date" + i, temp.getFormattedDate());
            i++;
        }
    }
    private void loadRecordsFromPrefs(){
        int count = prefs.getInt("record_count", 0);
        for (int i = 0; i<count; i++) {
            int minutes = prefs.getInt("minutes" + i, 0);
            int seconds = prefs.getInt("seconds" + i, 0);
            String date = prefs.get("date" + i, "");
            LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
            highTime.add(new Record(minutes, seconds, dateTime));
        }
    }
    public void clearRecords(){
        highTime.clear();

        int count = prefs.getInt("record_count", 0);
        prefs.remove("record_count");

        for (int i = 0; i < count; i++) {
            prefs.remove("record_" + i + "_minutes");
            prefs.remove("record_" + i + "_seconds");
            prefs.remove("record_" + i + "_date");
        }

    }

}
