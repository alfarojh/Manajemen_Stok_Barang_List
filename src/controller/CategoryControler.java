package controller;

import custom.Text;
import database.Category;
import input.InputHandler;

import java.util.ArrayList;

public class CategoryControler {
    private final ArrayList<Category> categories;

    // Konstruktor untuk inisialisasi kelas CategoryController.
    public CategoryControler() {
        this.categories = new ArrayList<>();
    }

    // Mendapatkan jumlah kategori.
    public int getCategoriesSize() {
        return categories.size();
    }

    // Fungsi ini menghasilkan ID baru untuk kategori.
    public String getNewIDCategory() {
        // Jika daftar kategori kosong, mengembalikan ID "1".
        if (categories.size() == 0) return "1";
        else return String.valueOf(Integer.parseInt(categories.get(categories.size() - 1).getIdCategory()) + 1);
    }

    // Fungsi ini mendapatkan objek kategori berdasarkan nama kategori.
    public Category getCategoryByName(String categoryName) {
        // Mendapatkan indeks kategori berdasarkan nama kategori.
        int indexCategory = getIndexCategoryByName(categoryName);
        // Jika indeks negatif, mengembalikan nilai null.
        if (indexCategory < 0) return null;
        // Mengembalikan objek kategori dari daftar.
        return categories.get(indexCategory);
    }

    // Fungsi ini mendapatkan objek kategori berdasarkan ID kategori.
    public Category getCategoryByID(String categoryID) {
        // Mendapatkan indeks kategori berdasarkan ID kategori.
        int indexCategory = getIndexCategoryByID(categoryID);
        // Jika indeks negatif, mengembalikan nilai null.
        if (indexCategory < 0) return null;
        // Mengembalikan objek kategori dari daftar.
        return categories.get(indexCategory);
    }

    // Fungsi ini mengembalikan nama kategori berdasarkan input.
    public String getNameCategoryByInput(String categoryInput) {
        if (isCategoryNameExist(categoryInput)) {
            return categoryInput;
        } else if (isCategoryIDExist(categoryInput)) {
            return getNameByIDCategory(categoryInput);
        } else {
            return null;
        }
    }

    // Fungsi ini mengembalikan ID kategori berdasarkan input.
    public String getIDCategoryByInput(String categoryInput) {
        if (isCategoryIDExist(categoryInput)) {
            return categoryInput;
        } else if (isCategoryNameExist(categoryInput)) {
            return getIDByNameCategory(categoryInput);
        } else {
            return null;
        }
    }

    // Fungsi ini memeriksa apakah nama kategori sudah ada.
    public boolean isCategoryNameExist(String categoryName) {
        // Iterasi melalui daftar kategori dan memeriksa kesamaan nama kategori.
        for (Category category : categories) {
            if (category.getNameCategory().equalsIgnoreCase(categoryName)) {
                return true; // Jika ditemukan, mengembalikan true.
            }
        }
        return false; // Jika tidak ditemukan, mengembalikan false.
    }

    // Fungsi ini memeriksa apakah ID kategori sudah ada.
    public boolean isCategoryIDExist(String categoryID) {
        // Iterasi melalui daftar kategori dan memeriksa kesamaan ID kategori.
        for (Category category : categories) {
            if (category.getIdCategory().equalsIgnoreCase(categoryID)) {
                return true; // Jika ditemukan, mengembalikan true.
            }
        }
        return false; // Jika tidak ditemukan, mengembalikan false.
    }

    // Fungsi ini mendapatkan indeks kategori berdasarkan nama kategori.
    public int getIndexCategoryByName(String categoryName) {
        // Iterasi melalui daftar kategori dan mencari indeks dengan nama yang cocok.
        for (int indexCategory = 0; indexCategory < categories.size(); indexCategory++) {
            if(categories.get(indexCategory).getNameCategory().equals(categoryName)){
                return indexCategory; // Mengembalikan indeks jika ditemukan.
            }
        }
        return -1; // Mengembalikan -1 jika tidak ditemukan.
    }

    // Fungsi ini mendapatkan indeks kategori berdasarkan ID kategori.
    public int getIndexCategoryByID(String categoryID) {
        // Iterasi melalui daftar kategori dan mencari indeks dengan ID yang cocok.
        for (int indexCategory = 0; indexCategory < categories.size(); indexCategory++) {
            if(categories.get(indexCategory).getIdCategory().equalsIgnoreCase(categoryID)){
                return indexCategory; // Mengembalikan indeks jika ditemukan.
            }
        }
        return -1; // Mengembalikan -1 jika tidak ditemukan.
    }

