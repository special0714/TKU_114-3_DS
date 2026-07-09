import java.util.Scanner;

public class OrderSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int totalQuantity = 0; 
        int totalAmount = 0;   
        int choice;           

        do {
            System.out.println("\n--- 商品選單 ---");
            System.out.println("1. Black tea  $30");
            System.out.println("2. Green tea  $35");
            System.out.println("3. Coffee     $50");
            System.out.println("0. Checkout   結帳");
            System.out.print("請輸入商品選項：");
            choice = sc.nextInt();

            if (choice == 1 || choice == 2 || choice == 3) {
                // 決定單價
                int price = 0;
                String itemName = "";
                if (choice == 1) {
                    price = 30;
                    itemName = "Black tea";
                } else if (choice == 2) {
                    price = 35;
                    itemName = "Green tea";
                } else {
                    price = 50;
                    itemName = "Coffee";
                }

                System.out.print("請輸入 " + itemName + " 的數量：");
                int quantity = sc.nextInt();
                while (quantity <= 0) {
                    System.out.print("數量必須大於 0，請重新輸入：");
                    quantity = sc.nextInt();
                }

                int subtotal = price * quantity;
                totalQuantity += quantity;
                totalAmount += subtotal;
                System.out.println("-> 已加入購物車：" + itemName + " x " + quantity + "，小計：" + subtotal + " 元");

            } else if (choice != 0) {
                System.out.println("無效的選項，請重新選擇。");
            }

        } while (choice != 0); 

        System.out.println("\n--- 結帳明細 ---");
        System.out.println("總數量：" + totalQuantity + " 杯");
        System.out.println("總金額：" + totalAmount + " 元");
        System.out.println("謝謝光臨！");

        sc.close();
    }
}