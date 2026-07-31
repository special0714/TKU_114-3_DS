public class Order {
    private String orderId;    
    private String customerName; 
    private double amount;     
    private long timestamp;    

    public Order(String orderId, String customerName, double amount, long timestamp) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getAmount() {
        return amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId != null && orderId.equals(order.orderId);
    }

    @Override
    public int hashCode() {
        return orderId != null ? orderId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return String.format("訂單編號: %-6s | 顧客: %-8s | 金額: %8.2f | 時間: %d", 
                orderId, customerName, amount, timestamp);
    }
}