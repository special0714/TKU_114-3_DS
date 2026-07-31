public class ProductSortPractice {

    public static void main(String[] args) {
        Product[] products = {
            new Product("P001", "鍵盤", 1200, 15),
            new Product("P002", "滑鼠", 600, 30),
            new Product("P003", "螢幕", 4500, 8),
            new Product("P004", "耳機", 1500, 20),
            new Product("P005", "喇叭", 1200, 10), 
            new Product("P006", "網線", 150, 50),
            new Product("P007", "麥克風", 1500, 12), 
            new Product("P008", "鼠墊", 600, 40)   
        };

        System.out.println("=== 排序前商品列表 ===");
        printProducts(products);

        insertionSortByPrice(products);

        System.out.println("\n=== 排序後商品列表（依價格升冪，同價保持原順序）===");
        printProducts(products);
    }

    public static void insertionSortByPrice(Product[] products) {
        if (products == null || products.length <= 1) {
            return;
        }

        int n = products.length;

        for (int i = 1; i < n; i++) {
            Product key = products[i]; 
            int j = i - 1;

            while (j >= 0 && products[j].getPrice() > key.getPrice()) {
                products[j + 1] = products[j]; 
                j--;
            }

            products[j + 1] = key; 
        }
    }

    public static void printProducts(Product[] products) {
        System.out.println("---------------------------------------------------------");
        for (Product p : products) {
            System.out.println(p);
        }
        System.out.println("---------------------------------------------------------");
    }
}