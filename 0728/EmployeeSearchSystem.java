import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeeSearchSystem {

    public static Employee binarySearchById(Employee[] employees, int targetId) {
        if (employees == null || employees.length == 0) {
            return null;
        }

        int low = 0;
        int high = employees.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2; 

            if (employees[mid].getId() == targetId) {
                return employees[mid]; 
            } else if (employees[mid].getId() < targetId) {
                low = mid + 1; 
            } else {
                high = mid - 1; 
            }
        }

        return null; 
    }

    public static Employee[] processAndSortEmployees(Employee[] rawData) {
        if (rawData == null || rawData.length == 0) {
            return new Employee[0];
        }

        List<Employee> validList = new ArrayList<>();
        Set<Integer> seenIds = new HashSet<>();

        System.out.println("=== 系統資料載入與檢查 ===");
        for (Employee emp : rawData) {
            if (emp == null) continue;

            // 檢查重複編號處理
            if (seenIds.contains(emp.getId())) {
                System.out.println(" [警告] 發現重複的員工編號 (" + emp.getId() + " - " + emp.getName() + ")，已忽略該筆資料。");
            } else {
                seenIds.add(emp.getId());
                validList.add(emp);
            }
        }

        Employee[] sortedEmployees = validList.toArray(new Employee[0]);
        Arrays.sort(sortedEmployees, Comparator.comparingInt(Employee::getId));

        System.out.println(" 資料載入完成！共計 " + sortedEmployees.length + " 筆有效排序資料。\n");
        return sortedEmployees;
    }

    public static void main(String[] args) {
        Employee[] rawEmployees = {
            new Employee(1005, "張小明", "資訊部", "2301"),
            new Employee(1001, "李大華", "人事部", "1102"),
            new Employee(1008, "王美玲", "財務部", "1205"),
            new Employee(1003, "陳建國", "業務部", "3100"),
            new Employee(1005, "重複測試", "測試部", "0000"), 
            new Employee(1002, "林雅婷", "研發部", "2400")
        };

        Employee[] sortedEmployees = processAndSortEmployees(rawEmployees);

        System.out.println("=== 排序後的員工系統資料庫 ===");
        for (Employee emp : sortedEmployees) {
            System.out.println(emp);
        }
        System.out.println("------------------------------------------------------------------");

        System.out.println("\n=== 二分搜尋測試 ===");

        executeSearchTest(sortedEmployees, 1003, "正常搜尋已存在的員工");

        executeSearchTest(sortedEmployees, 9999, "搜尋不存在的員工編號");

        Employee[] emptyEmployees = new Employee[0];
        executeSearchTest(emptyEmployees, 1001, "搜尋空陣列資料庫");
    }

    private static void executeSearchTest(Employee[] database, int targetId, String testName) {
        System.out.println("【測試項目: " + testName + "】搜尋編號: " + targetId);
        
        Employee result = binarySearchById(database, targetId);

        if (result != null) {
            System.out.println(" 找到員工資料：");
            System.out.println("   " + result);
        } else {
            System.out.println(" 搜尋結果：找不到編號為 " + targetId + " 的員工資料（可能不存在或資料庫為空）。");
        }
        System.out.println("------------------------------------------------------------------");
    }
}