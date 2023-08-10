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
//                    case 1 -> addCategory();
//                    case 2 -> removeCategory();
//                    case 3 -> updateNameCategory();
//                    case 4 -> addItem();
//                    case 5 -> removeItem();
//                    case 6 -> updateNameItem();
//                    case 7 -> updateStockItem();
//                    case 8 -> searchItem();
//                    case 9 -> displayAllItems();
                    case 10 -> warehouseController.showLog();
                    default -> inputHandler.errorMessage("Maaf, input diluar batas pilihan");
                }
            } catch (Exception e) {
                // Menangkap dan menampilkan pesan jika terjadi exception (kesalahan)
                System.out.println("Keluar dari program");
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