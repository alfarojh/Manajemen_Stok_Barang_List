package warehouse;

import custom.Text;
import input.InputHandler;
import log.MessageLog;

import java.util.ArrayList;

public class WarehouseController {
    private final InputHandler inputHandler = new InputHandler();
    private final ArrayList<Category> category = new ArrayList<>();
    private final ArrayList<MessageLog> log = new ArrayList<>();

    public WarehouseController () {
        category.add(new Category("makanan"));
        category.get(0).addItem("nasi", 20);
        category.get(0).addItem("ikan", 10);
        category.get(0).addItem("ayam", 15);

        category.add(new Category("pakaian"));
        category.get(1).addItem("baju", 20);
        category.get(1).addItem("syal", 10);
        category.get(1).addItem("celana", 15);
    }
    // ================================ Category Section =======================================

    // Memeriksa apakah daftar kategori kosong.
    public boolean isCategoriesEmpty() {
        return category.size() == 0;
    }

    // Memeriksa apakah kategori dengan nama tertentu sudah ada.
    public boolean isCategoryExist(String categoryName) {
        for (Category value : category) {
            if (value.getNameCategory().equalsIgnoreCase(categoryName)) {
                return true;
            }
        }
        return false;
    }

    // Memeriksa apakah indeks kategori tertentu ada dalam rentang yang valid.
    public boolean isCategoryExist(int categoryIndex) {
        return categoryIndex >= 0 && categoryIndex < category.size();
    }

    // Mendapatkan indeks dari kategori dengan nama tertentu.
    public int getIndexCategory(String categoryName) {
        for (int indexCategory = 0; indexCategory < category.size(); indexCategory++) {
            if (category.get(indexCategory).getNameCategory().equalsIgnoreCase(categoryName)) {
                return indexCategory;
            }
        }
        return -1; // Mengembalikan -1 jika kategori tidak ditemukan.
    }

    // Mendapatkan jumlah total kategori.
    public int getSizeCategory() {
        return category.size();
    }

    // Menambahkan kategori baru ke dalam daftar kategori.
    public void addCategory(String categoryName) {
        if (isCategoryExist(categoryName)) {
            inputHandler.errorMessage("Kategori sudah ada!"); // Menampilkan pesan error jika kategori sudah ada.
            return;
        }
        category.add(new Category(categoryName)); // Menambahkan kategori baru.
        System.out.println("Kategori " + categoryName + " berhasil ditambahkan."); // Menampilkan pesan sukses.
        log.add(new MessageLog(categoryName, "Tambah Kategori")); // Menambahkan pesan log.
    }

    // Menghapus kategori berdasarkan indeks.
    public void removeCategory(int categoryIndex) {
        // Jika kategori memiliki barang, tanyakan apakah ingin dilanjutkan.
        if (category.get(categoryIndex).getItemSize() > 0) {
            String choice = inputHandler.getInputText("Terdapat barang di kategori " +
                    category.get(categoryIndex).getNameCategory() +
                    ", apakah ingin dilanjut (Y/n)? ");
            if (!choice.equals("Y")) { return; }
        }

        try {
            System.out.println("Kategori " + category.get(categoryIndex).getNameCategory() + " berhasil dihapus.");
            log.add(new MessageLog(category.get(categoryIndex).getNameCategory(), "Hapus Kategori")); // Tambahkan pesan log.
            category.remove(categoryIndex); // Hapus kategori.
            } catch (IndexOutOfBoundsException e) {
            inputHandler.errorMessage("Kategori tidak ada, gagal menghapus.");
        }
    }

    // Menghapus kategori berdasarkan nama kategori.
    public void removeCategory(String categoryName) {
        removeCategory(getIndexCategory(categoryName)); // Panggil metode removeCategory dengan indeks yang sesuai.
    }

    // Memperbarui nama suatu kategori berdasarkan indeks.
    public void updateNameCategory(int categoryIndex, String newName) {
        try {
            System.out.println("Nama berhasil diupdate dari " + category.get(categoryIndex).getNameCategory() +
                    " menjadi " + newName);
            log.add(new MessageLog(category.get(categoryIndex).getNameCategory(),
                    "Update Nama Kategori menjadi " + newName)); // Tambahkan pesan log.
            category.get(categoryIndex).setNameCategory(newName); // Perbarui nama kategori.
            } catch (IndexOutOfBoundsException e) {
            inputHandler.errorMessage("Update gagal!");
        }
    }

    // Memperbarui nama suatu kategori berdasarkan nama kategori lama.
    public void updateNameCategory(String oldName, String newName) {
        updateNameCategory(getIndexCategory(oldName), newName);
    }

