import controller.CategoryControler;
import controller.ItemController;
import controller.TransactionCategoriesItemsController;
import controller.TransactionItemsQtyController;
import input.InputHandler;

public class Main {
    private static final InputHandler inputHandler = new InputHandler();
    private static final CategoryControler categoryController = new CategoryControler();
    private static final ItemController itemController = new ItemController();
    private static final TransactionCategoriesItemsController transactionCategoriesItemsController = new TransactionCategoriesItemsController();
    private static final TransactionItemsQtyController transactionItemsQtyController = new TransactionItemsQtyController();

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
                    case 7 -> addItemToCategory();
                    case 8 -> transactionCategoriesItemsController.showTransaction();
//                    case 9 -> displayAllItems();
//                    case 10 -> warehouseController.showLog();
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
                |=======================================|
                |              Menu                     |
                |=======================================|
                |  1.  Tambah Kategori                  |
                |  2.  Hapus Kategori                   |
                |  3.  Update Nama Kategori             |
                |  4.  Tambah Barang                    |
                |  5.  Hapus Barang                     |
                |  6.  Update Nama Barang               |
                |  7.  Masukkan Barang ke Kategori      |
                |  8.  Tampilkan Relasi Barang Kategori |
                |  0.  Keluar                           |
                |=======================================|
                Silahkan masukkan pilihan:\s""";
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

    private static void addCategory() {
        String nameCategory = inputHandler.getInputText("Masukkan nama kategori baru: ");
        if (categoryController.addCategory(nameCategory)){
            System.out.println("Kategori berhasil ditambahkan.");
        } else {
            inputHandler.errorMessage("Kategori gagal ditambahkan.");
        }
        inputHandler.delayInput();
    }

    private static void removeCategory() {
        if (categoryController.getCategoriesSize() == 0) {
            inputHandler.errorMessage("Kategori kosong, kembali ke menu utama.");
            return;
        }

        categoryController.showCategories();
        String inputCategory = inputHandler.getInputText("Pilih kategori: ");

        if (categoryController.isCategoryIDExist(inputCategory)) {
            String nameCategory = categoryController.getCategoryByID(inputCategory).getNameCategory();
            if (categoryController.removeCategoryByID(inputCategory)) {
                System.out.println("Kategori " + nameCategory + " berhasil dihapus.");
            } else {
                inputHandler.errorMessage("Kategori ID tidak ditemukan, gagal menghapus.");
            }
        } else {
            String nameCategory = categoryController.getCategoryByName(inputCategory).getNameCategory();
            if (categoryController.removeCategoryByName(inputCategory)) {
                System.out.println("Kategori " + nameCategory + " berhasil dihapus.");
            } else {
                inputHandler.errorMessage("Nama kategori tidak ditemukan, gagal menghapus.");
            }
        }
        inputHandler.delayInput();
    }

    private static void updateNameCategory() {
        if (categoryController.getCategoriesSize() == 0) {
            inputHandler.errorMessage("Kategori kosong, kembali ke menu utama.");
            return;
        }

        categoryController.showCategories();
        String inputCategory = inputHandler.getInputText("Pilih kategori: ");

        if (!categoryController.isCategoryIDExist(inputCategory) && !categoryController.isCategoryNameExist(inputCategory)) {
            inputHandler.errorMessage("Kategori tidak ditemukan, kembali ke menu utama.");
            return;
        }

        String newNameCategory = inputHandler.getInputText("Masukkan nama kategori baru: ");

        if (categoryController.isCategoryIDExist(inputCategory)) {
            String oldNameCategory = categoryController.getCategoryByID(inputCategory).getNameCategory();
            if (categoryController.updateNameCategoryByID(inputCategory, newNameCategory)) {
                System.out.println("Nama kategori berhasil diupdate dari "
                        + oldNameCategory + " menjadi " + newNameCategory + ".");
            } else {
                inputHandler.errorMessage("Kategori ID tidak ditemukan, gagal menghapus.");
            }
        } else {
            String oldNameCategory = categoryController.getCategoryByName(inputCategory).getNameCategory();
            if (categoryController.updateNameCategoryByName(inputCategory, newNameCategory)) {
                System.out.println("Nama kategori berhasil diupdate dari "
                        + oldNameCategory + " menjadi " + newNameCategory + ".");
            } else {
                inputHandler.errorMessage("Nama kategori tidak ditemukan, gagal menghapus.");
            }
        }
    }

    //========================================== Category Section ==========================================

    //============================================ Item Section ============================================

    private static void addItem() {
        String nameItem = inputHandler.getInputText("Masukkan nama item baru: ");
        if (itemController.addItem(nameItem)){
            System.out.println("Item berhasil ditambahkan.");
        } else {
            inputHandler.errorMessage("Item gagal ditambahkan.");
        }
        inputHandler.delayInput();
    }

    private static void removeItem() {
        if (itemController.getItemsSize() == 0) {
            inputHandler.errorMessage("Item kosong, kembali ke menu utama.");
            return;
        }

        itemController.showItems();
        String inputItem = inputHandler.getInputText("Pilih item: ");

        if (itemController.isItemIDExist(inputItem)) {
            String itemName = itemController.getItemyByID(inputItem).getNameItem();
            if (itemController.removeItemByID(inputItem)) {
                System.out.println("Item " + itemName + " berhasil dihapus.");
            } else {
                inputHandler.errorMessage("Item ID tidak ditemukan, gagal menghapus.");
            }
        } else {
            String itemName = itemController.getItemyByName(inputItem).getNameItem();
            if (itemController.removeItemByName(inputItem)) {
                System.out.println("Item " + itemName + " berhasil dihapus.");
            } else {
                inputHandler.errorMessage("Nama item tidak ditemukan, gagal menghapus.");
            }
        }
        inputHandler.delayInput();
    }

    private static void updateNameItem() {
        if (itemController.getItemsSize() == 0) {
            inputHandler.errorMessage("Item kosong, kembali ke menu utama.");
            return;
        }

        itemController.showItems();
        String inputItem = inputHandler.getInputText("Pilih item: ");

        if (!itemController.isItemIDExist(inputItem) && !itemController.isItemNameExist(inputItem)) {
            inputHandler.errorMessage("Item tidak ditemukan, kembali ke menu utama.");
            return;
        }

        String newNameItem = inputHandler.getInputText("Masukkan nama item baru: ");

        if (itemController.isItemIDExist(inputItem)) {
            String oldNameItem = itemController.getItemyByID(inputItem).getNameItem();
            if (itemController.updateNameItemByID(inputItem, newNameItem)) {
                System.out.println("Nama kategori berhasil diupdate dari "
                        + oldNameItem + " menjadi " + newNameItem + ".");
            } else {
                inputHandler.errorMessage("Item ID tidak ditemukan, gagal mengupdate.");
            }
        } else {
            String oldNameItem = itemController.getItemyByName(inputItem).getNameItem();
            if (itemController.updateNameItemByName(inputItem, newNameItem)) {
                System.out.println("Nama kategori berhasil diupdate dari "
                        + oldNameItem + " menjadi " + newNameItem + ".");
            } else {
                inputHandler.errorMessage("Nama item tidak ditemukan, gagal mengupdate.");
            }
        }
    }

    //============================================ Item Section ============================================

    //============================================ Transaction =============================================
    private static void addItemToCategory() {
        if (itemController.getItemsSize() == 0) {
            inputHandler.errorMessage("Item kosong, kembali ke menu utama.");
            return;
        } else if (categoryController.getCategoriesSize() == 0) {
            inputHandler.errorMessage("Kategori kosong, kembali ke menu utama.");
            return;
        }

        itemController.showItems();
        String inputItem = inputHandler.getInputText("Pilih item: ");

        if (!itemController.isItemIDExist(inputItem)) {
            inputHandler.errorMessage("Item ID tidak ditemukan, kembali ke menu utama.");
            return;
        } else if (!itemController.isItemNameExist(inputItem)) {
            inputHandler.errorMessage("Nama item tidak ditemukan, kembali ke menu utama.");
            return;
        }

        categoryController.showCategories();
        String inputCategory = inputHandler.getInputText("Pilih kategori: ");

        if (!categoryController.isCategoryIDExist(inputCategory)) {
            inputHandler.errorMessage("Kategori ID tidak ditemukan, kembali ke menu utama.");
            return;
        } else if (!categoryController.isCategoryNameExist(inputCategory)) {
            inputHandler.errorMessage("Nama kategori tidak ditemukan, kembali ke menu utama.");
            return;
        }

        if (categoryController.isCategoryNameExist(inputCategory)) {
            if (itemController.isItemNameExist(inputItem)) {
                if (transactionCategoriesItemsController.addTransaction(
                        categoryController.getCategoryByName(inputCategory),
                        itemController.getItemyByName(inputItem))) {
                    System.out.println("Item " + itemController.getItemyByName(inputItem).getNameItem()
                            + " berhasil dimasukkan ke dalam kategori "
                            + categoryController.getCategoryByName(inputCategory).getNameCategory());
                } else {
                    inputHandler.errorMessage("Item " + itemController.getItemyByName(inputItem).getNameItem()
                            + " gagal ditambahkan ke dalam kategori "
                            + categoryController.getCategoryByName(inputCategory).getNameCategory());
                }
            } else {
                if (transactionCategoriesItemsController.addTransaction(
                        categoryController.getCategoryByName(inputCategory),
                        itemController.getItemyByID(inputItem))) {
                    System.out.println("Item " + itemController.getItemyByID(inputItem).getNameItem()
                            + " berhasil dimasukkan ke dalam kategori "
                            + categoryController.getCategoryByName(inputCategory).getNameCategory());
                } else {
                    inputHandler.errorMessage("Item " + itemController.getItemyByID(inputItem).getNameItem()
                            + " gagal dimasukkan ke dalam kategori "
                            + categoryController.getCategoryByName(inputCategory).getNameCategory());
                }
            }
        } else {
            if (itemController.isItemNameExist(inputItem)) {
                if (transactionCategoriesItemsController.addTransaction(
                        categoryController.getCategoryByID(inputCategory),
                        itemController.getItemyByName(inputItem))) {
                    System.out.println("Item " + itemController.getItemyByName(inputItem).getNameItem()
                            + " berhasil dimasukkan ke dalam kategori "
                            + categoryController.getCategoryByID(inputCategory).getNameCategory());
                } else {
                    inputHandler.errorMessage("Item " + itemController.getItemyByName(inputItem).getNameItem()
                            + " gagal dimasukkan ke dalam kategori "
                            + categoryController.getCategoryByID(inputCategory).getNameCategory());
                }

            } else {
                if (transactionCategoriesItemsController.addTransaction(
                        categoryController.getCategoryByID(inputCategory),
                        itemController.getItemyByID(inputItem))) {
                    System.out.println("Item " + itemController.getItemyByID(inputItem).getNameItem()
                            + " berhasil dimasukkan ke dalam kategori "
                            + categoryController.getCategoryByID(inputCategory).getNameCategory());
                } else {
                    inputHandler.errorMessage("Item " + itemController.getItemyByID(inputItem).getNameItem()
                            + " gagal dimasukkan ke dalam kategori "
                            + categoryController.getCategoryByID(inputCategory).getNameCategory());
                }

            }
        }
    }

    //============================================ Transaction =============================================
}