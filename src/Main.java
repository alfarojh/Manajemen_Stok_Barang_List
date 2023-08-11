import controller.CategoryControler;
import controller.ItemController;
import controller.TransactionCategoriesItemsController;
import controller.TransactionItemsQtyController;
import custom.DisplayPrint;
import input.InputHandler;

public class Main {
    private static final InputHandler inputHandler = new InputHandler();
    private static final CategoryControler categoryController = new CategoryControler();
    private static final ItemController itemController = new ItemController();
    private static final TransactionCategoriesItemsController transactionCategoriesItemsController = new TransactionCategoriesItemsController();
    private static final TransactionItemsQtyController transactionItemsQtyController = new TransactionItemsQtyController();
    private static final DisplayPrint displayPrint = new DisplayPrint();

    public static void main(String[] args) {
        while (true) {
            try {
                // Menampilkan menu dan mendapatkan pilihan dari pengguna
                int choice = inputHandler.getIntegerInput(displayPrint.displayMenu());
                newLine(2);
                switch (choice) {
                    case 0 -> throw new Exception(); // Jika pengguna memilih 0, lempar Exception untuk keluar dari program
                    case 1 -> {
                        while (true) {
                            choice = inputHandler.getIntegerInput(displayPrint.displayMenuCategory());
                            System.out.println("===========================================================================================\n");
                            try {
                                switch (choice) {
                                    case 0 -> throw new Exception(); // Jika pengguna memilih 0, kembali ke menu utama
                                    case 1 -> addCategory();
                                    case 2 -> removeCategory();
                                    case 3 -> updateNameCategory();
                                    case 4 -> showCategories();
                                    default -> inputHandler.errorMessage("Maaf, input diluar batas pilihan");
                                }
                            } catch (Exception e) {
                                System.out.println("Kembali ke menu utama");
                                newLine(2);
                                break;
                            }
                        }
                    }
                    case 2 -> {
                        while (true) {
                            choice = inputHandler.getIntegerInput(displayPrint.displayMenuItem());
                            System.out.println("===========================================================================================\n");
                            try {
                                switch (choice) {
                                    case 0 -> throw new Exception(); // Jika pengguna memilih 0, kembali ke menu utama
                                    case 1 -> addItem();
                                    case 2 -> removeItem();
                                    case 3 -> updateNameItem();
                                    case 4 -> searchItem();
                                    case 5 -> addTransactionItem();
                                    case 6 -> showItems();
                                    default -> inputHandler.errorMessage("Maaf, input diluar batas pilihan");
                                }
                            } catch (Exception e) {
                                System.out.println("Kembali ke menu utama");
                                newLine(2);
                                break;
                            }
                        }
                    }
                    case 3 -> {
                        while (true) {
                            choice = inputHandler.getIntegerInput(displayPrint.displayTransaction());
                            System.out.println("===========================================================================================\n");
                            try {
                                switch (choice) {
                                    case 0 -> throw new Exception(); // Jika pengguna memilih 0, kembali ke menu utama
                                    case 1 -> addTransactionItem();
                                    case 2 -> addItemToCategory();
                                    case 3 -> {
                                        transactionCategoriesItemsController.showTransaction();
                                        inputHandler.delayInput();
                                    }
                                    case 4 -> {
                                        transactionItemsQtyController.showTransaction();
                                        inputHandler.delayInput();
                                    }
                                    default -> inputHandler.errorMessage("Maaf, input diluar batas pilihan");
                                }
                            } catch (Exception e) {
                                System.out.println("Kembali ke menu utama");
                                newLine(2);
                                break;
                            }

                        }
                    }
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

    //========================================== Category Section ==========================================

    // Fungsi untuk menambah kategori baru
    private static void addCategory() {
        // Menampilkan instruksi untuk pengguna
        System.out.println("Ketik 0 atau 'keluar' untuk kembali ke menu utama.");

        // Meminta input nama kategori baru dari pengguna
        String nameCategory = inputHandler.getInputText("Masukkan nama kategori baru: ");

        // Memeriksa apakah pengguna ingin kembali ke menu utama
        if (nameCategory.equalsIgnoreCase("keluar") || nameCategory.equals("0")) {
            System.out.println("Kembali ke menu utama.");
            newLine();
            return;
        }

        // Menambahkan kategori baru dan memberikan pesan hasilnya
        if (categoryController.addCategory(nameCategory)) {
            System.out.println("Kategori berhasil ditambahkan.");
        } else {
            inputHandler.errorMessage("Kategori gagal ditambahkan.");
        }
        inputHandler.delayInput();
    }

    // Fungsi untuk menghapus kategori
    private static void removeCategory() {
        // Memeriksa apakah ada kategori yang tersedia
        if (categoryController.getCategoriesSize() == 0) {
            inputHandler.errorMessage("Kategori kosong, kembali ke menu utama.");
            newLine();
            return;
        }

        // Menampilkan daftar kategori dan meminta input dari pengguna
        categoryController.showCategories();
        String inputCategory = inputHandler.getInputText("Pilih kategori: ");

        // Memeriksa apakah pengguna ingin kembali ke menu utama
        if (inputCategory.equalsIgnoreCase("keluar") || inputCategory.equals("0")) {
            System.out.println("Kembali ke menu utama.");
            newLine();
            return;
        }

        if (categoryController.isCategoryIDExist(inputCategory)) {
            // Jika ID kategori sudah ada, peroleh nama kategori berdasarkan ID tersebut.
            String nameCategory = categoryController.getCategoryByID(inputCategory).getNameCategory();
            if (categoryController.removeCategoryByID(inputCategory)) {
                System.out.println("Kategori " + nameCategory + " berhasil dihapus."); // Tampilkan pesan sukses jika kategori berhasil dihapus.
            }
        } else if (categoryController.isCategoryNameExist(inputCategory)) {
            // Jika ID kategori tidak ditemukan, cek apakah nama kategori sudah ada.
            String nameCategory = categoryController.getCategoryByName(inputCategory).getNameCategory();
            if (categoryController.removeCategoryByName(inputCategory)) {
                System.out.println("Kategori " + nameCategory + " berhasil dihapus."); // Tampilkan pesan sukses jika kategori berhasil dihapus.
            }
        } else {
            // Jika kategori tidak ditemukan, tampilkan pesan kesalahan.
            inputHandler.errorMessage("Nama kategori tidak ditemukan, gagal menghapus kategori.");
        }
        inputHandler.delayInput();
    }

    // Fungsi untuk mengubah nama kategori
    private static void updateNameCategory() {
        // Memeriksa apakah ada kategori yang tersedia
        if (categoryController.getCategoriesSize() == 0) {
            inputHandler.errorMessage("Kategori kosong, kembali ke menu utama.");
            newLine();
            return;
        }

        // Menampilkan daftar kategori dan meminta input dari pengguna
        categoryController.showCategories();
        String inputCategory = inputHandler.getInputText("Pilih kategori: ");

        // Memeriksa apakah pengguna ingin kembali ke menu utama
        if (inputCategory.equalsIgnoreCase("keluar") || inputCategory.equals("0")) {
            System.out.println("Kembali ke menu utama.");
            newLine();
            return;
        }

        // Meminta input nama kategori baru dari pengguna
        String newNameCategory = inputHandler.getInputText("Masukkan nama kategori baru: ");

        if (categoryController.isCategoryIDExist(inputCategory)) {
            // Jika ID kategori sudah ada, peroleh nama kategori berdasarkan ID tersebut.
            String oldNameCategory = categoryController.getCategoryByID(inputCategory).getNameCategory();
            if (categoryController.updateNameCategoryByID(inputCategory, newNameCategory)) {
                System.out.println("Nama kategori berhasil diperbarui dari " + oldNameCategory + " menjadi " + newNameCategory + ".");
            }
        } else if (categoryController.isCategoryNameExist(inputCategory)) {
            // Jika ID kategori tidak ditemukan, cek apakah nama kategori sudah ada.
            String oldNameCategory = categoryController.getCategoryByName(inputCategory).getNameCategory();
            if (categoryController.updateNameCategoryByName(inputCategory, newNameCategory)) {
                System.out.println("Nama kategori berhasil diperbarui dari " + oldNameCategory + " menjadi " + newNameCategory + ".");
            }
        } else {
            // Jika kategori tidak ditemukan, tampilkan pesan kesalahan.
            inputHandler.errorMessage("Kategori tidak ditemukan, gagal mengupdate nama.");
        }
        inputHandler.delayInput();
    }

    // Fungsi ini digunakan untuk menampilkan daftar kategori jika ada.
    // Jika tidak ada, menanyakan kepada pengguna apakah ingin menambahkan kategori atau tidak.
    private static void showCategories() {
        if (categoryController.getCategoriesSize() == 0) {
            System.out.println("Kategori tidak ada, ingin menambahkan kategori?");
            newLine();
            addCategory(); // Memanggil fungsi untuk menambahkan kategori.
            return;
        }
        categoryController.showCategories(); // Menampilkan daftar kategori jika ada.
    }

    //========================================== Category Section ==========================================

    //============================================ Item Section ============================================

    // Fungsi untuk menambah item baru
    private static void addItem() {
        System.out.println("Ketik 0 atau 'keluar' untuk kembali ke menu utama.");
        String nameItem = inputHandler.getInputText("Masukkan nama item baru: ");

        // Memeriksa apakah pengguna ingin keluar atau kembali ke menu utama
        if (nameItem.equalsIgnoreCase("keluar") || nameItem.equals("0")) {
            System.out.println("Kembali ke menu utama.");
            newLine();
            return;
        }

        // Menambahkan item baru dengan memanggil itemController.addItem()
        if (itemController.addItem(nameItem)) {
            System.out.println("Item berhasil ditambahkan.");
        } else {
            inputHandler.errorMessage("Item gagal ditambahkan.");
        }
        inputHandler.delayInput();
    }

    // Fungsi untuk menghapus item
    private static void removeItem() {
        // Memeriksa apakah daftar item kosong
        if (itemController.getItemsSize() == 0) {
            inputHandler.errorMessage("Item kosong, kembali ke menu utama.");
            newLine();
            return;
        }

        // Menampilkan daftar item dan meminta input pengguna
        itemController.showItems();
        String inputItem = inputHandler.getInputText("Pilih item: ");

        // Memeriksa apakah pengguna ingin keluar atau kembali ke menu utama
        if (inputItem.equalsIgnoreCase("keluar") || inputItem.equals("0")) {
            System.out.println("Kembali ke menu utama.");
            newLine();
            return;
        }

        if (itemController.isItemIDExist(inputItem)) {
            // Jika ID item sudah ada, peroleh nama item berdasarkan ID tersebut.
            String itemName = itemController.getItemByID(inputItem).getNameItem();
            if (itemController.removeItemByID(inputItem)) {
                System.out.println("Item " + itemName + " berhasil dihapus."); // Tampilkan pesan sukses jika item berhasil dihapus.
            }
        } else if (itemController.isItemNameExist(inputItem)) {
            // Jika ID item tidak ditemukan, cek apakah nama item sudah ada.
            String itemName = itemController.getItemByName(inputItem).getNameItem();
            if (itemController.removeItemByName(inputItem)) {
                System.out.println("Item " + itemName + " berhasil dihapus."); // Tampilkan pesan sukses jika item berhasil dihapus.
            }
        } else {
            // Jika item tidak ditemukan, tampilkan pesan kesalahan.
            inputHandler.errorMessage("Nama item tidak ditemukan, gagal menghapus item.");
        }
        inputHandler.delayInput();
    }

    // Fungsi untuk mengubah nama item
    private static void updateNameItem() {
        // Memeriksa apakah daftar item kosong
        if (itemController.getItemsSize() == 0) {
            inputHandler.errorMessage("Item kosong, kembali ke menu utama.");
            newLine();
            return;
        }

        // Menampilkan daftar item dan meminta input pengguna
        itemController.showItems();
        String inputItem = inputHandler.getInputText("Pilih item: ");

        // Memeriksa apakah pengguna ingin keluar atau kembali ke menu utama
        if (inputItem.equalsIgnoreCase("keluar") || inputItem.equals("0")) {
            System.out.println("Kembali ke menu utama.");
            newLine();
            return;
        }

        // Meminta input untuk nama item baru dan melakukan pembaruan nama item
        String newNameItem = inputHandler.getInputText("Masukkan nama item baru: ");

        if (itemController.isItemIDExist(inputItem)) {
            // Jika ID item sudah ada, perbarui nama item berdasarkan ID.
            String oldNameItem = itemController.getItemByID(inputItem).getNameItem();
            if (itemController.updateNameItemByID(inputItem, newNameItem)) {
                System.out.println("Nama item berhasil diperbarui dari "
                        + oldNameItem + " menjadi " + newNameItem + ".");
            }
        } else if (itemController.isItemNameExist(inputItem)) {
            // Jika ID item tidak ditemukan, cek apakah nama item sudah ada.
            String oldNameItem = itemController.getItemByName(inputItem).getNameItem();
            if (itemController.updateNameItemByName(inputItem, newNameItem)) {
                System.out.println("Nama item berhasil diperbarui dari "
                        + oldNameItem + " menjadi " + newNameItem + ".");
            }
        } else {
            // Jika item tidak ditemukan, tampilkan pesan kesalahan.
            inputHandler.errorMessage("Item tidak ditemukan, gagal mengupdate nama item.");
        }
        inputHandler.delayInput();
    }

    // Fungsi ini digunakan untuk menampilkan daftar item jika ada.
    // Jika tidak ada, menanyakan kepada pengguna apakah ingin menambahkan item atau tidak.
    private static void showItems() {
        if (itemController.getItemsSize() == 0) {
            System.out.println("Item tidak ada, ingin menambahkan item?");
            newLine();
            addItem(); // Memanggil fungsi untuk menambahkan item.
            return;
        }
        itemController.showItems(); // Menampilkan daftar item jika ada.
        inputHandler.delayInput();
    }

    //============================================ Item Section ============================================

    //============================================ Transaction =============================================

    // Fungsi untuk menghubungkan item dengan kategori
    private static void addItemToCategory() {
        if (itemController.getItemsSize() == 0) {
            inputHandler.errorMessage("Item kosong, kembali ke menu utama.");
            newLine();
            return;
        } else if (categoryController.getCategoriesSize() == 0) {
            inputHandler.errorMessage("Kategori kosong, kembali ke menu utama.");
            newLine();
            return;
        }

        // Menampilkan daftar item dan meminta input pengguna untuk memilih item
        itemController.showItems();
        String inputItem = inputHandler.getInputText("Pilih item: ");

        // Memeriksa apakah pengguna ingin keluar atau kembali ke menu utama,
        // serta apakah item yang dipilih ada dalam daftar item
        if (inputItem.equalsIgnoreCase("keluar") || inputItem.equals("0")) {
            System.out.println("Kembali ke menu utama.");
            newLine();
            return;
        } else if (!itemController.isItemIDExist(inputItem) && !itemController.isItemNameExist(inputItem)) {
            inputHandler.errorMessage("Item tidak ditemukan, kembali ke menu utama.");
            newLine();
            return;
        }

        newLine();
        // Menampilkan daftar kategori dan meminta input pengguna untuk memilih kategori
        categoryController.showCategories();
        String inputCategory = inputHandler.getInputText("Pilih kategori: ");

        // Memeriksa apakah pengguna ingin keluar atau kembali ke menu utama,
        // serta apakah kategori yang dipilih ada dalam daftar kategori
        if (inputCategory.equalsIgnoreCase("keluar") || inputCategory.equals("0")) {
            System.out.println("Kembali ke menu utama.");
            newLine();
            return;
        } else if (!categoryController.isCategoryIDExist(inputCategory) && !categoryController.isCategoryNameExist(inputCategory)) {
            inputHandler.errorMessage("Kategori tidak ditemukan, kembali ke menu utama.");
            newLine();
            return;
        }

        // Mendapatkan nama kategori berdasarkan input kategori
        String nameCategory = categoryController.getNameCategoryByInput(inputCategory);
        // Mendapatkan nama item berdasarkan input item
        String nameItem = itemController.getNameItemByInput(inputItem);

        // Memeriksa apakah nama kategori dan nama item tidak null
        if (nameCategory != null && nameItem != null) {
            // Jika item berhasil ditambahkan ke kategori
            if (transactionCategoriesItemsController.addTransaction(
                    categoryController.getCategoryByName(inputCategory),
                    itemController.getItemByName(inputItem))) {
                System.out.println("Item " + itemController.getItemByName(inputItem).getNameItem()
                        + " berhasil dimasukkan ke dalam kategori "
                        + categoryController.getCategoryByName(inputCategory).getNameCategory());
            } else {
                // Jika item sudah ada dalam kategori
                inputHandler.errorMessage("Item " + itemController.getItemByName(inputItem).getNameItem()
                        + " sudah ditambahkan ke dalam kategori "
                        + categoryController.getCategoryByName(inputCategory).getNameCategory());
            }
        }


        inputHandler.delayInput();
    }

    // Fungsi untuk menambahkan log transaksi dari tambah kurang item
    private static void addTransactionItem() {
        if (itemController.getItemsSize() == 0) {
            inputHandler.errorMessage("Item kosong, kembali ke menu utama.");
            return;
        }

        // Menampilkan daftar item dan meminta input pengguna untuk memilih item
        itemController.showItems();
        String inputItem = inputHandler.getInputText("Pilih item: ");

        // Memeriksa apakah pengguna ingin keluar atau kembali ke menu utama,
        // serta apakah item yang dipilih ada dalam daftar item
        if (inputItem.equalsIgnoreCase("keluar") || inputItem.equals("0")) {
            System.out.println("Kembali ke menu utama.");
            return;
        } else if (!itemController.isItemIDExist(inputItem) && !itemController.isItemNameExist(inputItem)) {
            inputHandler.errorMessage("Item tidak ditemukan, kembali ke menu utama.");
            return;
        }

        // Meminta input jumlah transaksi
        int qty = inputHandler.getIntegerInputWithOperator("Masukkan jumlah (+/-): ");

        // Memproses transaksi item
        if (itemController.isItemIDExist(inputItem)) {
            if (transactionItemsQtyController.addTransaction(itemController.getItemByID(inputItem), qty)) {
                System.out.println("Transaksi item " + itemController.getItemByID(inputItem).getNameItem() + " berhasil.");
            } else {
                inputHandler.errorMessage("Gagal melakukan transaksi, sisa barang digudang "
                        + transactionItemsQtyController.getAmountQty(itemController.getItemByID(inputItem)));
            }
        } else if (itemController.isItemNameExist(inputItem)) {
            if (transactionItemsQtyController.addTransaction(itemController.getItemByName(inputItem), qty)) {
                System.out.println("Transaksi item " + itemController.getItemByName(inputItem).getNameItem() + " berhasil.");
            } else {
                inputHandler.errorMessage("Gagal melakukan transaksi, sisa barang digudang "
                        + transactionItemsQtyController.getAmountQty(itemController.getItemByName(inputItem)));
            }
        } else {
            inputHandler.errorMessage("Transaksi batal.");
        }
        inputHandler.delayInput();
    }

    // Fungsi untuk mencari rincian item dengan nama item
    private static void searchItem() {
        if (itemController.getItemsSize() == 0) {
            inputHandler.errorMessage("Item kosong, kembali ke menu utama.");
            return;
        }

        System.out.println("Ketik 0 atau 'keluar' untuk kembali ke menu utama.");
        // Meminta input nama item yang akan dicari
        String nameItem = inputHandler.getInputText("Masukkan nama item yang ingin dicari: ");

        // Memeriksa apakah pengguna ingin keluar atau kembali ke menu utama,
        // serta apakah item yang dicari ada dalam daftar item
        if (nameItem.equalsIgnoreCase("keluar") || nameItem.equals("0")) {
            System.out.println("Kembali ke menu utama.");
            return;
        } else if (!itemController.isItemNameExist(nameItem)) {
            inputHandler.errorMessage("Item tidak ditemukan");
            return;
        }

        // Menampilkan informasi tentang item yang dicari
        System.out.println("Nama item: " + nameItem);
        System.out.println("Termasuk kategori: ");
        transactionCategoriesItemsController.getCategoriesByItem(itemController.getItemByName(nameItem));
        System.out.println("Stok digudang: " + transactionItemsQtyController.getAmountQty(itemController.getItemByName(nameItem)));
        inputHandler.delayInput();
    }

    //============================================ Transaction =============================================
}