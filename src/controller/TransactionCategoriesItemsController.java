package controller;

import custom.Text;
import database.Category;
import database.Item;
import database.TransactionCategoriesItems;

import java.util.ArrayList;

public class TransactionCategoriesItemsController {
    private final ArrayList<TransactionCategoriesItems> transactionCategoriesItems;

    // Konstruktor untuk inisialisasi ArrayList transactionCategoriesItems.
    public TransactionCategoriesItemsController() {
        this.transactionCategoriesItems = new ArrayList<>();
    }

    // Fungsi ini memeriksa apakah kategori dan item sudah ada dalam relasi.
    private boolean isDuplicate(Category category, Item item) {
        // Iterasi melalui daftar relasi transaksi kategori-item.
        for (TransactionCategoriesItems transactionCategoriesItem : transactionCategoriesItems) {
            // Memeriksa kesamaan kategori dan item dalam relasi.
            if (transactionCategoriesItem.getCategory().equals(category) &&
                    transactionCategoriesItem.getItem().equals(item)) {
                return true; // Jika ditemukan relasi yang sama, mengembalikan true.
            }
        }
        return false; // Jika tidak ditemukan relasi yang sama, mengembalikan false.
    }

    // Fungsi ini menambahkan relasi antara kategori dan item ke dalam transaksi.
    public boolean addTransaction(Category category, Item item) {
        // Jika relasi sudah ada, kembalikan false.
        if (isDuplicate(category, item)) return false;
        // Tambahkan relasi baru ke dalam daftar transaksi.
        transactionCategoriesItems.add(new TransactionCategoriesItems(category, item));
        return true;
    }

    // Fungsi ini menampilkan seluruh transaksi.
    public void showTransaction() {
        // Jika daftar transaksi kosong, tampilkan pesan.
        if (transactionCategoriesItems.size() == 0) {
            System.out.println("Belum ada relasi.");
            return;
        }
        // Buat objek untuk menampilkan daftar transaksi dalam bentuk tabel.
        Text text = new Text("",
                new String[]{"Kategori", "Item"},
                new char[]{'l', 'l'},
                new int[]{20, 20});
        text.printSubTitle(); // Tampilkan judul tabel.
        // Iterasi melalui daftar transaksi dan tampilkan setiap entri.
        for (int index = 0; index < transactionCategoriesItems.size(); index++) {
            text.printBody(index, new String[]{
                    transactionCategoriesItems.get(index).getCategory().getNameCategory(),
                    transactionCategoriesItems.get(index).getItem().getNameItem()
            });
        }
        text.line(); // Tampilkan garis pemisah.
    }

    // Fungsi ini menampilkan kategori-kategori yang terkait dengan suatu item.
    public void getCategoriesByItem(Item item) {
        // Iterasi melalui daftar transaksi dan tampilkan kategori yang terkait dengan item.
        boolean notExist = true;
        for (int index = 0; index < transactionCategoriesItems.size(); index++) {
            if (transactionCategoriesItems.get(index).getItem().equals(item)) {
                System.out.println((index + 1) + ". " + transactionCategoriesItems.get(index).getCategory().getNameCategory());
                notExist = false;
            }
        }
        if (notExist) {
            System.out.println("Tidak ada kategori.");
        }
    }

}
