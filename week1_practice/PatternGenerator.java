import java.util.Scanner;

public class PatternGenerator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();
            System.out.print("請輸入選項：");

            if (!sc.hasNextInt()) {
                System.out.println("無效的選項，請重新輸入！\n");
                sc.next(); 
                continue;
            }
            int option = sc.nextInt();

            if (option == 0) {
                System.out.println("程式已結束，謝謝使用！");
                break;
            }

            switch (option) {
                case 1:
                    printMultiplicationTable();
                    break;
                case 2:
                    int triHeight = readPositiveInt(sc, "請輸入正三角形高度：");
                    printTriangle(triHeight);
                    break;
                case 3:
                    int revTriHeight = readPositiveInt(sc, "請輸入倒三角形高度：");
                    printReverseTriangle(revTriHeight);
                    break;
                case 4:
                    int rows = readPositiveInt(sc, "請輸入方格列數（rows）：");
                    int cols = readPositiveInt(sc, "請輸入方格欄數（cols）：");
                    printNumberGrid(rows, cols);
                    break;
                default:
                    System.out.println("無效的選項，請重新輸入！\n");
                    break;
            }
        }
        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== Pattern Generator ===");
        System.out.println("1. 九九乘法表");
        System.out.println("2. 正三角形星號");
        System.out.println("3. 倒三角形星號");
        System.out.println("4. 數字方格");
        System.out.println("0. 離開");
    }

    public static int readPositiveInt(Scanner sc, String message) {
        int value;
        while (true) {
            System.out.print(message);
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                if (value > 0) {
                    break; 
                }
            } else {
                sc.next(); 
            }
            System.out.println("輸入錯誤，數值必須大於 0，請重新輸入。");
        }
        return value;
    }

    public static void printMultiplicationTable() {
        System.out.println("\n=== 九九乘法表 ===");
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                System.out.print(j + "x" + i + "=" + (i * j) + "\t");
            }
            System.out.println(); 
        }
        System.out.println();
    }

    public static void printTriangle(int height) {
        System.out.println("\n=== 正三角形 (高度: " + height + ") ===");
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printReverseTriangle(int height) {
        System.out.println("\n=== 倒三角形 (高度: " + height + ") ===");
        for (int i = height; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printNumberGrid(int rows, int cols) {
        System.out.println("\n=== 數字方格 (" + rows + "列 " + cols + "欄) ===");
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}