package log;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class MessageLog {
    private final String currentTime;

    private final String itemName;

    private final String activityLog;

    public MessageLog (String nameItem, String logActivity) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        currentTime = format.format(timestamp);
        itemName = nameItem;
        activityLog = logActivity;
    }
    public String getLog () {
        return currentTime + " - " + itemName + " - " + activityLog;
    }
 }
