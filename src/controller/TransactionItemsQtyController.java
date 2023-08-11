package controller;

import custom.Text;
import database.Item;
import database.TransactionItemsQty;

import java.util.ArrayList;

public class TransactionItemsQtyController {
    private final ArrayList<TransactionItemsQty> transactionItemsQties;

    // Konstruktor untuk inisialisasi ArrayList transactionItemsQties.
    public TransactionItemsQtyController() {
        this.transactionItemsQties = new ArrayList<>();
    }

    // Fungsi ini menghitung total jumlah kuantitas transaksi untuk suatu item.
    public int getAmountQty(Item item) {
        int sum = 0;
        // Iterasi melalui daftar transaksi kuantitas item dan menghitung total jumlah kuantitas.
        for (TransactionItemsQty transactionItemsQty : transactionItemsQties) {
            if (transactionItemsQty.getItem().equals(item)) {
                sum += transactionItemsQty.getQty();
            }
        }
        return sum;
    }

    // Fungsi ini memeriksa apakah stok item tidak mencukupi untuk transaksi.
    private boolean isInsufficient(Item item, int qty) {
        // Mengembalikan true jika stok item tidak mencukupi untuk transaksi dengan kuantitas negatif.
        return getAmountQty(item) < Math.abs(qty) && qty < 0;
    }

    // Fungsi ini menambahkan transaksi kuantitas item ke dalam daftar.
    public boolean addTransaction(Item item, int qty) {
        // Jika stok tidak mencukupi, kembalikan false.
        if (isInsufficient(item, qty)) return false;
        // Tambahkan transaksi kuantitas baru ke dalam daftar.
        transactionItemsQties.add(new TransactionItemsQty(item, qty));
        return true;
    }

    // Fungsi ini menampilkan seluruh transaksi kuantitas item.
    public void showTransaction() {
        // Jika daftar transaksi kuantitas item kosong, tampilkan pesan.
        if (transactionItemsQties.size() == 0) {
            System.out.println("Belum ada transaksi.");
            return;
        }
        // Buat objek untuk menampilkan daftar transaksi kuantitas item dalam bentuk tabel.
        Text text = new Text("",
                new String[]{"Waktu", "Item", "Jumlah"},
                new char[]{'c', 'l', 'c'},
                new int[]{19, 30, 10});
        text.printSubTitle(); // Tampilkan judul tabel.
        // Iterasi melalui daftar transaksi kuantitas item dan tampilkan setiap entri.
        for (int index = 0; index < transactionItemsQties.size(); index++) {
            String qty = String.valueOf(transactionItemsQties.get(index).getQty());
            if (transactionItemsQties.get(index).getQty() > 0){
                qty = "+" + transactionItemsQties.get(index).getQty();
            }
            text.printBody(index, new String[]{
                    transactionItemsQties.get(index).getCurrentTime(),
                    transactionItemsQties.get(index).getItem().getNameItem(), qty
            });
        }
        text.line(); // Tampilkan garis pemisah.
    }

}
