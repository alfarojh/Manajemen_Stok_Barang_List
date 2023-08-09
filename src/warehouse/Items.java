package warehouse;

public class Items {

    private String nameItem;

    private int qtyItem;

    // Konstruktor untuk membuat item dengan nama dan jumlah sesuai paramter.
    public Items(String itemName, int itemCount) {
        setNameItem(itemName); // Mengatur nama item.
        setQtyItem(itemCount); // Mengatur jumlah stok item.
    }

    // Mengatur nama item.
    public void setNameItem(String itemName) {
        nameItem = itemName;
    }

    // Mendapatkan nama item.
    public String getNameItem() {
        return nameItem;
    }

    // Mengatur jumlah stok item.
    public void setQtyItem(int itemCount) {
        qtyItem = itemCount;
    }

    // Mendapatkan jumlah stok item.
    public int getQtyItem() {
        return qtyItem;
    }
}
