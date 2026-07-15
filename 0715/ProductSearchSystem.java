import java.util.Scanner;

/**
 * * 1. 測試案例一：顯示全部商品
 * - 操作：選擇功能 1
 * - 預期結果：完整印出 5 項商品（Keyboard, Mouse, Monitor, USB Cable, Headset）之名稱、價格與庫存。
 * * 2. 測試案例二：完整名稱搜尋（完全匹配、忽略大小寫與前後空白）
 * - 操作：選擇功能 2，輸入 "  mOniTor  "
 * - 預期結果：成功找到並顯示 "Monitor" 的詳細資訊（價格 5200, 庫存 5）。
 * * 3. 測試案例三：完整名稱搜尋（找不到商品）
 * - 操作：選擇功能 2，輸入 "Key"
 * - 預期結果：顯示「找不到名稱為 "Key" 的商品。」（因為功能 2 要求精確完整匹配）。
 * * 4. 測試案例四：部分名稱搜尋（多筆結果）
 * - 操作：選擇功能 3，輸入 "o"（或 "O"）
 * - 預期結果：篩選出所有名稱含有 "o" 的商品（Keyboard, Mouse, Monitor），並印出其詳細資料。
 * * 5. 測試案例五：部分名稱搜尋（無符合結果）
 * - 操作：選擇功能 3，輸入 "XYZ"
 * - 預期結果：顯示「沒有任何商品的名稱包含 "XYZ"。」
 * * 6. 測試案例六：搜尋關鍵字在商品名稱中第一次出現的位置
 * - 操作：選擇功能 5，輸入 "board"
 * - 預期結果：
 * - Keyboard: 首次出現於索引 3
 * - 其他商品: 未包含此關鍵字 (顯示 -1 或未找到)
 */
public class ProductSearchSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};
        int[] prices = {890, 490, 5200, 250, 1290};
        int[] stocks = {12, 20, 5, 30, 8};

        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("請選擇操作功能 (1-6): ");
            int choice = readValidInt(sc);

            switch (choice) {
                case 1:
                    showAllProducts(names, prices, stocks);
                    break;
                case 2:
                    searchExactName(sc, names, prices, stocks);
                    break;
                case 3:
                    searchPartialName(sc, names, prices, stocks);
                    break;
                case 4:
                    showLongestProductName(names, prices, stocks);
                    break;
                case 5:
                    showKeywordFirstIndex(sc, names);
                    break;
                case 6:
                    System.out.println("\n系統已成功關閉，感謝您的使用！");
                    running = false;
                    break;
                default:
                    System.out.println("錯誤：無此功能選項，請重新輸入 1 到 6 的數字。");
            }
            System.out.println(); 
        }
        sc.close();
    }

    /**
     * 自訂方法 1：顯示系統主選單
     */
    public static void displayMenu() {
        System.out.println("========== 商品搜尋管理系統 ==========");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 完整名稱搜尋 (忽略大小寫、前後空白)");
        System.out.println("3. 部分名稱搜尋 (支援多筆結果)");
        System.out.println("4. 顯示名稱最長的商品");
        System.out.println("5. 查詢關鍵字於商品名稱之首見位置");
        System.out.println("6. 結束程式");
        System.out.println("======================================");
    }

    /**
     * 自訂方法 2：顯示全部商品資訊
     */
    public static void showAllProducts(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n--- 倉庫商品清冊 ---");
        System.out.printf("%-12s \t%-8s \t%-6s\n", "商品名稱", "價格", "庫存數量");
        System.out.println("----------------------------------------");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%-12s \t$%-7d \t%-6d\n", names[i], prices[i], stocks[i]);
        }
    }

    /**
     * 自訂方法 3：完整名稱搜尋（精確比對、忽略大小寫與前後空白）
     */
    public static void searchExactName(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("請輸入欲搜尋的完整商品名稱: ");
        String target = sc.nextLine().trim(); 

        boolean found = false;
        for (int i = 0; i < names.length; i++) {
            if (names[i].equalsIgnoreCase(target)) {
                System.out.println("\n[精確搜尋成功]");
                System.out.println("商品名稱: " + names[i]);
                System.out.println("商品價格: $" + prices[i]);
                System.out.println("剩餘庫存: " + stocks[i] + " 個");
                found = true;
                break; 
            }
        }

        if (!found) {
            System.out.println("❌ 找不到名稱為 \"" + target + "\" 的商品。");
        }
    }

    /**
     * 自訂方法 4：部分名稱搜尋（模糊比對，顯示多筆符合結果）
     */
    public static void searchPartialName(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("請輸入部分商品關鍵字: ");
        String keyword = sc.nextLine().trim().toLowerCase();

        boolean found = false;
        System.out.println("\n--- 部分搜尋結果 ---");
        System.out.printf("%-12s \t%-8s \t%-6s\n", "商品名稱", "價格", "庫存數量");
        System.out.println("----------------------------------------");

        for (int i = 0; i < names.length; i++) {
            if (names[i].toLowerCase().contains(keyword)) {
                System.out.printf("%-12s \t$%-7d \t%-6d\n", names[i], prices[i], stocks[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("❌ 沒有任何商品的名稱包含 \"" + keyword + "\"。");
        }
    }

    /**
     * 自訂方法 5：顯示商品名稱中最長的商品
     */
    public static void showLongestProductName(String[] names, int[] prices, int[] stocks) {
        if (names == null || names.length == 0) {
            System.out.println("目前無商品資料。");
            return;
        }

        int longestIndex = 0;
        for (int i = 1; i < names.length; i++) {
            if (names[i].length() > names[longestIndex].length()) {
                longestIndex = i;
            }
        }

        System.out.println("\n--- 名字最長商品資訊 ---");
        System.out.println("最長名稱: " + names[longestIndex] + " (長度: " + names[longestIndex].length() + " 個字元)");
        System.out.println("商品單價: $" + prices[longestIndex]);
        System.out.println("現有庫存: " + stocks[longestIndex] + " 個");
    }

    /**
     * 自訂方法 6：顯示商品名稱中，搜尋關鍵字第一次出現的索引位置 (indexOf)
     */
    public static void showKeywordFirstIndex(Scanner sc, String[] names) {
        System.out.print("請輸入要找尋位置的關鍵字: ");
        String keyword = sc.nextLine(); 

        System.out.println("\n--- 關鍵字 \"" + keyword + "\" 首次出現位置 (Index) ---");
        System.out.printf("%-12s \t%-10s\n", "商品名稱", "首次出現索引 (找不到顯示 -1)");
        System.out.println("-------------------------------------------------");

        for (String name : names) {
            int index = name.indexOf(keyword);
            System.out.printf("%-12s \t%-10d\n", name, index);
        }
    }

    private static int readValidInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("輸入錯誤，請輸入有效的選單數字 (1-6)：");
            sc.next(); 
        }
        int val = sc.nextInt();
        sc.nextLine(); 
        return val;
    }
}