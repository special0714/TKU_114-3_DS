import java.util.Scanner;

public class ProductManagementSystem {

    private static int totalAddSuccess = 0;
    private static int totalSellQuantity = 0;
    private static int totalRestockSuccess = 0;
    private static int totalPriceUpdateSuccess = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Product[] products = new Product[10];
        int productCount = 0;

        productCount = initProducts(products, productCount);

        System.out.println("=== 歡迎使用商品管理系統 ===");

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("請選擇選單功能 (1-9): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    displayAll(products, productCount);
                    break;
                case "2":
                    searchByName(products, productCount, scanner);
                    break;
                case "3":
                    productCount = addNewProduct(products, productCount, scanner);
                    break;
                case "4":
                    sellProduct(products, productCount, scanner);
                    break;
                case "5":
                    replenishStock(products, productCount, scanner);
                    break;
                case "6":
                    modifyPrice(products, productCount, scanner);
                    break;
                case "7":
                    displayLowStock(products, productCount);
                    break;
                case "8":
                    displayTotalValue(products, productCount);
                    break;
                case "9":
                    exitAndShowSummary();
                    running = false;
                    break;
                default:
                    System.out.println("【系統提示】輸入錯誤，請輸入 1 到 9 的數字。");
            }
        }
        scanner.close();
    }

    private static int initProducts(Product[] products, int count) {
        products[count++] = new Product("iPhone 15", 29900, 15);
        products[count++] = new Product("MacBook Air", 32900, 5);  // 低庫存
        products[count++] = new Product("iPad Air", 19900, 8);     // 低庫存
        products[count++] = new Product("AirPods Pro", 7490, 25);
        products[count++] = new Product("Apple Watch", 13500, 12);
        return count;
    }

    private static void printMenu() {
        System.out.println("\n================ 選單功能 ================");
        System.out.println("1. 顯示全部商品          2. 依完整名稱搜尋");
        System.out.println("3. 新增商品              4. 出售商品");
        System.out.println("5. 補充庫存              6. 修改商品價格");
        System.out.println("7. 顯示低庫存商品        8. 顯示全部庫存總價值");
        System.out.println("9. 結束並顯示操作摘要");
        System.out.println("=========================================");
    }

    private static int findProductIndex(Product[] products, int count, String searchName) {
        if (searchName == null) return -1;
        String cleanSearch = searchName.trim().toLowerCase();
        for (int i = 0; i < count; i++) {
            if (products[i].getName().toLowerCase().equals(cleanSearch)) {
                return i;
            }
        }
        return -1;
    }

    private static void displayAll(Product[] products, int count) {
        System.out.println("\n--- 全部商品列表 ---");
        if (count == 0) {
            System.out.println("系統內目前沒有任何商品。");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + products[i].toString());
        }
    }

    private static void searchByName(Product[] products, int count, Scanner scanner) {
        System.out.print("請輸入要搜尋的商品名稱: ");
        String name = scanner.nextLine();
        int index = findProductIndex(products, count, name);

        if (index != -1) {
            System.out.println("【搜尋結果】" + products[index].toString());
        } else {
            System.out.println("【搜尋結果】找不到此商品。");
        }
    }

    private static int addNewProduct(Product[] products, int count, Scanner scanner) {
        if (count >= products.length) {
            System.out.println("【新增失敗】陣列已滿（上限 10 項商品），無法再新增。");
            return count;
        }

        System.out.print("請輸入新商品名稱: ");
        String name = scanner.nextLine();

        if (findProductIndex(products, count, name) != -1) {
            System.out.println("【新增失敗】不可新增重複名稱的商品！");
            return count;
        }

        try {
            System.out.print("請輸入價格: ");
            int price = Integer.parseInt(scanner.nextLine());
            System.out.print("請輸入初始庫存: ");
            int stock = Integer.parseInt(scanner.nextLine());

            products[count] = new Product(name, price, stock);
            System.out.println("【新增成功】" + products[count].toString());
            totalAddSuccess++;
            return count + 1;
        } catch (NumberFormatException e) {
            System.out.println("【新增失敗】輸入格式錯誤，價格與庫存請輸入整數數字。");
            return count;
        }
    }

    private static void sellProduct(Product[] products, int count, Scanner scanner) {
        System.out.print("請輸入欲出售的商品名稱: ");
        String name = scanner.nextLine();
        int index = findProductIndex(products, count, name);

        if (index == -1) {
            System.out.println("【出售失敗】找不到該商品。");
            return;
        }

        try {
            System.out.print("請輸入出售數量: ");
            int qty = Integer.parseInt(scanner.nextLine());

            if (products[index].sell(qty)) {
                System.out.println("【出售成功】已售出 " + qty + " 件。當前庫存: " + products[index].getStock());
                totalSellQuantity += qty;
            } else {
                System.out.println("【出售失敗】數量必須大於 0 且不可超過目前庫存（目前庫存: " + products[index].getStock() + "）。");
            }
        } catch (NumberFormatException e) {
            System.out.println("【出售失敗】請輸入有效的整數。");
        }
    }

    private static void replenishStock(Product[] products, int count, Scanner scanner) {
        System.out.print("請輸入欲補貨的商品名稱: ");
        String name = scanner.nextLine();
        int index = findProductIndex(products, count, name);

        if (index == -1) {
            System.out.println("【補貨失敗】找不到該商品。");
            return;
        }

        try {
            System.out.print("請輸入補充數量: ");
            int qty = Integer.parseInt(scanner.nextLine());

            if (products[index].restock(qty)) {
                System.out.println("【補貨成功】已補充庫存。當前庫存: " + products[index].getStock());
                totalRestockSuccess++;
            } else {
                System.out.println("【補貨失敗】補貨數量必須大於 0。");
            }
        } catch (NumberFormatException e) {
            System.out.println("【補貨失敗】請輸入有效的整數。");
        }
    }

    private static void modifyPrice(Product[] products, int count, Scanner scanner) {
        System.out.print("請輸入欲修改價格的商品名稱: ");
        String name = scanner.nextLine();
        int index = findProductIndex(products, count, name);

        if (index == -1) {
            System.out.println("【修改失敗】找不到該商品。");
            return;
        }

        try {
            System.out.print("請輸入新價格: ");
            int newPrice = Integer.parseInt(scanner.nextLine());

            if (products[index].setPrice(newPrice)) {
                System.out.println("【修改成功】價格已更新為: " + products[index].getPrice());
                totalPriceUpdateSuccess++;
            } else {
                System.out.println("【修改失敗】價格必須大於 0。");
            }
        } catch (NumberFormatException e) {
            System.out.println("【修改失敗】請輸入有效的價格數字。");
        }
    }

    private static void displayLowStock(Product[] products, int count) {
        System.out.println("\n--- 低庫存警示列表 (庫存 < 10) ---");
        boolean hasLowStock = false;
        for (int i = 0; i < count; i++) {

            if (products[i].isLowStock()) {
                System.out.println(products[i].toString());
                hasLowStock = true;
            }
        }
        if (!hasLowStock) {
            System.out.println("所有商品庫存充足！");
        }
    }

    private static void displayTotalValue(Product[] products, int count) {
        long grandTotalValue = 0;
        for (int i = 0; i < count; i++) {
            grandTotalValue += products[i].getInventoryValue();
        }
        System.out.println("\n【全店庫存總價值】" + grandTotalValue + " 元");
    }

    private static void exitAndShowSummary() {
        System.out.println("\n======================================");
        System.out.println("          系統登出 - 操作摘要          ");
        System.out.println("======================================");
        System.out.println(" 成功新增商品種類次數: " + totalAddSuccess + " 次");
        System.out.println(" 商品出售總數量      : " + totalSellQuantity + " 件");
        System.out.println(" 成功補充庫存次數    : " + totalRestockSuccess + " 次");
        System.out.println(" 成功修改價格次數    : " + totalPriceUpdateSuccess + " 次");
        System.out.println("======================================");
        System.out.println("系統已關閉，謝謝使用！");
    }
}