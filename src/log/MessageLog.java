package log;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class MessageLog {
    private final String currentTime;

    private final String itemName;

    private final String logActivity;

    // Konstruktor untuk kelas MessageLog.
    public MessageLog(String itemName, String logActivity) {
        // Buat objek SimpleDateFormat untuk mengatur format tanggal dan waktu.
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Dapatkan waktu saat ini dalam bentuk timestamp.
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        // Format waktu ke dalam string sesuai dengan format yang ditentukan.
        currentTime = format.format(timestamp);
        // Set atribut itemName dengan nama item yang diberikan.
        this.itemName = itemName;
        // Set atribut activityLog dengan aktivitas log yang diberikan.
        this.logActivity = logActivity;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public String getItemName() {
        return itemName;
    }

    public String getLogActivity() {
        return logActivity;
    }

}
