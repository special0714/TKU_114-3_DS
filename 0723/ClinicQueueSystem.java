import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class ClinicQueueSystem {

    private Map<String, Queue<Patient>> departmentQueues;
    
    private Set<Integer> usedNumbers;
    
    private int totalServedCount;

    public ClinicQueueSystem() {
        this.departmentQueues = new HashMap<>();
        this.usedNumbers = new HashSet<>();
        this.totalServedCount = 0;
    }

    public boolean register(int number, String name, String department) {
        if (name == null || department == null || department.trim().isEmpty()) {
            System.out.println("【掛號失敗】患者姓名與科別不可為空。");
            return false;
        }

        if (usedNumbers.contains(number)) {
            System.out.println("【掛號失敗】號碼 [" + number + "] 已被使用，請更換號碼！");
            return false;
        }

        Patient patient = new Patient(number, name, department);
        
        departmentQueues.putIfAbsent(department, new ArrayDeque<>());
        departmentQueues.get(department).offer(patient);

        usedNumbers.add(number);

        System.out.println("【掛號成功】" + patient);
        return true;
    }

    public Patient callNext(String department) {
        Queue<Patient> queue = departmentQueues.get(department);

        
        if (queue == null || queue.isEmpty()) {
            System.out.println("【叫號提示】[" + department + "] 目前沒有等待看診的患者。");
            return null;
        }

        Patient patient = queue.poll(); 
        totalServedCount++;             
        System.out.println("【叫號看診】請 " + patient + " 至診間看診！");
        return patient;
    }

    public void peekNext(String department) {
        Queue<Patient> queue = departmentQueues.get(department);

        if (queue == null || queue.isEmpty()) {
            System.out.println("【查看提示】[" + department + "] 下一位：目前無人等待。");
        } else {
            System.out.println("【下一位看診】[" + department + "] -> " + queue.peek());
        }
    }

    public void printWaitingList(String department) {
        Queue<Patient> queue = departmentQueues.get(department);

        System.out.println("\n--- [" + department + "] 等待清單 ---");
        if (queue == null || queue.isEmpty()) {
            System.out.println("(無等待患者)");
        } else {
            int count = 1;
            for (Patient p : queue) {
                System.out.println("  " + (count++) + ". " + p);
            }
        }
        System.out.println("-------------------------");
    }

    public void printStatistics() {
        System.out.println("\n====================================");
        System.out.println("      診所營運統計報告");
        System.out.println("====================================");
        System.out.println("【各科別等待人數】");
        
        if (departmentQueues.isEmpty()) {
            System.out.println("  目前尚無任何科別掛號紀錄。");
        } else {
            for (Map.Entry<String, Queue<Patient>> entry : departmentQueues.entrySet()) {
                System.out.printf("  - %-6s 科 : %d 人等待\n", entry.getKey(), entry.getValue().size());
            }
        }
        
        System.out.println("------------------------------------");
        System.out.println("【服務總人數】: " + totalServedCount + " 人");
        System.out.println("====================================\n");
    }

    public static void main(String[] args) {
        ClinicQueueSystem system = new ClinicQueueSystem();

        System.out.println("=== 診所門診掛號叫號系統測試 (ClinicQueueSystem) ===\n");

        System.out.println("[測試 1] 空結構叫號與查看測試");
        system.callNext("內科");
        system.peekNext("內科");
        system.printStatistics();

        System.out.println("[測試 2] 患者掛號與號碼重複防護");
        system.register(101, "張小明", "內科");
        system.register(102, "李美玲", "內科");
        system.register(201, "陳大華", "外科");
        system.register(301, "林志豪", "眼科");
        
        system.register(101, "王小華", "外科"); 
        system.register(103, "王小華", "外科"); 
        System.out.println("------------------------------------\n");

        System.out.println("[測試 3] 查看下一位與等待清單");
        system.peekNext("內科");
        system.printWaitingList("內科");

        System.out.println("[測試 4] 跨科別叫號服務");
        system.callNext("內科");
        system.callNext("外科"); 
        system.peekNext("內科"); 

        System.out.println("\n[測試 5] 消化剩餘患者");
        system.callNext("內科"); 
        system.callNext("內科"); 

        system.printStatistics();
    }
}