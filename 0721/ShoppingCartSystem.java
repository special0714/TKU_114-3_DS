import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingCartSystem {

    private static final List<CartItem> cartList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("請選擇操作項目 (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addItem();
                    break;
                case "2":
                    updateQuantity();
                    break;
                case "3":
                    removeItem();
                    break;
                case "4":
                    listCartAndTotal();
                    break;
                case "5":
                    clearCart();
                    break;
                case "6":
                    running = false;
                    System.out.println("感謝使用，系統已結束！");
                    break;
                default:
                    System.out.println(" 無效的選擇，請輸入 1 到 6 之間的數字。");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("====================================");
        System.out.println("         購物車管理系統");
        System.out.println("====================================");
        System.out.println("1. 加入商品 / 累加數量");
        System.out.println("2. 修改商品數量");
        System.out.println("3. 移除商品");
        System.out.println("4. 查看購物車與總額");
        System.out.println("5. 清空購物車");
        System.out.println("6. 離開系統");
        System.out.println("====================================");
    }

    private static void addItem() {
        System.out.print("請輸入商品代碼: ");
        String id = scanner.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println(" 錯誤：商品代碼不能為空白！");
            return;
        }

        try {
            System.out.print("請輸入加入數量: ");
            int quantity = Integer.parseInt(scanner.nextLine().trim());

            if (quantity <= 0) {
                System.out.println(" 錯誤：新增數量必須大於 0！");
                return;
            }

            CartItem existingItem = findItemById(id);

            if (existingItem != null) {
                int newQty = existingItem.getQuantity() + quantity;
                existingItem.setQuantity(newQty);
                System.out.println(" 商品代碼「" + id + "」已存在於購物車，數量已自動累加！");
                System.out.println("   目前「" + existingItem.getName() + "」總數量為：" + newQty);
            } else {
                System.out.print("請輸入商品名稱: ");
                String name = scanner.nextLine().trim();

                if (name.isEmpty()) {
                    System.out.println(" 錯誤：商品名稱不能為空白！");
                    return;
                }

                System.out.print("請輸入商品單價: ");
                double price = Double.parseDouble(scanner.nextLine().trim());

                if (price < 0) {
                    System.out.println(" 錯誤：單價不能為負數！");
                    return;
                }

                cartList.add(new CartItem(id, name, price, quantity));
                System.out.println(" 成功加入商品：「" + name + "」 (代碼: " + id + ")");
            }

        } catch (NumberFormatException e) {
            System.out.println(" 錯誤：數量與單價必須為合法的數字！");
        }
    }

    private static void updateQuantity() {
        if (cartList.isEmpty()) {
            System.out.println(" 目前購物車是空的，無法修改。");
            return;
        }

        System.out.print("請輸入要修改的商品代碼: ");
        String id = scanner.nextLine().trim();

        CartItem item = findItemById(id);

        if (item == null) {
            System.out.println(" 找不到代碼為「" + id + "」的商品。");
            return;
        }

        System.out.println("找到商品：「" + item.getName() + "」，目前數量: " + item.getQuantity());

        try {
            System.out.print("請輸入新的數量: ");
            int newQuantity = Integer.parseInt(scanner.nextLine().trim());

            if (newQuantity <= 0) {
                System.out.println(" 錯誤：更新失敗！數量必須大於 0（若要刪除商品請使用選項 3）。");
                return;
            }

            int oldQuantity = item.getQuantity();
            item.setQuantity(newQuantity);
            System.out.println(" 已將「" + item.getName() + "」的數量從 " + oldQuantity + " 修改為 " + newQuantity + "！");

        } catch (NumberFormatException e) {
            System.out.println(" 錯誤：數量必須為合法的整數！");
        }
    }

    // 3. 移除商品
    private static void removeItem() {
        if (cartList.isEmpty()) {
            System.out.println(" 目前購物車是空的，無法移除。");
            return;
        }

        System.out.print("請輸入要移除的商品代碼: ");
        String id = scanner.nextLine().trim();

        CartItem item = findItemById(id);

        if (item != null) {
            cartList.remove(item);
            System.out.println(" 成功從購物車移除商品：「" + item.getName() + "」 (代碼: " + id + ")！");
        } else {
            System.out.println(" 找不到代碼為「" + id + "」的商品，移除失敗。");
        }
    }

    // 4. 查看購物車與計算總額
    private static void listCartAndTotal() {
        if (cartList.isEmpty()) {
            System.out.println(" 目前購物車是空的。");
            return;
        }

        System.out.println(" 購物車清單 (共 " + cartList.size() + " 項商品):");
        double grandTotal = calculateTotal();

        for (int i = 0; i < cartList.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + cartList.get(i));
        }

        System.out.println("------------------------------------");
        System.out.printf(" 購物車總金額：$%.1f\n", grandTotal);
    }

    // 清空購物車
    private static void clearCart() {
        if (cartList.isEmpty()) {
            System.out.println(" 目前購物車已是空的。");
            return;
        }

        cartList.clear();
        System.out.println(" 已成功清空購物車！");
    }

    private static double calculateTotal() {
        double total = 0;
        for (CartItem item : cartList) {
            total += item.getSubtotal();
        }
        return total;
    }

    private static CartItem findItemById(String id) {
        for (CartItem item : cartList) {
            if (item.getId().equalsIgnoreCase(id)) {
                return item;
            }
        }
        return null;
    }
}