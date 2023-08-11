package database;

public class Item {
    private final String idItem;
    private String nameItem;

    // Konstruktor untuk kelas Item.
    public Item(String itemId, String itemName) {
        this.idItem = itemId;
        this.nameItem = itemName;
    }

    // Fungsi untuk mendapatkan nama item.
    public String getNameItem() {
        return nameItem;
    }

    // Fungsi untuk mengubah nama item.
    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    // Fungsi untuk mendapatkan id item.
    public String getIdItem() {
        return idItem;
    }
}