    // Menampilkan daftar kategori beserta indeksnya.
    public void showCategories() {
        Text text = new Text("",
                new String[]{"No", "Kategori"},
                new char[]{'c', 'l'},
                new int[]{3, 20});
        text.line();
        for (int indexCategory = 0; indexCategory < category.size(); indexCategory++) {
            text.printBody(indexCategory,
                    new String[]{String.valueOf(indexCategory + 1),
                            category.get(indexCategory).getNameCategory()});
//            System.out.println((indexCategory + 1) + ". " + category.get(indexCategory).getNameCategory());
        }
        text.printBody(category.size(),
                new String[]{"0",
                        "Keluar"});
        text.line();
    }

    // ================================ Category Section =======================================

    // =================================== Item Section ========================================

    // Memeriksa apakah item dengan indeks tertentu ada dalam kategori yang valid.
    public boolean isItemExistByCategory(int categoryIndex, int itemIndex) {
        if (!isCategoryExist(categoryIndex)) { // Jika kategori tidak ada, item pasti tidak ada.
            return false;
        }
        return itemIndex >= 0 && itemIndex < category.get(categoryIndex).getItemSize(); // Memeriksa rentang indeks item.
    }

    // Memeriksa apakah item dengan nama tertentu ada dalam kategori yang valid.
    public boolean isItemExistByCategory(int categoryIndex, String itemName) {
        return isItemExistByCategory(categoryIndex, getIndexItemByCategory(categoryIndex, itemName));
    }

    // Menambahkan item ke dalam kategori dengan jumlah tertentu.
    public void addItemByCategory(int categoryIndex, String itemName, int itemCount) {
        if (isItemExistByCategory(categoryIndex, itemName)) {
            return; // Jika item sudah ada, tidak perlu ditambahkan lagi.
        }
        category.get(categoryIndex).addItem(itemName, itemCount); // Tambahkan item ke dalam kategori.
        System.out.println("Item berhasil ditambahkan.");
        log.add(new MessageLog(itemName, "Tambah Item di Kategori " + category.get(categoryIndex).getNameCategory())); // Tambahkan pesan log.
    }

    // Menghapus item dari kategori berdasarkan indeks item.
    public void removeItemByCategory(int categoryIndex, int itemIndex) {
        if (!isItemExistByCategory(categoryIndex, itemIndex)) {
            return; // Jika item tidak ada dalam kategori, tidak perlu dihapus.
        }
        log.add(new MessageLog(category.get(categoryIndex).getItem(itemIndex).getNameItem(),
                "Hapus Item di Kategori " + category.get(categoryIndex).getNameCategory())); // Tambahkan pesan log.
        category.get(categoryIndex).removeItem(itemIndex); // Hapus item dari kategori.
    }

    // Menghapus item dari kategori berdasarkan nama item.
    public void removeItemByCategory(int categoryIndex, String itemName) {
        removeItemByCategory(categoryIndex, getIndexItemByCategory(categoryIndex, itemName));
    }

    // Memperbarui nama item dalam kategori berdasarkan indeks item.
    public void updateNameItemByCategory(int categoryIndex, int itemIndex, String newName) {
        if (!isItemExistByCategory(categoryIndex, itemIndex)) {
            return; // Jika item tidak ada dalam kategori, tidak perlu diperbarui.
        }
        log.add(new MessageLog(category.get(categoryIndex).getItem(itemIndex).getNameItem(),
                "Update Nama Item di dari " + category.get(categoryIndex).getNameCategory()
                        + " menjadi " + newName)); // Tambahkan pesan log.
        category.get(categoryIndex).updateItem(itemIndex, newName); // Perbarui nama item dalam kategori.
    }

    // Memperbarui nama item dalam kategori berdasarkan nama item lama.
    public void updateNameItemByCategory(int categoryIndex, String oldName, String newName) {
        updateNameItemByCategory(categoryIndex, getIndexItemByCategory(categoryIndex, oldName), newName);
    }

    // Mengambil indeks item berdasarkan nama dalam suatu kategori.
    private int getIndexItemByCategory(int categoryIndex, String itemName) {
        for (int indexItem = 0; indexItem < category.get(categoryIndex).getItemSize(); indexItem++) {
            if (category.get(categoryIndex).getItem(indexItem).getNameItem().equalsIgnoreCase(itemName)) {
                return indexItem;
            }
        }
        return -1; // Mengembalikan -1 jika item tidak ditemukan dalam kategori.
    }

