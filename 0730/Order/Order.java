public class Order {
    private String id;
    private String customer;
    private int amount;

    public Order(String id, String customer, int amount) {
        this.id = id;
        this.customer = customer;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getCustomer() {
        return customer;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return id + " " + customer + " $" + amount;
    }
}