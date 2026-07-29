public class Employee {
    private int id;       
    private String name;   
    private String department;
    private String extension; 

    public Employee(int id, String name, String department, String extension) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.extension = extension;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public String toString() {
        return String.format("編號: %-5d | 姓名: %-8s | 部門: %-10s | 分機: %s", 
                             id, name, department, extension);
    }
}