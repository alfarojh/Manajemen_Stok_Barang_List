import input.InputHandler;
import warehouse.WarehouseController;

public class Main {
    private static final WarehouseController warehouseController = new WarehouseController();
    private static final InputHandler inputHandler = new InputHandler();

    public static void main(String[] args) {
        while (true) {
            try {
                // Menampilkan menu dan mendapatkan pilihan dari pengguna
                int choice = inputHandler.getIntegerInput(displayMenu());
                System.out.println("===========================================================================================\n");
                switch (choice) {
                    case 0 -> throw new Exception(); // Jika pengguna memilih 0, lempar Exception untuk keluar dari program
                    case 1 -> addCategory();
                    case 2 -> removeCategory();
                    case 3 -> updateNameCategory();
                    case 4 -> addItem();
                    case 5 -> removeItem();
                    case 6 -> updateNameItem();
                    case 7 -> updateStockItem();
                    case 8 -> searchItem();
                    case 9 -> displayAllItems();
                    case 10 -> warehouseController.showLog();
                    default -> inputHandler.errorMessage("Maaf, input diluar batas pilihan");
                }
            } catch (Exception e) {
                // Menangkap dan menampilkan pesan jika terjadi exception (kesalahan)
//                System.out.println("Keluar dari program");
                e.printStackTrace();
                break; // Keluar dari perulangan untuk mengakhiri program
            }
            newLine(5);
        }
        inputHandler.close();
    }
    // Menampilkan menu utama
    private static String displayMenu () {
        return """
                |===================================|
                |              Menu                 |
                |===================================|
                |  1.  Tambah Kategori              |
                |  2.  Hapus Kategori               |
                |  3.  Update Nama Kategori         |
                |  4.  Tambah Barang                |
                |  5.  Hapus Barang                 |
                |  6.  Update Nama Barang           |
                |  7.  Update Stok Barang           |
                |  8.  Cari Barang                  |
                |  9.  Tampilkan Stok Gudang        |
                |  10. Tampilkan Log                |
                |  0.  Keluar                       |
                |===================================|
                Silahkan masukkan pilihan:\s""";
    }

    //========================================= warehouse.Category Section =============================================

    // Fungsi untuk menambahkan kategori baru.
    private static void addCategory() {
        String nameCategory = inputHandler.getInputText("Masukkan nama kategori: ");

        // Periksa apakah kategori sudah ada sebelumnya.
        while (warehouseController.isCategoryExist(nameCategory)) {
            inputHandler.errorMessage("Maaf, kategori sudah ada.\n" +
                    "Ketik 'keluar' jika ingin kembali ke menu utama");
            nameCategory = inputHandler.getInputText("Masukkan nama kategori: ");
            if (nameCategory.equalsIgnoreCase("keluar")) { break; }
        }

        // Jika pengguna memilih untuk keluar, kembali ke menu utama.
        if (nameCategory.equalsIgnoreCase("keluar")) { return; }

        // Tambahkan kategori baru ke dalam sistem gudang.
        warehouseController.addCategory(nameCategory);
        newLine();
    }

    // Fungsi untuk menghapus kategori.
    private static void removeCategory() {
        if (warehouseController.isCategoriesEmpty()) {
            System.out.println("Kategori tidak ditemukan!");
            return;
        }

        // Tampilkan daftar kategori.
        warehouseController.showCategories();
        String inputCategory = inputHandler.getInputText("Masukkan kategori yang ingin dihapus: ");

        try {
            // Coba untuk menghapus kategori berdasarkan indeks yang diinput.
            if (!warehouseController.isCategoryExist(Integer.parseInt(inputCategory))) {
                inputHandler.errorMessage("Kategori tidak ditemukan, gagal menghapus nama kategori.");
                return;
            }
            warehouseController.removeCategory(Integer.parseInt(inputCategory) - 1);
        } catch (Exception e) {
            // Jika input bukan indeks, coba menghapus kategori berdasarkan nama.
            if (!warehouseController.isCategoryExist(inputCategory)) {
                inputHandler.errorMessage("Kategori tidak ditemukan, gagal menghapus nama kategori.");
                return;
            }
            warehouseController.removeCategory(inputCategory);
        }
        newLine();
    }

