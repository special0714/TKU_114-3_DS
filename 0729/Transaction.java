public class Transaction {
    private String id;        
    private String account;   
    private double amount;    
    private long timestamp;   
    public Transaction(String id, String account, double amount, long timestamp) {
        this.id = id;
        this.account = account;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public double getAmount() {
        return amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("交易編號: %-6s | 帳號: %-10s | 金額: %8.2f | 時間序號: %d", 
                id, account, amount, timestamp);
    }
}