import java.util.Scanner;

public class AtmMethodHomework {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int balance = 1000; 
        int choice = -1;

        System.out.println("=== 歡迎使用 ATM 方法版系統 ===");

        while (choice != 0) {
            printMenu(); 
            
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
            } else {
                System.out.println("輸入錯誤，請輸入數字選項！");
                sc.next(); 
                continue;
            }

            switch (choice) {
                case 1:
                    printBalance(balance);
                    break;

                case 2:
                    int depositAmount = readPositiveAmount(sc, "請輸入存款金額：");
                    balance = deposit(balance, depositAmount);
                    break;

                case 3:
                    int withdrawAmount = readPositiveAmount(sc, "請輸入提款金額：");
                    balance = withdraw(balance, withdrawAmount);
                    break;

                case 0:
                    System.out.println("感謝您的使用，再見！");
                    break;

                default:
                    System.out.println("無效的選項，請重新輸入。");
                    break;
            }
        }

        sc.close();
    }

    public static void printMenu() {
        System.out.println("\n-------------------");
        System.out.println("1：查詢餘額");
        System.out.println("2：存款");
        System.out.println("3：提款");
        System.out.println("0：離開");
        System.out.print("請選擇操作項目：");
    }

    public static int readPositiveAmount(Scanner sc, String message) {
        System.out.print(message);
        int amount = sc.nextInt();
        while (amount <= 0) {
            System.out.print("錯誤：金額必須大於 0 元！請重新輸入：");
            amount = sc.nextInt();
        }
        return amount;
    }

    public static int deposit(int balance, int amount) {
        balance += amount;
        System.out.println("存款成功！已存入 " + amount + " 元。");
        return balance;
    }

    public static int withdraw(int balance, int amount) {
        if (amount > balance) {
            System.out.println("錯誤：餘額不足！交易取消。");
            return balance; 
        }
        balance -= amount;
        System.out.println("提款成功！已取出 " + amount + " 元。");
        return balance;
    }

    public static void printBalance(int balance) {
        System.out.println("目前餘額為：" + balance + " 元");
    }
}