    // Fungsi untuk mengupdate nama kategori.
    private static void updateNameCategory() {
        if (warehouseController.isCategoriesEmpty()) {
            System.out.println("Kategori tidak ditemukan!");
            return;
        }

        // Tampilkan daftar kategori.
        warehouseController.showCategories();
        String inputCategory = inputHandler.getInputText("Masukkan kategori yang ingin diupdate: ");

        try {
            // Coba untuk mengupdate nama kategori berdasarkan indeks yang diinput.
            if (!warehouseController.isCategoryExist(Integer.parseInt(inputCategory))) {
                inputHandler.errorMessage("Kategori tidak ditemukan, gagal mengupdate nama kategori.");
                return;
            }
        } catch (Exception e) {
            // Jika input bukan indeks, coba mengupdate nama kategori berdasarkan nama.
            if (!warehouseController.isCategoryExist(inputCategory)) {
                inputHandler.errorMessage("Kategori tidak ditemukan, gagal mengupdate nama kategori.");
                return;
            }
        }

        // Meminta input nama kategori baru dan melakukan update.
        String newNameCategory = inputHandler.getInputText("Masukkan nama kategori baru: ");
        try {
            warehouseController.updateNameCategory(Integer.parseInt(inputCategory) - 1, newNameCategory);
        } catch (Exception e) {
            warehouseController.updateNameCategory(inputCategory, newNameCategory);
        }
        newLine();
    }

    //========================================= warehouse.Category Section =============================================

    //=========================================== Item Section ===============================================
    // Fungsi untuk menambahkan item ke dalam kategori.
    private static void addItem() {
        String choice;

        // Jika tidak ada kategori, tambahkan kategori baru terlebih dahulu.
        if (warehouseController.isCategoriesEmpty()) {
            System.out.println("Kategori tidak ditemukan!");
            System.out.println("Tambahkan kategori baru.");
            addCategory();
            choice = "1";
        } else {
            // Tampilkan daftar kategori.
            warehouseController.showCategories();
            System.out.println("0. Keluar");
            choice = inputHandler.getInputText("Pilih kategori: ");

            // Jika pengguna memilih untuk keluar, kembali ke menu utama.
            if (choice.equalsIgnoreCase("keluar") || choice.equals("0")) { return; }
        }

        try {
            // Coba untuk menambahkan item berdasarkan pilihan kategori.
            if (!warehouseController.isCategoryExist(Integer.parseInt(choice) - 1)) {
                inputHandler.errorMessage("Kategori tidak ditemukan.");
                return;
            }
            addItemByCategory(Integer.parseInt(choice) - 1);
        } catch (Exception e) {
            // Jika input bukan indeks, coba menambahkan item berdasarkan nama kategori.
            if (!warehouseController.isCategoryExist(choice)) {
                inputHandler.errorMessage("Kategori tidak ditemukan.");
                return;
            }
            addItemByCategory(choice);
        }
    }

    // Fungsi untuk menambahkan item ke dalam kategori berdasarkan indeks kategori.
    private static void addItemByCategory(int indexCategory) {
        // Tampilkan daftar item dalam kategori.
        warehouseController.showItemsByCategories(indexCategory);
        newLine();

        String nameItem = inputHandler.getInputText("Masukkan nama item: ");

        // Periksa apakah item sudah ada dalam kategori sebelumnya.
        while (warehouseController.isItemExistByCategory(indexCategory, nameItem)) {
            inputHandler.errorMessage("Maaf, Item sudah ada.\n" +
                    "Ketik 'keluar' jika ingin kembali ke menu utama");
            nameItem = inputHandler.getInputText("Masukkan nama item: ");
            if (nameItem.equalsIgnoreCase("keluar")) { break; }
        }

        // Jika pengguna memilih untuk keluar, kembali ke menu utama.
        if (nameItem.equalsIgnoreCase("keluar")) { return; }

        // Minta input jumlah item dan tambahkan item baru ke dalam sistem gudang.
        String qtyItem = String.valueOf(inputHandler.getIntegerInput("Masukkan jumlah item: "));
        warehouseController.addItemByCategory(indexCategory, nameItem, Integer.parseInt(qtyItem));
        newLine();

        // Tampilkan ulang daftar item dalam kategori dan tunda input pengguna.
        warehouseController.showItemsByCategories(indexCategory);
        inputHandler.delayInput();
    }

    // Fungsi untuk menambahkan item ke dalam kategori berdasarkan nama kategori.
    private static void addItemByCategory(String nameCategory) {
        addItemByCategory(warehouseController.getIndexCategory(nameCategory));
    }

    // Fungsi untuk menghapus item dari kategori.
    private static void removeItem() {
        if (warehouseController.isCategoriesEmpty()) {
            inputHandler.errorMessage("Kategori tidak ditemukan, kembali ke menu utama");
            return;
        }

        // Tampilkan daftar kategori.
        warehouseController.showCategories();
        System.out.println("0. Keluar");
        String choice = inputHandler.getInputText("Pilih kategori: ");

        // Jika pengguna memilih untuk keluar, kembali ke menu utama.
        if (choice.equalsIgnoreCase("keluar") || choice.equals("0")) { return; }

        try {
            // Coba untuk menghapus item berdasarkan pilihan kategori.
            if (!warehouseController.isCategoryExist(Integer.parseInt(choice) - 1)) {
                inputHandler.errorMessage("Kategori tidak ditemukan, kembali ke menu utama.");
                return;
            }
            removeItemByCategory(Integer.parseInt(choice) - 1);
        } catch (Exception e) {
            // Jika input bukan indeks, coba menghapus item berdasarkan nama kategori.
            if (!warehouseController.isCategoryExist(choice)) {
                inputHandler.errorMessage("Kategori tidak ditemukan, kembali ke menu utama.");
                return;
            }
            removeItemByCategory(choice);
        }
    }

