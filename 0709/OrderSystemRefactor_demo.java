import java.util.Scanner;

public class OrderSystemRefactor_demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int totalQuantity = 0; 
        int totalAmount = 0;   
        int choice = -1;           

        System.out.println("=== 歡迎使用點餐系統 ===");

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
                case 2:
                case 3:
                    int price = getPrice(choice);
                    String itemName = getItemName(choice);

                    int quantity = readValidQuantity(sc);

                    int subtotal = calculateSubtotal(price, quantity);
                    
                    totalQuantity += quantity;
                    totalAmount += subtotal;
                    System.out.println("-> 已加入購物車：" + itemName + " x " + quantity + "，小計：" + subtotal + " 元");
                    break;

                case 0:
                    System.out.println("正在為您結帳...");
                    break;

                default:
                    System.out.println("無效的選項，請重新選擇。");
                    break;
            }
        } 

        printReceipt(totalQuantity, totalAmount);

        sc.close();
    }

    public static void printMenu() {
        System.out.println("\n--- 商品選單 ---");
        System.out.println("1. Black tea  $30");
        System.out.println("2. Green tea  $35");
        System.out.println("3. Coffee     $50");
        System.out.println("0. Checkout   結帳");
        System.out.print("請輸入商品選項：");
    }

    public static int getPrice(int option) {
        switch (option) {
            case 1: return 30;
            case 2: return 35;
            case 3: return 50;
            default: return 0;
        }
    }

    public static String getItemName(int option) {
        switch (option) {
            case 1: return "Black tea";
            case 2: return "Green tea";
            case 3: return "Coffee";
            default: return "Unknown";
        }
    }

    public static int readValidQuantity(Scanner sc) {
        System.out.print("請輸入數量：");
        int quantity = sc.nextInt();
        
        while (quantity <= 0) {
            System.out.print("數量必須大於 0，請重新輸入：");
            quantity = sc.nextInt();
        }
        return quantity;
    }

    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    public static void printReceipt(int totalItems, int totalAmount) {
        System.out.println("\n--- 結帳明細 ---");
        System.out.println("總數量：" + totalItems + " 杯");
        System.out.println("總金額：" + totalAmount + " 元");
        System.out.println("謝謝光臨！");
    }
}