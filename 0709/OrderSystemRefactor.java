import java.util.Scanner;

public class OrderSystemRefactor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int totalQuantity = 0; 
        int totalAmount = 0;   
        int choice;           

        do {
            printMenu();
            choice = sc.nextInt();

            if (choice == 1 || choice == 2 || choice == 3) {
                int price = getPrice(choice);
                
                String itemName = (choice == 1) ? "Black tea" : (choice == 2) ? "Green tea" : "Coffee";

                int quantity = readValidQuantity(sc);

                int subtotal = calculateSubtotal(price, quantity);
                
                totalQuantity += quantity;
                totalAmount += subtotal;
                System.out.println("-> 已加入購物車：" + itemName + " x " + quantity + "，小計：" + subtotal + " 元");

            } else if (choice != 0) {
                System.out.println("無效的選項，請重新選擇。");
            }

        } while (choice != 0); 

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
        if (option == 1) {
            return 30;
        } else if (option == 2) {
            return 35;
        } else if (option == 3) {
            return 50;
        }
        return 0; 
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