    // Fungsi untuk menghapus item dari kategori berdasarkan indeks kategori.
    private static void removeItemByCategory(int indexCategory) {
        // Tampilkan daftar item dalam kategori.
        warehouseController.showItemsByCategories(indexCategory);
        System.out.println("0. Keluar");
        String choice = inputHandler.getInputText("Pilih item yang ingin dihapus: ");

        // Jika pengguna memilih untuk keluar, kembali ke menu utama.
        if (choice.equalsIgnoreCase("keluar") || choice.equals("0")) { return; }

        try {
            // Coba untuk menghapus item berdasarkan pilihan indeks item.
            warehouseController.removeItemByCategory(indexCategory, Integer.parseInt(choice) - 1);
        } catch (Exception e) {
            // Jika input bukan indeks, coba menghapus item berdasarkan nama item.
            warehouseController.removeItemByCategory(indexCategory, choice);
        }
    }

    // Fungsi untuk menghapus item dari kategori berdasarkan nama kategori.
    private static void removeItemByCategory(String nameCategory) {
        removeItemByCategory(warehouseController.getIndexCategory(nameCategory));
    }

    // Fungsi untuk mengupdate nama item.
    private static void updateNameItem() {
        if (warehouseController.isCategoriesEmpty()) {
            inputHandler.errorMessage("Kategori tidak ditemukan, kembali ke menu utama");
            return;
        }

        // Tampilkan daftar kategori.
        warehouseController.showCategories();
        System.out.println("0. Keluar");
        String choice = inputHandler.getInputText("Pilih kategori: ");

        // Jika pengguna memilih untuk keluar, kembali ke menu utama.
        if (choice.equalsIgnoreCase("keluar") || choice.equals("0")) { return; }
        newLine();

        try {
            // Coba untuk mengupdate nama item berdasarkan pilihan kategori.
            if (!warehouseController.isCategoryExist(Integer.parseInt(choice) - 1)) {
                inputHandler.errorMessage("Kategori tidak ditemukan, kembali ke menu utama.");
                return;
            }
            updateNameItemByCategory(Integer.parseInt(choice) - 1);
        } catch (Exception e) {
            // Jika input bukan indeks, coba mengupdate nama item berdasarkan nama kategori.
            if (!warehouseController.isCategoryExist(choice)) {
                inputHandler.errorMessage("Kategori tidak ditemukan, kembali ke menu utama.");
                return;
            }
            updateNameItemByCategory(choice);
        }
    }

    // Fungsi untuk mengupdate nama item dalam kategori berdasarkan indeks kategori.
    private static void updateNameItemByCategory(int indexCategory) {
        // Tampilkan daftar item dalam kategori.
        warehouseController.showItemsByCategories(indexCategory);
        System.out.println("0. Keluar");
        String choice = inputHandler.getInputText("Pilih item yang ingin namanya diupdate: ");

        // Jika pengguna memilih untuk keluar, kembali ke menu utama.
        if (choice.equalsIgnoreCase("keluar") || choice.equals("0")) { return; }

        try {
            // Coba untuk mengupdate nama item berdasarkan pilihan indeks item.
            if (!warehouseController.isItemExistByCategory(indexCategory, Integer.parseInt(choice) - 1)) {
                inputHandler.errorMessage("Item tidak ditemukan, gagal mengupdate nama.");
                return;
            }

        } catch (Exception e) {
            // Jika input bukan indeks, coba mengupdate nama item berdasarkan nama item.
            if (!warehouseController.isItemExistByCategory(indexCategory, choice)) {
                inputHandler.errorMessage("Item tidak ditemukan, gagal mengupdate nama.");
                return;
            }
        }

        // Minta input nama item baru dan lakukan update.
        String newName = inputHandler.getInputText("Masukkan nama item baru: ");

        try {
            warehouseController.updateNameItemByCategory(indexCategory, Integer.parseInt(choice) - 1, newName);
        } catch (Exception e) {
            warehouseController.updateNameItemByCategory(indexCategory, choice, newName);
        }
    }

