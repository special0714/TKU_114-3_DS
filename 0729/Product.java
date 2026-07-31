public class Product {
    private String id;      
    private String name;     
    private int price;       
    private int stock;       

    public Product(String id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return String.format("編號: %-6s | 名稱: %-10s | 價格: %-5d | 庫存: %-3d", id, name, price, stock);
    }
}