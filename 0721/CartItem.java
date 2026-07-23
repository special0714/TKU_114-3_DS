public class CartItem {
    private String id;      
    private String name;     
    private double price;     
    private int quantity;     

    public CartItem(String id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return String.format("代碼: %-8s | 名稱: %-12s | 單價: %-8.1f | 數量: %-4d | 小計: %.1f", 
                id, name, price, quantity, getSubtotal());
    }
}