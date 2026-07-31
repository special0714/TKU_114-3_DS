import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class RepairSchedulingSystem {

    private final List<RepairTask> allTasks = new ArrayList<>();      
    private final Queue<RepairTask> pendingQueue = new ArrayDeque<>(); 
    private final Deque<RepairTask> completedStack = new ArrayDeque<>();

    private final Set<String> registeredTaskIds = new HashSet<>();   
    private long globalSequenceCounter = 1;                        

    public boolean addTask(String taskId, String deviceName, int priority) {
        if (registeredTaskIds.contains(taskId)) {
            System.out.println("[錯誤] 工作編號重複，無法新增: " + taskId);
            return false;
        }

        RepairTask task = new RepairTask(taskId, deviceName, priority, globalSequenceCounter++);
        registeredTaskIds.add(taskId);
        allTasks.add(task);
        pendingQueue.offer(task);

        System.out.println("[成功登記] " + task);
        return true;
    }

    public RepairTask processNextTask() {
        if (pendingQueue.isEmpty()) {
            System.out.println("[提示] 目前等待佇列為空，無可執行的維修工作。");
            return null;
        }

        RepairTask task = pendingQueue.poll();
        completedStack.push(task); 
        System.out.println("[完成工作] 已完成工作: " + task.getTaskId() + " (已存入復原 Stack)");
        return task;
    }

    public RepairTask undoLastCompletedTask() {
        if (completedStack.isEmpty()) {
            System.out.println("[提示] 目前已完成 Stack 為空，無法執行復原動作。");
            return null;
        }

        RepairTask task = completedStack.pop();

        ((ArrayDeque<RepairTask>) pendingQueue).addFirst(task);
        System.out.println("↩[復原成功] 已將工作 " + task.getTaskId() + " 從 Stack 退回至等待 Queue 前端");
        return task;
    }

    public void sortAllTasksByPriorityDesc() {
        if (allTasks.isEmpty()) return;
        RepairAlgorithms.mergeSortByPriorityDesc(allTasks, 0, allTasks.size() - 1);
        System.out.println("[排序完成] 全域工作已依「優先等級降冪 (同等級維持登記順序)」排序。");
    }

    public RepairTask searchById(String taskId) {
        return RepairAlgorithms.searchByTaskId(allTasks, taskId);
    }

    public List<RepairTask> searchByDeviceName(String deviceName) {
        return RepairAlgorithms.searchByDeviceName(allTasks, deviceName);
    }

    public void displaySystemStatistics() {
        System.out.println("\n==========================================================================");
        System.out.println("                          【 維修系統狀態統計 】                          ");
        System.out.println("==========================================================================");
        System.out.printf("統計數據報告: [全部工作總數: %d 筆] | [等待維修 Queue: %d 筆] | [已完成 Stack: %d 筆]%n",
                allTasks.size(), pendingQueue.size(), completedStack.size());
        System.out.println("--------------------------------------------------------------------------");
        
        System.out.println("【1. 等待佇列 (Queue - 依序處理)】:");
        if (pendingQueue.isEmpty()) {
            System.out.println("   (無待處理工作)");
        } else {
            for (RepairTask t : pendingQueue) {
                System.out.println("   -> " + t);
            }
        }

        System.out.println("\n【2. 已完成堆疊 (Stack - 最上層可復原)】:");
        if (completedStack.isEmpty()) {
            System.out.println("   (無已完成工作)");
        } else {
            for (RepairTask t : completedStack) {
                System.out.println("   -> " + t);
            }
        }
        System.out.println("==========================================================================\n");
    }

    public static void main(String[] args) {
        RepairSchedulingSystem system = new RepairSchedulingSystem();

        System.out.println("==========================================================================");
        System.out.println("                        【 維修調度系統功能測試 】                        ");
        System.out.println("==========================================================================\n");

        System.out.println("=== 測試 1：登記維修工作 ===");
        system.addTask("TASK-101", "馬達 A",   3);
        system.addTask("TASK-102", "伺服器 B", 5); 
        system.addTask("TASK-103", "冷卻塔 C", 3); 
        system.addTask("TASK-104", "馬達 A",   1); 
        system.addTask("TASK-101", "重複測試", 5); 
        system.addTask("TASK-105", "控制板 D", 3); 

        system.displaySystemStatistics();

        System.out.println("=== 測試 2：工作處理與 Stack 復原 (Undo) 操作 ===");
        system.processNextTask(); 
        system.processNextTask(); 
        system.displaySystemStatistics();

        System.out.println("--- 執行 Undo 復原動作 ---");
        system.undoLastCompletedTask(); 
        system.displaySystemStatistics();

        System.out.println("=== 測試 3：Merge Sort 依優先等級降冪排序 ===");
        system.sortAllTasksByPriorityDesc();

        System.out.println("【排序後的全域工作列表 (ArrayList)】:");

        for (RepairTask task : system.allTasks) {
            System.out.println("  " + task);
        }

        System.out.println("\n=== 測試 4：工作搜尋測試 ===");
        
        String searchId = "TASK-103";
        System.out.println("搜尋工作編號: " + searchId);
        RepairTask tId = system.searchById(searchId);
        System.out.println(tId != null ? "  -> 找到工作: " + tId : "  查無此工作編號");

        String searchDevice = "馬達 A";
        System.out.println("\n搜尋設備名稱全部工作: \"" + searchDevice + "\"");
        List<RepairTask> deviceTasks = system.searchByDeviceName(searchDevice);
        for (RepairTask t : deviceTasks) {
            System.out.println("  -> " + t);
        }

        String missingDevice = "變壓器 X";
        System.out.println("\n搜尋設備名稱全部工作: \"" + missingDevice + "\"");
        List<RepairTask> emptyResult = system.searchByDeviceName(missingDevice);
        if (emptyResult.isEmpty()) {
            System.out.println("  查無設備 \"" + missingDevice + "\" 的維修紀錄。");
        }
    }
}