    // Fungsi untuk mengupdate nama item dalam kategori berdasarkan nama kategori.
    private static void updateNameItemByCategory(String nameCategory) {
        updateNameItemByCategory(warehouseController.getIndexCategory(nameCategory));
    }

    // Fungsi untuk mengupdate stok item.
    private static void updateStockItem() {
        if (warehouseController.isCategoriesEmpty()) {
            inputHandler.errorMessage("Kategori tidak ditemukan, kembali ke menu utama");
            return;
        }

        // Tampilkan daftar kategori.
        warehouseController.showCategories();
        System.out.println("0. Keluar");
        String choice = inputHandler.getInputText("Pilih kategori: ");

        // Jika pengguna memilih untuk keluar, kembali ke menu utama.
        if (choice.equalsIgnoreCase("keluar") || choice.equals("0")) { return; }
        newLine();

        try {
            // Coba untuk mengupdate stok item berdasarkan pilihan kategori.
            if (!warehouseController.isCategoryExist(Integer.parseInt(choice) - 1)) {
                inputHandler.errorMessage("Kategori tidak ditemukan, kembali ke menu utama.");
                return;
            }
            updateStockItemByCategory(Integer.parseInt(choice) - 1);
        } catch (Exception e) {
            // Jika input bukan indeks, coba mengupdate stok item berdasarkan nama kategori.
            if (!warehouseController.isCategoryExist(choice)) {
                inputHandler.errorMessage("Kategori tidak ditemukan, kembali ke menu utama.");
                return;
            }
            updateStockItemByCategory(choice);
        }
    }

    // Fungsi untuk mengupdate stok item dalam kategori berdasarkan indeks kategori.
    private static void updateStockItemByCategory(int categoryIndex) {
        // Tampilkan daftar item dalam kategori.
        warehouseController.showItemsByCategories(categoryIndex);
        System.out.println("0. Keluar");
        String choice = inputHandler.getInputText("Pilih item yang ingin stocknya diupdate: ");

        // Jika pengguna memilih untuk keluar, kembali ke menu utama.
        if (choice.equalsIgnoreCase("keluar") || choice.equals("0")) { return; }

        try {
            // Coba untuk mengupdate stok item berdasarkan pilihan indeks item.
            if (!warehouseController.isItemExistByCategory(categoryIndex, Integer.parseInt(choice) - 1)) {
                inputHandler.errorMessage("Item tidak ditemukan, gagal mengupdate stock.");
                return;
            }

        } catch (Exception e) {
            // Jika input bukan indeks, coba mengupdate stok item berdasarkan nama item.
            if (!warehouseController.isItemExistByCategory(categoryIndex, choice)) {
                inputHandler.errorMessage("Item tidak ditemukan, gagal mengupdate stock.");
                return;
            }
        }

        // Minta input jumlah item yang ingin diupdate dan lakukan update.
        int countItem = inputHandler.getIntegerInputWithOperator("Masukkan jumlah item (+/-): ");

        try {
            warehouseController.updateQtyItemByCategory(categoryIndex, Integer.parseInt(choice) - 1, countItem);
        } catch (Exception e) {
            warehouseController.updateQtyItemByCategory(categoryIndex, choice, countItem);
        }
    }

    // Fungsi untuk mengupdate stok item dalam kategori berdasarkan nama kategori.
    private static void updateStockItemByCategory(String categoryName) {
        updateStockItemByCategory(warehouseController.getIndexCategory(categoryName));
    }

    //=========================================== Item Section ===============================================
    // Fungsi untuk mencari item berdasarkan nama.
    private static void searchItem() {
        // Minta input nama item yang ingin dicari.
        String nameItem = inputHandler.getInputText("Masukkan nama yang ingin dicari: ");
        // Panggil fungsi searchItem dari warehouseController.
        warehouseController.searchItem(nameItem);
    }

    // Fungsi untuk menampilkan semua item.
    private static void displayAllItems() {
        // Jika kategori kosong, minta pengguna untuk menambahkan kategori baru.
        if (warehouseController.isCategoriesEmpty()) {
            System.out.println("Kategori kosong, tolong masukkan kategori baru");
            addCategory();
            return;
        }

        // Tampilkan semua item dalam setiap kategori.
        for (int indexCategory = 0; indexCategory < warehouseController.getSizeCategory(); indexCategory++) {
            warehouseController.showItemsByCategories(indexCategory);
        }
    }

    // Fungsi untuk menampilkan baris baru sebanyak yang ditentukan.
    private static void newLine(int... count) {
        // Jika parameter count diberikan, cetak baris baru sebanyak count[0] kali.
        if (count.length > 0) {
            for (int i = 0; i < count[0]; i++) {
                System.out.println();
            }
        } else {
            // Jika tidak ada parameter count, cetak satu baris baru.
            System.out.println();
        }
    }

}