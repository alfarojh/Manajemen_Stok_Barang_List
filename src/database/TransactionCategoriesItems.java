package database;

public class TransactionCategoriesItems {
    private final Category category;
    private final Item item;

    // Konstruktor untuk kelas TransactionCategoriesItems.
    public TransactionCategoriesItems(Category category, Item item) {
        this.category = category;
        this.item = item;
    }

    // Fungsi untuk mendapatkan objek kategori dalam transaksi.
    public Category getCategory() {
        return category;
    }

    // Fungsi untuk mendapatkan objek item dalam transaksi.
    public Item getItem() {
        return item;
    }
}