    // Menampilkan daftar item dalam suatu kategori.
    public void showItemsByCategories(int categoryIndex) {
        if (categoryIndex < 0 || categoryIndex >= category.size()) { return; }

        Text text = new Text("Kategori: " + category.get(categoryIndex).getNameCategory(),
                new String[]{"Id", "Nama Barang", "Qty"},
                new char[]{'c','l','c'},
                new int[]{4, 30, 6});

        text.printTitle();
        if (category.get(categoryIndex).getItemSize() == 0) {
            text.printMessage("Barang tidak tersedia");
            return;
        }

        for (int indexItem = 0; indexItem < category.get(categoryIndex).getItemSize(); indexItem++) {
            text.printBody(indexItem, new String[]{String.valueOf(indexItem + 1),
                    category.get(categoryIndex).getItem(indexItem).getNameItem(),
                    String.valueOf(category.get(categoryIndex).getItem(indexItem).getQtyItem())});
        }
        text.line();
        System.out.println();
    }

    // Mencari item berdasarkan nama item.
    public void searchItem(String itemName) {
        for (int indexCategory = 0; indexCategory < getSizeCategory(); indexCategory++) {
            boolean itemExist = false;
            Text text = new Text("Kategori: " + category.get(indexCategory).getNameCategory(),
                    new String[]{"Id", "Nama Barang", "Qty"},
                    new char[]{'c','l','c'},
                    new int[]{4, 30, 6});
            text.printTitle();
            for (int indexItem = 0; indexItem < category.get(indexCategory).getItemSize(); indexItem++) {
                if (category.get(indexCategory).getItem(indexItem).getNameItem().toLowerCase()
                        .contains(itemName.toLowerCase())) {
                    text.printBody(indexItem, new String[]{String.valueOf(indexItem + 1),
                            category.get(indexCategory).getItem(indexItem).getNameItem(),
                            String.valueOf(category.get(indexCategory).getItem(indexItem).getQtyItem())});
                    itemExist = true;
                }
            }
            if (!itemExist) {
                text.printMessage("Barang tidak tersedia");

            }
            text.line();
            System.out.println();
        }
    }
    // =================================== Item Section ========================================

    // ==================================== Qty Section ========================================

    // Memperbarui jumlah stok item dalam suatu kategori berdasarkan indeks item.
    public void updateQtyItemByCategory(int categoryIndex, int itemIndex, int itemCount) {
        if (!isItemExistByCategory(categoryIndex, itemIndex)) {
            // Jika item tidak ada dalam kategori, tampilkan pesan dan kembali ke menu utama.
            System.out.println(isItemExistByCategory(categoryIndex, itemIndex) + " "
                    + categoryIndex + " " + itemIndex);
            return;
        }
        int currentQty = category.get(categoryIndex).getItem(itemIndex).getQtyItem();
        // Jika jumlah item yang dikurangi melebihi stok, tampilkan pesan error dan kembali ke menu utama.
        if (currentQty < Math.abs(itemCount) && itemCount < 0) {
            inputHandler.errorMessage("Jumlah barang tidak mencukupi, sisa barang digudang sebanyak " + currentQty);
            return;
        }
        category.get(categoryIndex).updateQty(itemIndex, currentQty + itemCount); // Perbarui jumlah stok item.
        log.add(new MessageLog(category.get(categoryIndex).getItem(itemIndex).getNameItem(),
                "Update Stok Item di Kategori " + category.get(categoryIndex).getNameCategory()
                        + " -> " + itemCount)); // Tambahkan pesan log.
    }

    // Memperbarui jumlah stok item dalam suatu kategori berdasarkan nama item.
    public void updateQtyItemByCategory(int categoryIndex, String itemName, int itemCount) {
        updateQtyItemByCategory(categoryIndex, getIndexItemByCategory(categoryIndex, itemName), itemCount);
    }

    // ==================================== Qty Section ========================================

    // Menampilkan pesan log yang berisi kegiatan pengguna.
    public void showLog () {
        if (log.size() > 0) {
            Text text = new Text("",
                    new String[]{"Timestamp", "Nama", "Keterangan"},
                    new char[]{'c', 'l', 'l'},
                    new int[]{19, 30, 100});

            text.line();
            text.printSubTitle();
            for (int indexLog = 0; indexLog < log.size(); indexLog++) {
                text.printBody(indexLog,
                        new String[]{log.get(indexLog).getCurrentTime(), log.get(indexLog).getItemName(), log.get(indexLog).getLogActivity()});
            }
            text.line();
        } else {
            System.out.println("Tidak ada log");
        }

    }
}
