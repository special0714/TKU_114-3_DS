public class Patient {

    private int number;       
    private String name;      
    private String department; 

    public Patient(int number, String name, String department) {
        this.number = number;
        this.name = name;
        this.department = department;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return String.format("[%s] 號碼: %03d | 姓名: %s", department, number, name);
    }
}