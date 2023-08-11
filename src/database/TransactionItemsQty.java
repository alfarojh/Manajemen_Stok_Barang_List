package database;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TransactionItemsQty {
    private final Item item;
    private final int qty;
    private final String currentTime;

    // Konstruktor untuk kelas TransactionItemsQty.
    public TransactionItemsQty(Item item, int qty) {
        this.item = item;
        this.qty = qty;

        // Buat objek SimpleDateFormat untuk mengatur format tanggal dan waktu.
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Dapatkan waktu saat ini dalam bentuk timestamp.
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        // Format waktu ke dalam string sesuai dengan format yang ditentukan.
        this.currentTime = format.format(timestamp);
    }

    // Fungsi untuk mendapatkan objek item dalam transaksi.
    public Item getItem() {
        return item;
    }

    // Fungsi untuk mendapatkan jumlah item dalam transaksi.
    public int getQty() {
        return qty;
    }

    // Fungsi untuk mendapatkan waktu transaksi dalam bentuk string.
    public String getCurrentTime() {
        return currentTime;
    }
}
