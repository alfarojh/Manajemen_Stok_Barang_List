package controller;

import custom.Text;
import database.Item;
import input.InputHandler;

import java.util.ArrayList;

public class ItemController {
    private final ArrayList<Item> items;

    // Konstruktor untuk inisialisasi ArrayList items.
    public ItemController() {
        this.items = new ArrayList<>();
    }

    // Mengembalikan jumlah item yang ada.
    public int getItemsSize() {
        return items.size();
    }

    // Menghasilkan ID baru untuk item.
    public String getNewIDItem(String itemName) {
        if (items.size() == 0) return "1";
        else return String.valueOf(Integer.parseInt(items.get(items.size()-1).getIdItem()) + 1);
    }

    public ArrayList<Item> findItemsByMatchingLetter(String itemName) {
        ArrayList<Item> items1 = new ArrayList<>();
        // Iterasi melalui daftar item dan memeriksa kesamaan nama item.
        for (Item item : items) {
            if (item.getNameItem().contains(itemName)) {
                items1.add(item);
            }
        }
        return items1;
    }

    // Fungsi ini mendapatkan objek Item berdasarkan nama.
    public Item getItemByName(String itemName) {
        // Mendapatkan indeks item berdasarkan nama.
        int indexItem = getIndexItemByName(itemName);
        // Jika indeks negatif, mengembalikan nilai null.
        if (indexItem < 0) return null;
        // Mengembalikan objek Item dari daftar.
        return items.get(indexItem);
    }

    // Fungsi ini mendapatkan objek Item berdasarkan ID.
    public Item getItemByID(String itemID) {
        // Mendapatkan indeks item berdasarkan ID.
        int indexItem = getIndexItemByID(itemID);
        // Jika indeks negatif, mengembalikan nilai null.
        if (indexItem < 0) return null;
        // Mengembalikan objek Item dari daftar.
        return items.get(indexItem);
    }

    // Fungsi ini mengembalikan nama item berdasarkan input.
    public String getNameItemByInput (String categoryInput) {
        if (isItemNameExist(categoryInput)) return categoryInput;
        else if(isItemIDExist(categoryInput)) return getNameByIDItem(categoryInput);
        else return null;
    }

    // Mengembalikan ID kategori berdasarkan input.
    public String getIDItemByInput (String categoryInput) {
        if (isItemIDExist(categoryInput)) return categoryInput;
        else if(isItemNameExist(categoryInput)) return getIDByNameItem(categoryInput);
        else return null;
    }

    // Fungsi ini memeriksa apakah nama item sudah ada.
    public boolean isItemNameExist(String itemName) {
        // Iterasi melalui daftar item dan memeriksa kesamaan nama item.
        for (Item item : items) {
            if (item.getNameItem().equalsIgnoreCase(itemName)) {
                return true; // Jika ditemukan, mengembalikan true.
            }
        }
        return false; // Jika tidak ditemukan, mengembalikan false.
    }

    // Fungsi ini memeriksa apakah ID item sudah ada.
    public boolean isItemIDExist(String itemID) {
        // Iterasi melalui daftar item dan memeriksa kesamaan ID item.
        for (Item item : items) {
            if (item.getIdItem().equalsIgnoreCase(itemID)) {
                return true; // Jika ditemukan, mengembalikan true.
            }
        }
        return false; // Jika tidak ditemukan, mengembalikan false.
    }

    // Fungsi ini mendapatkan indeks item berdasarkan nama.
    public int getIndexItemByName(String itemName) {
        // Iterasi melalui daftar item dan mencari indeks dengan nama yang cocok.
        for (int indexItem = 0; indexItem < items.size(); indexItem++) {
            if(items.get(indexItem).getNameItem().equals(itemName)){
                return indexItem; // Mengembalikan indeks jika ditemukan.
            }
        }
        return -1; // Mengembalikan -1 jika tidak ditemukan.
    }

