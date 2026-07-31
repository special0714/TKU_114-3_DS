public class StoreProduct implements Cloneable {
    private String id;       
    private String name;    
    private int price;       
    private int stock;       

    public StoreProduct(String id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // Getter 方法
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
    public StoreProduct clone() {
        try {
            return (StoreProduct) super.clone();
        } catch (CloneNotSupportedException e) {
            return new StoreProduct(this.id, this.name, this.price, this.stock);
        }
    }

    @Override
    public String toString() {
        return String.format("編號: %-6s | 名稱: %-10s | 價格: %-6d | 庫存: %-4d", id, name, price, stock);
    }
}