    // Fungsi ini mendapatkan ID berdasarkan nama kategori.
    public String getIDByNameCategory(String categoryName) {
        // Mendapatkan indeks kategori berdasarkan nama kategori.
        int indexCategory = getIndexCategoryByName(categoryName);
        // Jika indeks negatif, mengembalikan nilai null.
        if (indexCategory < 0) return null;
        // Mengembalikan ID kategori dari objek kategori yang ditemukan.
        return categories.get(indexCategory).getIdCategory();
    }

    // Fungsi ini mendapatkan nama berdasarkan ID kategori.
    public String getNameByIDCategory(String categoryID) {
        // Mendapatkan indeks kategori berdasarkan ID kategori.
        int indexCategory = getIndexCategoryByName(categoryID);
        // Jika indeks negatif, mengembalikan nilai null.
        if (indexCategory < 0) return null;
        // Mengembalikan nama kategori dari objek kategori yang ditemukan.
        return categories.get(indexCategory).getNameCategory();
    }

    //================================================ CRUD ======================================================

    // Fungsi ini menambahkan kategori baru.
    public boolean addCategory(String categoryName) {
        // Jika nama kategori sudah ada, kembalikan false.
        if (isCategoryNameExist(categoryName)) {
            System.out.println("Kategori sudah ada.");
            return false;
        }
        // Tambahkan kategori baru ke dalam daftar.
        categories.add(new Category(getNewIDCategory(), categoryName));
        return true;
    }

    // Fungsi ini menghapus kategori berdasarkan ID.
    public boolean removeCategoryByID(String categoryID) {
        // Jika ID kategori tidak ada, kembalikan false.
        if (!isCategoryIDExist(categoryID)) return false;
        // Hapus kategori dari daftar berdasarkan ID.
        categories.remove(getIndexCategoryByID(categoryID));
        return true;
    }

    // Fungsi ini menghapus kategori berdasarkan nama.
    public boolean removeCategoryByName(String categoryName) {
        // Jika nama kategori tidak ada, kembalikan false.
        if (!isCategoryNameExist(categoryName)) return false;
        // Hapus kategori dari daftar berdasarkan nama.
        categories.remove(getIndexCategoryByName(categoryName));
        return true;
    }

    // Fungsi ini mengupdate nama kategori berdasarkan ID.
    public boolean updateNameCategoryByID(String categoryID, String newNameCategory) {
        // Jika ID kategori tidak ada, kembalikan false.
        if (!isCategoryIDExist(categoryID) || isCategoryNameExist(newNameCategory)) return false;
        // Update nama kategori berdasarkan ID.
        categories.get(getIndexCategoryByID(categoryID)).setNameCategory(newNameCategory);
        return true;
    }

    // Fungsi ini mengupdate nama kategori berdasarkan nama.
    public boolean updateNameCategoryByName(String categoryName, String newNameCategory) {
        // Jika nama kategori tidak ada, kembalikan false.
        if (!isCategoryNameExist(categoryName) || isCategoryNameExist(newNameCategory)) return false;
        // Update nama kategori berdasarkan nama.
        categories.get(getIndexCategoryByName(categoryName)).setNameCategory(newNameCategory);
        return true;
    }

    // Fungsi ini menampilkan daftar kategori.
    public void showCategories() {
        // Jika daftar kategori kosong, keluar dari fungsi.
        if (categories.size() == 0) return;
        // Buat objek untuk menampilkan daftar kategori dalam bentuk tabel.
        Text text = new Text("",
                new String[]{"ID", "Nama Kategori"},
                new char[]{'c', 'l'},
                new int[]{5, 50});
        text.printSubTitle(); // Tampilkan judul tabel.
        // Iterasi melalui daftar kategori dan tampilkan setiap entri.
        for (int index = 0; index < categories.size(); index++) {
            text.printBody(index, new String[]{
                    String.format("%03d", Integer.parseInt(categories.get(index).getIdCategory())),
                    categories.get(index).getNameCategory()
            });
        }
        // Tampilkan opsi untuk keluar dari daftar kategori.
        text.printBody(categories.size(), new String[]{ "0", "Keluar" });
        text.line(); // Tampilkan garis pemisah.
    }

    //================================================ CRUD ======================================================
}