    // Fungsi ini mendapatkan indeks item berdasarkan ID.
    public int getIndexItemByID(String itemID) {
        // Iterasi melalui daftar item dan mencari indeks dengan ID yang cocok.
        for (int indexItem = 0; indexItem < items.size(); indexItem++) {
            if(items.get(indexItem).getIdItem().equalsIgnoreCase(itemID)){
                return indexItem; // Mengembalikan indeks jika ditemukan.
            }
        }
        return -1; // Mengembalikan -1 jika tidak ditemukan.
    }

    // Fungsi ini mendapatkan ID item berdasarkan nama.
    public String getIDByNameItem(String itemName) {
        // Mendapatkan indeks item berdasarkan nama.
        int indexItem = getIndexItemByName(itemName);
        // Mengembalikan ID item dari objek item yang ditemukan.
        return items.get(indexItem).getIdItem();
    }

    // Fungsi ini mendapatkan nama item berdasarkan ID.
    public String getNameByIDItem(String itemID) {
        // Mendapatkan indeks item berdasarkan ID.
        int indexItem = getIndexItemByID(itemID);
        // Mengembalikan nama item dari objek item yang ditemukan.
        return items.get(indexItem).getNameItem();
    }

    //================================================ CRUD ======================================================

    // Fungsi ini menambahkan item baru.
    public boolean addItem(String itemName) {
        // Jika nama item sudah ada, kembalikan false.
        if (isItemNameExist(itemName)){
            System.out.println("Item sudah ada.");
            return false;
        }
        // Tambahkan item baru ke dalam daftar.
        items.add(new Item(getNewIDItem(itemName), itemName));
        return true;
    }

    // Fungsi ini menghapus item berdasarkan ID.
    public boolean removeItemByID(String itemId) {
        // Jika ID item tidak ada, kembalikan false.
        if (!isItemIDExist(itemId)) return false;
        // Hapus item dari daftar berdasarkan ID.
        items.remove(getIndexItemByID(itemId));
        return true;
    }

    // Fungsi ini menghapus item berdasarkan nama.
    public boolean removeItemByName(String itemName) {
        // Jika nama item tidak ada, kembalikan false.
        if (!isItemNameExist(itemName)) return false;
        // Hapus item dari daftar berdasarkan nama.
        items.remove(getIndexItemByName(itemName));
        return true;
    }

    // Fungsi ini mengupdate nama item berdasarkan ID.
    public boolean updateNameItemByID(String itemId, String newNameItem) {
        // Jika ID item tidak ada, kembalikan false.
        if (!isItemIDExist(itemId) || isItemNameExist(newNameItem)) return false;
        // Update nama item berdasarkan ID.
        items.get(getIndexItemByID(itemId)).setNameItem(newNameItem);
        return true;
    }

    // Fungsi ini mengupdate nama item berdasarkan nama.
    public boolean updateNameItemByName(String itemName, String newNameItem) {
        // Jika nama item tidak ada, kembalikan false.
        if (!isItemNameExist(itemName) || isItemNameExist(newNameItem)) return false;
        // Update nama item berdasarkan nama.
        items.get(getIndexItemByName(itemName)).setNameItem(newNameItem);
        return true;
    }

    // Fungsi ini menampilkan daftar item.
    public void showItems() {
        // Jika daftar item kosong, keluar dari fungsi.
        if (items.size() == 0) return;
        // Buat objek untuk menampilkan daftar item dalam bentuk tabel.
        Text text = new Text("",
                new String[]{"ID", "Nama Item"},
                new char[]{'c', 'l'},
                new int[]{5, 50});
        text.printSubTitle(); // Tampilkan judul tabel.
        // Iterasi melalui daftar item dan tampilkan setiap entri.
        for (int index = 0; index < items.size(); index++) {
            text.printBody(index, new String[]{
                    String.format("%03d", Integer.parseInt(items.get(index).getIdItem())),
                    items.get(index).getNameItem()
            });
        }
        // Tampilkan opsi untuk keluar dari daftar item.
        text.printBody(items.size(), new String[]{ "0", "Keluar" });
        text.line(); // Tampilkan garis pemisah.
    }

    //================================================ CRUD ======================================================
}
