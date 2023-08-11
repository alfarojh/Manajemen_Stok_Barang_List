package database;

public class Category {
    private final String idCategory;
    private String nameCategory;

    // Konstruktor untuk kelas Category.
    public Category(String categoryId, String categoryName) {
        this.idCategory = categoryId;
        this.nameCategory = categoryName;
    }

    // Fungsi untuk mendapatkan nama kategori.
    public String getNameCategory() {
        return nameCategory;
    }

    // Fungsi untuk mendapatkan id kategori.
    public String getIdCategory() {
        return idCategory;
    }

    // Fungsi untuk mengubah nama kategori.
    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}