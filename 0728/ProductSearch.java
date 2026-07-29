public class ProductSearch {
    public static void main(String[] args) {
        Product[] products = {
            new Product("P103", "Keyboard", 1290),
            new Product("P205", "Mouse", 650),
            new Product("P118", "Monitor", 5200)
        };

        Product result = findById(products, "P205");
        if (result == null) {
            System.out.println("找不到商品");
        } else {
            System.out.println(result);
        }
    }

    public static Product findById(Product[] products, String targetId) {
        for (Product product : products) {
            if (product.getId().equals(targetId)) {
                return product;
            }
        }
        return null;
    }
}

class Product {
    private String id;
    private String name;
    private int price;

    public Product(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + " " + name + " $" + price;
    }
}