import java.util.Scanner;

public class ProductArraySystem {

    private static final int LOW_STOCK_THRESHOLD = 10;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};
        int[] prices = {890, 490, 5200, 250, 1290};
        int[] stocks = {12, 20, 5, 30, 8};

        int purchaseCount = 0;
        int restockCount = 0;

        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("請選擇操作功能 (1-7): ");
            int choice = readValidInt(sc);

            switch (choice) {
                case 1:
                    showAllProducts(names, prices, stocks);
                    break;
                case 2:
                    queryProductById(sc, names, prices, stocks);
                    break;
                case 3:
                    if (purchaseProduct(sc, names, prices, stocks)) {
                        purchaseCount++;
                    }
                    break;
                case 4:
                    if (restockProduct(sc, names, stocks)) {
                        restockCount++;
                    }
                    break;
                case 5:
                    showLowStock(names, stocks);
                    break;
                case 6:
                    showTotalValue(names, prices, stocks);
                    break;
                case 7:
                    showSummary(purchaseCount, restockCount);
                    running = false;
                    break;
                default:
                    System.out.println("錯誤：無此功能選項，請重新輸入 1 到 7 的數字。");
            }
            System.out.println(); 
        }
        sc.close();
    }

    public static void displayMenu() {
        System.out.println("========== 商品管理系統 ==========");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依商品編號查詢");
        System.out.println("3. 購買商品");
        System.out.println("4. 補充商品庫存");
        System.out.println("5. 顯示低庫存商品 (< 10)");
        System.out.println("6. 顯示全部庫存總價值");
        System.out.println("7. 結束程式");
        System.out.println("==================================");
    }

    public static void showAllProducts(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n--- 全部商品清單 ---");
        System.out.printf("%-6s \t%-12s \t%-8s \t%-6s\n", "編號", "商品名稱", "價格", "庫存");
        System.out.println("----------------------------------------------");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("#%-5d \t%-12s \t$%-7d \t%-6d\n", (i + 1), names[i], prices[i], stocks[i]);
        }
    }

    public static void queryProductById(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("請輸入要查詢的商品編號 (1-" + names.length + "): ");
        int id = readValidInt(sc);
        int index = id - 1; 
        if (index >= 0 && index < names.length) {
            System.out.println("\n--- 查詢結果 ---");
            System.out.println("商品名稱: " + names[index]);
            System.out.println("商品價格: $" + prices[index]);
            System.out.println("目前庫存: " + stocks[index] + " 個");
        } else {
            System.out.println("錯誤：找不到該編號的商品！");
        }
    }

    public static boolean purchaseProduct(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("請輸入欲購買的商品編號 (1-" + names.length + "): ");
        int id = readValidInt(sc);
        int index = id - 1;

        if (index < 0 || index >= names.length) {
            System.out.println("錯誤：無此商品編號！");
            return false;
        }

        System.out.println("您選擇的商品是: " + names[index] + " (目前庫存: " + stocks[index] + ")");
        if (stocks[index] == 0) {
            System.out.println("抱歉，該商品已無庫存，無法購買！");
            return false;
        }

        System.out.print("請輸入欲購買數量: ");
        int amount = readValidInt(sc);

        if (amount <= 0) {
            System.out.println("錯誤：購買數量必須大於 0！");
            return false;
        }
        if (amount > stocks[index]) {
            System.out.println("錯誤：庫存不足！上限為 " + stocks[index] + " 個。");
            return false;
        }

        stocks[index] -= amount;
        int cost = prices[index] * amount;
        System.out.println("購買成功！您購買了 " + amount + " 個 " + names[index]);
        System.out.println("消費總金額: $" + cost + "，商品庫存剩餘: " + stocks[index]);
        return true;
    }

    public static boolean restockProduct(Scanner sc, String[] names, int[] stocks) {
        System.out.print("請輸入欲補貨的商品編號 (1-" + names.length + "): ");
        int id = readValidInt(sc);
        int index = id - 1;

        if (index < 0 || index >= names.length) {
            System.out.println("錯誤：無此商品編號！");
            return false;
        }

        System.out.println("您選擇的商品是: " + names[index] + " (目前庫存: " + stocks[index] + ")");
        System.out.print("請輸入要補充的庫存數量: ");
        int amount = readValidInt(sc);

        // 驗證規則：補貨數量必須大於 0
        if (amount <= 0) {
            System.out.println("錯誤：補貨數量必須大於 0！");
            return false;
        }

        stocks[index] += amount;
        System.out.println("補貨成功！" + names[index] + " 已更新庫存為: " + stocks[index]);
        return true;
    }

    public static void showLowStock(String[] names, int[] stocks) {
        System.out.println("\n--- 低庫存商品警示 (< " + LOW_STOCK_THRESHOLD + ") ---");
        boolean hasLowStock = false;
        for (int i = 0; i < names.length; i++) {
            if (stocks[i] < LOW_STOCK_THRESHOLD) {
                System.out.println("商品編號 #" + (i + 1) + " [" + names[i] + "] 庫存告急！剩餘: " + stocks[i]);
                hasLowStock = true;
            }
        }
        if (!hasLowStock) {
            System.out.println("目前所有商品庫存充足！");
        }
    }

    public static void showTotalValue(String[] names, int[] prices, int[] stocks) {
        int grandTotal = 0;
        System.out.println("\n--- 各商品庫存價值試算 ---");
        for (int i = 0; i < names.length; i++) {
            int value = prices[i] * stocks[i];
            grandTotal += value;
            System.out.printf("%-12s (單價:$%-5d * 庫存:%-3d) = 價值:$%-d\n", names[i], prices[i], stocks[i], value);
        }
        System.out.println("----------------------------------------------");
        System.out.println("目前倉庫全商品總價值為：$" + grandTotal);
    }

    public static void showSummary(int purchaseCount, int restockCount) {
        System.out.println("\n========== 系統已安全登出 ==========");
        System.out.println("【本次執行操作摘要】");
        System.out.println("成功進行購買次數：" + purchaseCount + " 次");
        System.out.println("成功進行補貨次數：" + restockCount + " 次");
        System.out.println("感謝您的使用，再見！");
        System.out.println("====================================");
    }

    private static int readValidInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("輸入錯誤，請輸入有效的整數數字：");
            sc.next(); 
        }
        return sc.nextInt();
    }
}