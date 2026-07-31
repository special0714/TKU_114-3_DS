public class ProductSortingSystem {

    public static void main(String[] args) {

        StoreProduct[] originalProducts = {
            new StoreProduct("P001", "電競鍵盤", 2500, 15),
            new StoreProduct("P002", "無線滑鼠", 1200, 45),
            new StoreProduct("P003", "4K螢幕", 8900, 8),
            new StoreProduct("P004", "藍牙耳機", 1200, 30),
            new StoreProduct("P005", "電腦喇叭", 1800, 12),
            new StoreProduct("P006", "高速網線", 250, 100),
            new StoreProduct("P007", "錄音麥克風", 3500, 15),
            new StoreProduct("P008", "RGB鼠墊", 600, 50),
            new StoreProduct("P009", "網頁鏡頭", 1800, 25),
            new StoreProduct("P010", "外接硬碟", 2500, 20)
        };

        System.out.println("=========================================================");
        System.out.println("                  【 原始商品資料 】                      ");
        System.out.println("=========================================================");
        printProducts(originalProducts);

        testSortMode(originalProducts, "PRICE_ASC", "價格", "升冪");

        testSortMode(originalProducts, "PRICE_DESC", "價格", "降冪");

        testSortMode(originalProducts, "STOCK_DESC", "庫存", "降冪");
    }

    private static void testSortMode(StoreProduct[] original, String mode, String fieldName, String direction) {

        StoreProduct[] workingCopy = copyProductArray(original);

        customSort(workingCopy, mode);

        System.out.println("\n=========================================================");
        System.out.printf("  排序欄位：%-4s | 排序方向：%-4s | 模式程式碼：%s%n", fieldName, direction, mode);
        System.out.println("=========================================================");
        printProducts(workingCopy);
    }

    public static void customSort(StoreProduct[] arr, String mode) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;

        for (int start = 0; start < n - 1; start++) {
            int selectedIdx = start;

            for (int j = start + 1; j < n; j++) {
                if (shouldReplace(arr[j], arr[selectedIdx], mode)) {
                    selectedIdx = j;
                }
            }

            if (selectedIdx != start) {
                StoreProduct temp = arr[start];
                arr[start] = arr[selectedIdx];
                arr[selectedIdx] = temp;
            }
        }
    }

    private static boolean shouldReplace(StoreProduct candidate, StoreProduct current, String mode) {
        switch (mode) {
            case "PRICE_ASC": 
                return candidate.getPrice() < current.getPrice();
            case "PRICE_DESC": 
                return candidate.getPrice() > current.getPrice();
            case "STOCK_DESC": 
                return candidate.getStock() > current.getStock();
            default:
                return false;
        }
    }

    public static StoreProduct[] copyProductArray(StoreProduct[] original) {
        StoreProduct[] copy = new StoreProduct[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].clone();
        }
        return copy;
    }

    public static void printProducts(StoreProduct[] products) {
        System.out.println("---------------------------------------------------------");
        for (StoreProduct p : products) {
            System.out.println(p);
        }
        System.out.println("---------------------------------------------------------");
    }
}