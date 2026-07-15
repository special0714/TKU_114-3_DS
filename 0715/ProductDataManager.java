import java.util.Scanner;

public class ProductDataManager {

    private static final int LOW_STOCK_THRESHOLD = 10;
    
    private static int currentSize = 5;
    private static String[] names = new String[50];
    private static int[] prices = new int[50];
    private static int[] stocks = new int[50];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] records = {
            "Keyboard,890,12",
            "Mouse,490,20",
            "Monitor,5200,5",
            "USB Cable,250,30",
            "Headset,1290,8"
        };
        
        parseAndLoadRecords(records);

        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("請選擇操作功能 (1-7): ");
            int choice = readValidInt(sc);

            switch (choice) {
                case 1:
                    showProductTable();
                    break;
                case 2:
                    searchExactName(sc);
                    break;
                case 3:
                    searchPartialName(sc);
                    break;
                case 4:
                    showLowStock();
                    break;
                case 5:
                    showTotalValue();
                    break;
                case 6:
                    addNewRecord(sc);
                    break;
                case 7:
                    System.out.println("\n系統已安全關閉，謝謝使用！");
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
        System.out.println("========== 商品資料管理系統 ==========");
        System.out.println("1. 顯示商品清單表格");
        System.out.println("2. 完整名稱搜尋 (忽略大小寫)");
        System.out.println("3. 部分名稱搜尋 (模糊搜尋)");
        System.out.println("4. 顯示低庫存商品 (< 10)");
        System.out.println("5. 顯示庫存總價值");
        System.out.println("6. 新增一筆商品資料 (格式：名稱,價格,庫存)");
        System.out.println("7. 結束程式");
        System.out.println("======================================");
    }

    public static void parseAndLoadRecords(String[] records) {
        for (int i = 0; i < records.length; i++) {
            String[] parts = records[i].split(",");
            names[i] = parts[0].trim();
            prices[i] = Integer.parseInt(parts[1].trim());
            stocks[i] = Integer.parseInt(parts[2].trim());
        }
    }

    public static void showProductTable() {
        System.out.println("\n--- 目前商品庫存清冊 ---");
        System.out.printf("%-6s \t%-15s \t%-8s \t%-6s\n", "編號", "商品名稱", "價格", "庫存");
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < currentSize; i++) {
            System.out.printf("#%-5d \t%-15s \t$%-7d \t%-6d\n", (i + 1), names[i], prices[i], stocks[i]);
        }
    }

    public static void searchExactName(Scanner sc) {
        System.out.print("請輸入欲搜尋的「完整」商品名稱: ");
        String target = sc.nextLine().trim();

        boolean found = false;
        for (int i = 0; i < currentSize; i++) {
            if (names[i].equalsIgnoreCase(target)) {
                System.out.println("\n[精確搜尋成功]");
                System.out.println("商品名稱: " + names[i]);
                System.out.println("商品價格: $" + prices[i]);
                System.out.println("現有庫存: " + stocks[i]);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println(" 找不到名稱為 \"" + target + "\" 的商品。");
        }
    }

    public static void searchPartialName(Scanner sc) {
        System.out.print("請輸入欲搜尋的商品關鍵字: ");
        String keyword = sc.nextLine().trim().toLowerCase();

        boolean found = false;
        System.out.println("\n--- 部分名稱搜尋結果 ---");
        for (int i = 0; i < currentSize; i++) {
            if (names[i].toLowerCase().contains(keyword)) {
                System.out.printf("#%d - %s (價格: $%d, 庫存: %d)\n", (i + 1), names[i], prices[i], stocks[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println(" 沒有任何商品的名稱包含 \"" + keyword + "\"。");
        }
    }

    public static void showLowStock() {
        System.out.println("\n--- 低庫存警示 (< " + LOW_STOCK_THRESHOLD + " ) ---");
        boolean hasLowStock = false;
        for (int i = 0; i < currentSize; i++) {
            if (stocks[i] < LOW_STOCK_THRESHOLD) {
                System.out.printf("編號 #%d [%s] 庫存不足！現存: %d\n", (i + 1), names[i], stocks[i]);
                hasLowStock = true;
            }
        }
        if (!hasLowStock) {
            System.out.println("目前全部商品庫存皆高於安全標準。");
        }
    }

    public static void showTotalValue() {
        int grandTotal = 0;
        System.out.println("\n--- 庫存價值試算表 ---");
        for (int i = 0; i < currentSize; i++) {
            int value = prices[i] * stocks[i];
            grandTotal += value;
            System.out.printf("%-15s (單價:$%-5d * 庫存:%-3d) = 價值:$%-d\n", names[i], prices[i], stocks[i], value);
        }
        System.out.println("-------------------------------------------------");
        System.out.println("倉庫商品總庫存價值為：$" + grandTotal);
    }

    public static void addNewRecord(Scanner sc) {
        if (currentSize >= names.length) {
            System.out.println("錯誤：系統容量已達上限，無法再新增商品！");
            return;
        }

        System.out.println("\n請輸入新商品資料，格式為「名稱,價格,庫存」（例如：Speaker,1500,15）");
        System.out.print("請輸入：");
        String input = sc.nextLine().trim();

        String[] parts = input.split(",");
        if (parts.length != 3) {
            System.out.println(" 格式錯誤：資料必須剛好包含兩個逗號以區隔三項欄位（名稱、價格、庫存）。");
            return;
        }

        String newName = parts[0].trim();
        String priceStr = parts[1].trim();
        String stockStr = parts[2].trim();

        if (newName.isEmpty()) {
            System.out.println(" 格式錯誤：商品名稱不能為空字串。");
            return;
        }

        int newPrice = 0;
        int newStock = 0;

        try {
            newPrice = Integer.parseInt(priceStr);
        } catch (NumberFormatException e) {
            System.out.println(" 轉換錯誤：價格 \"" + priceStr + "\" 不是合法的整數數字。");
            return;
        }

        try {
            newStock = Integer.parseInt(stockStr);
        } catch (NumberFormatException e) {
            System.out.println(" 轉換錯誤：庫存 \"" + stockStr + "\" 不是合法的整數數字。");
            return;
        }

        // 4. 驗證數值範圍限制
        if (newPrice < 0) {
            System.out.println(" 數值錯誤：價格不能小於 0。");
            return;
        }
        if (newStock < 0) {
            System.out.println(" 數值錯誤：庫存不能小於 0。");
            return;
        }

        names[currentSize] = newName;
        prices[currentSize] = newPrice;
        stocks[currentSize] = newStock;
        currentSize++;

        System.out.println(" 商品新增成功！已加入商品: " + newName + " (價格: " + newPrice + ", 庫存: " + newStock + ")");
    }

    private static int readValidInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("輸入錯誤，請輸入有效的選單數字 (1-7)：");
            sc.next();
        }
        int val = sc.nextInt();
        sc.nextLine(); 
        return val;
    }
}