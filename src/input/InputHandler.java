package input;

import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner = new Scanner(System.in);

    // Menampilkan pesan error dalam teks merah (untuk digunakan pada terminal yang mendukung ANSI escape code)
    public void errorMessage(String message) {
        System.out.println("\u001B[31m" + message + "\u001B[0m\n");
    }

    // Fungsi ini memeriksa apakah input merupakan bilangan bulat (digit) dan memiliki operator + atau -
    public int getIntegerInputWithOperator(String message) {
        System.out.print(message); // Menampilkan pesan kepada pengguna dan mendapatkan input
        String input = scanner.nextLine().trim();

        // Melakukan validasi untuk memastikan input hanya berisi digit dan merupakan bilangan positif atau negatif
        while (!input.matches("^[-+]?\\d+")) {
            // Jika input tidak valid, tampilkan pesan kesalahan dan minta input lagi
            errorMessage("Maaf, harap masukkan input berupa bilangan bulat atau menggunakan operator + atau -");
            System.out.print(message);
            input = scanner.nextLine();
        }
        // Mengubah input menjadi bilangan bulat dan mengembalikannya
        return Integer.parseInt(input);
    }

    // Fungsi ini memeriksa apakah input merupakan bilangan bulat (digit) dan memiliki operator + atau -
    public int getIntegerInput(String message) {
        System.out.print(message); // Menampilkan pesan kepada pengguna dan mendapatkan input
        String input = scanner.nextLine().trim();

        // Melakukan validasi untuk memastikan input hanya berisi digit dan merupakan bilangan positif atau negatif
        while (!input.matches("\\d+")) {
            // Jika input tidak valid, tampilkan pesan kesalahan dan minta input lagi
            errorMessage("Maaf, harap masukkan input berupa bilangan bulat");
            System.out.print(message);
            input = scanner.nextLine();
        }
        // Mengubah input menjadi bilangan bulat dan mengembalikannya
        return Integer.parseInt(input);
    }

    // Fungsi untuk menunda masukan dari pengguna
    public void delayInput () {
        scanner.nextLine();
    }

    // Fungsi untuk menerima masukan dari pengguna dengan menampilkan pesan terlebih dahulu
    public String getInputText (String message) {
        System.out.print(message);
        String input = scanner.nextLine().trim();
        while (input.equals("")) {
            errorMessage("Maaf, input tidak boleh kosong!");
            System.out.print(message);
            input = scanner.nextLine().trim();
        }
        return input;
    }

    // Format untuk menata teks menjadi rata kanan berdasarkan panjang teks dan panjang maksimum yang diinginkan
    public String formatAutoSpacingLeft(String text, int maxLengthText) {
        if (text.length() < maxLengthText) {
            // Jika panjang teks lebih pendek dari panjang maksimum, tambahkan spasi di sebelah kanan
            int numSpace = maxLengthText - text.length();
            return text + " ".repeat(numSpace);
        } else {
            // Jika panjang teks sudah sama atau lebih panjang dari panjang maksimum, kembalikan teks asli
            return text;
        }
    }

    // Format untuk menata teks menjadi rata tengah berdasarkan panjang teks dan panjang maksimum yang diinginkan
    public String formatAutoSpacingCenter(String text, int maxLengthText) {
        if (text.length() < maxLengthText) {
            // Jika panjang teks lebih pendek dari panjang maksimum, tambahkan spasi di sebelah kiri dan kanan
            int numSpace = maxLengthText - text.length();
            StringBuilder spaceText = new StringBuilder();

            if (numSpace % 2 == 1) {
                // Jika jumlah spasi ganjil, tambahkan spasi tambahan di sebelah kanan
                numSpace /= 2;
                spaceText.append(" ".repeat(numSpace));
                spaceText.append(text);
                spaceText.append(" ".repeat(numSpace + 1));
            } else {
                // Jika jumlah spasi genap, tambahkan jumlah spasi yang sama di sebelah kiri dan kanan
                numSpace /= 2;
                spaceText.append(" ".repeat(numSpace));
                spaceText.append(text);
                spaceText.append(" ".repeat(numSpace));
            }
            return spaceText.toString();
        } else {
            return text; // Jika panjang teks sudah sama atau lebih panjang dari panjang maksimum, kembalikan teks asli
        }
    }

    // Menutup scanner setelah penggunaan selesai
    public void close () {
        scanner.close();
    }
}
