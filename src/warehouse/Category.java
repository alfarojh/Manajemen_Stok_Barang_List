package warehouse;

import input.InputHandler;
import java.util.ArrayList;

public class Category {
    private final InputHandler inputHandler = new InputHandler();
    private String nameCategory;
    private final ArrayList<Items> items = new ArrayList<>();

    // Konstruktor untuk membuat kategori berdasarkan parameter.
    public Category(String categoryName) {
        setNameCategory(categoryName);
    }

    // Mengatur nama kategori.
    public void setNameCategory(String categoryName) {
        nameCategory = categoryName;
    }

    // Mendapatkan nama kategori.
    public String getNameCategory() {
        return nameCategory;
    }

    // =================================== Item Section ========================================

    // Mendapatkan item berdasarkan indeks item.
    public Items getItem(int itemIndex) {
        return items.get(itemIndex);
    }

    // Mendapatkan jumlah item dalam kategori.
    public int getItemSize() {
        return items.size();
    }

    // Menambahkan item baru ke dalam kategori.
    public void addItem(String itemName, int itemCount) {
        if (itemCount < 0) {
            inputHandler.errorMessage("Stok tidak boleh bernilai negatif.");
            return;
        }
        items.add(new Items(itemName, itemCount));
    }

    // Menghapus item dari kategori berdasarkan indeks item.
    public void removeItem(int itemIndex) {
        try {
            System.out.println("Item " + items.get(itemIndex).getNameItem() + " berhasil dihapus.");
            items.remove(itemIndex);
        } catch (IndexOutOfBoundsException e) {
            inputHandler.errorMessage("Item tidak ada, gagal menghapus.");
        }
    }

    // Memperbarui nama item dalam kategori berdasarkan indeks item.
    public void updateItem(int itemIndex, String newName) {
        try {
            System.out.println("Item " + items.get(itemIndex).getNameItem()
                    + " berhasil diupdate menjadi " + newName + ".");
            items.get(itemIndex).setNameItem(newName);
        } catch (IndexOutOfBoundsException e) {
            inputHandler.errorMessage("Item tidak ditemukan, gagal mengupdate nama.");
        }
    }

    // Memperbarui jumlah stok item dalam kategori berdasarkan indeks item.
    public void updateQty(int itemIndex, int newQty) {
        try {
            System.out.println("Item " + items.get(itemIndex).getNameItem()
                    + " berhasil diupdate dari " + items.get(itemIndex).getQtyItem()
                    + " menjadi " + newQty + ".");
            items.get(itemIndex).setQtyItem(newQty);
        } catch (IndexOutOfBoundsException e) {
            inputHandler.errorMessage("Item tidak ditemukan, gagal mengupdate jumlah item.");
        }
    }
    // =================================== Item Section ========================================
}
