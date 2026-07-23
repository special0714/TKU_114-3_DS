public class Equipment {
    private String id;    
    private String name;        
    private boolean available;   

    public Equipment(String id, String name) {
        this.id = id;
        this.name = name;
        this.available = true;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        String status = available ? "可借用" : "已借出";
        return String.format("代碼: %-8s | 名稱: %-15s | 狀態: %s", id, name, status);
    }
}