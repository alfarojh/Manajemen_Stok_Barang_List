package custom;

import java.util.Arrays;

public class Text {
    private final String title;
    private final String[] subTitle;
    private char[] alignment;
    private final int[] textLength;

    private final int widthTabel;


    // Konstruktor untuk kelas Text yang digunakan untuk mencetak teks dalam bentuk tabel.
    public Text(String title, String[] subTitle, char[] alignment, int[] textLength) {
        // Inisialisasi atribut-atribut berdasarkan input yang diberikan.
        this.title = title;
        this.subTitle = subTitle;
        this.alignment = alignment;
        this.textLength = textLength;
        // Hitung lebar tabel berdasarkan panjang teks di setiap kolom dan judul subjudul.
        widthTabel = Arrays.stream(textLength).sum() + (subTitle.length * 3) - 1;
    }

    // Fungsi untuk mencetak judul tabel.
    public void printTitle() {
        line();  // Cetak garis batas atas tabel.
        // Cetak judul tabel dengan rata kanan.
        System.out.println("|  " + title + " ".repeat(widthTabel - title.length() - 4) + "  |");
        line();  // Cetak garis batas bawah judul tabel.
        printSubTitle();  // Cetak subjudul tabel.
    }

    // Fungsi untuk mencetak subjudul tabel.
    public void printSubTitle() {
        System.out.print("| ");
        for (int indexSubTitle = 0; indexSubTitle < textLength.length; indexSubTitle++) {
            int length = (textLength[indexSubTitle] - subTitle[indexSubTitle].length()) / 2;
            System.out.print(" ".repeat(length) + subTitle[indexSubTitle]);
            if ((textLength[indexSubTitle] - subTitle[indexSubTitle].length()) % 2 == 0) {
                System.out.print(" ".repeat(length));
            } else {
                System.out.print(" ".repeat(length + 1));
            }
            System.out.print(" | ");
        }
        System.out.println();
        line();  // Cetak garis batas bawah subjudul tabel.
    }

    // Fungsi untuk mencetak isi tabel.
    public void printBody(int index, String[] content) {
        // Berikan warna biru pada baris genap untuk membedakan isi tabel.
        if (index % 2 == 0) {
            System.out.print("\033[34m"); // Warna biru (kode ANSI).
        }
        System.out.print("| ");
        for (int indexSubTitle = 0; indexSubTitle < content.length; indexSubTitle++) {
            if (alignment[indexSubTitle] == 'c' || alignment[indexSubTitle] == 'C') {
                // Cetak konten rata tengah.
                int length = (textLength[indexSubTitle] - content[indexSubTitle].length()) / 2;
                System.out.print(" ".repeat(length) + content[indexSubTitle]);
                if ((textLength[indexSubTitle] - content[indexSubTitle].length()) % 2 == 0) {
                    System.out.print(" ".repeat(length));
                } else {
                    System.out.print(" ".repeat(length + 1));
                }
                System.out.print(" | ");
            } else {
                // Cetak konten tanpa perataan (alignment) atau perataan ke kiri.
                System.out.print(content[indexSubTitle] + " ".repeat(textLength[indexSubTitle] - content[indexSubTitle].length()) + " | ");
            }
        }
        System.out.println("\u001B[0m");  // Kembalikan warna ke default (kode ANSI).
    }

    // Fungsi untuk mencetak garis pembatas tabel.
    public void line() {
        System.out.println("|" + "=".repeat(widthTabel) + "|");
    }

}
