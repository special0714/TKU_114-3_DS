import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

public class DeliveryProcessingSystem {

    private Queue<DeliveryTask> pendingQueue;   
    private Deque<DeliveryTask> completedStack; 
    private List<String> allLogs;              

    public DeliveryProcessingSystem() {
        this.pendingQueue = new ArrayDeque<>();
        this.completedStack = new ArrayDeque<>();
        this.allLogs = new ArrayList<>();
    }

    public void addTask(String taskId, String destination, int steps) {
        DeliveryTask task = new DeliveryTask(taskId, destination, steps);
        pendingQueue.offer(task);
        
        String log = "【新增任務】" + task;
        allLogs.add(log);
        System.out.println(log);
    }

    public DeliveryTask processNext() {

        if (pendingQueue.isEmpty()) {
            System.out.println("【處理提示】目前佇列中沒有待處理的任務。");
            return null;
        }

        DeliveryTask task = pendingQueue.poll();
        task.processStep();

        if (task.isCompleted()) {
            completedStack.push(task);
            String log = "【任務完成】" + task.getTaskId() + " - " + task.getDestination() + " 已全數處理完畢並歸檔。";
            allLogs.add(log);
            System.out.println(log);
        } else {
            pendingQueue.offer(task);
            String log = "【階段處理】" + task.getTaskId() + " 尚需處理，已重新排入等待佇列尾端。(剩餘: " + task.getRequiredSteps() + " 次)";
            allLogs.add(log);
            System.out.println(log);
        }

        return task;
    }

    public void peekNext() {
        if (pendingQueue.isEmpty()) {
            System.out.println("【預覽提示】下一個任務：目前無待處理任務。");
        } else {
            System.out.println("【下一個待處理任務】" + pendingQueue.peek());
        }
    }

    public void showRecentCompleted(int count) {
        System.out.println("\n--- 最近完成的 " + count + " 筆報表/任務 (LIFO 順序) ---");
        if (completedStack.isEmpty()) {
            System.out.println("(無已完成的任務)");
            System.out.println("------------------------------------");
            return;
        }

        int shown = 0;
        for (DeliveryTask task : completedStack) {
            System.out.println("  " + (shown + 1) + ". " + task.getTaskId() + " - " + task.getDestination());
            shown++;
            if (shown >= count) {
                break;
            }
        }
        System.out.println("------------------------------------");
    }

    public void printSummaryAndLogs() {
        System.out.println("\n====================================");
        System.out.println("        系統狀態與歷史日誌彙整");
        System.out.println("====================================");
        System.out.println("【當前等待數】: " + pendingQueue.size() + " 個");
        System.out.println("【已完成總數】: " + completedStack.size() + " 個");
        System.out.println("------------------------------------");
        System.out.println("【所有處理歷程 Log (" + allLogs.size() + " 條)】:");
        for (int i = 0; i < allLogs.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + allLogs.get(i));
        }
        System.out.println("====================================\n");
    }

    public static void main(String[] args) {
        DeliveryProcessingSystem system = new DeliveryProcessingSystem();

        System.out.println("=== 配送與報表處理系統測試 (DeliveryProcessingSystem) ===\n");

        System.out.println("[測試 1] 空結構叫號與預覽測試");
        system.peekNext();
        system.processNext();
        system.showRecentCompleted(3);
        System.out.println("------------------------------------\n");

        System.out.println("[測試 2] 新增任務");
        system.addTask("T-01", "台北分行日報", 1); 
        system.addTask("T-02", "台中門市季報", 2); 
        system.addTask("T-03", "高雄總部月報", 1); 
        System.out.println("------------------------------------\n");

        System.out.println("[測試 3] 執行處理與多階段重新排隊");
        system.peekNext(); 
        system.processNext(); 

        system.processNext(); 
        system.processNext(); 
        system.processNext(); 
        System.out.println("------------------------------------\n");

        System.out.println("[測試 4] 檢視最近完成的報表");
        system.showRecentCompleted(3);

        System.out.println("[測試 5] 空佇列防護測試");
        system.processNext();

        system.printSummaryAndLogs();
    }
}