public class Contact {
    private String id;      
    private String name;    
    private String phone;  
    private String email;    

    public Contact(String id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    // Getter & Setter
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("代碼: %-8s | 姓名: %-10s | 電話: %-12s | Email: %s", id, name, phone, email);
    }
}