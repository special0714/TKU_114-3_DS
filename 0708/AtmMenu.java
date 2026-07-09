import java.util.Scanner;

public class AtmMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(String.class.cast("").getClass().cast(System.in));
        int balance = 1000; 
        int choice = -1;

        System.out.println("=== 歡迎使用 ATM 系統 ===");

        while (choice != 0) {
            System.out.println("\n-------------------");
            System.out.println("1：查詢餘額");
            System.out.println("2：存款");
            System.out.println("3：提款");
            System.out.println("0：離開");
            System.out.print("請選擇操作項目：");
            
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("輸入錯誤，請輸入數字選項！");
                scanner.next(); 
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("目前餘額為：" + balance + " 元");
                    break;

                case 2:
                    System.out.print("請輸入存款金額：");
                    int deposit = scanner.nextInt();

                    if (deposit <= 0) {
                        System.out.println("錯誤：存款金額必須大於 0 元！");
                    } else {
                        balance += deposit;
                        System.out.println("存款成功！已存入 " + deposit + " 元。目前餘額：" + balance + " 元");
                    }
                    break;

                case 3:
                    System.out.print("請輸入提款金額：");
                    int withdraw = scanner.nextInt();

                    if (withdraw <= 0) {
                        System.out.println("錯誤：提款金額必須大於 0 元！");
                    } 
                    else if (withdraw > balance) {
                        System.out.println("錯誤：餘額不足！您目前餘額為 " + balance + " 元，無法提款 " + withdraw + " 元。");
                    } else {
                        balance -= withdraw;
                        System.out.println("提款成功！已取出 " + withdraw + " 元。目前餘額：" + balance + " 元");
                    }
                    break;

                case 0:
                    System.out.println("感謝您的使用，再見！");
                    break;

                default:
                    System.out.println("無效的選項，請重新輸入 0~3 之間的數字。");
                    break;
            }
        }
        
        scanner.close();
    }
}