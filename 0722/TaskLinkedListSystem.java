public class TaskLinkedListSystem {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("        待辦工作管理系統 (Task LinkedList System)");
        System.out.println("==================================================\n");

        TaskLinkedList taskList = new TaskLinkedList();

        System.out.println("--- 1. 測試空串列狀態與邊界 ---");
        taskList.printAllTasks();
        taskList.completeTask("T001");
        taskList.removeTask("T001");
        System.out.println();

        System.out.println("--- 2. 測試一般工作加到尾端 (addGeneralTask) ---");
        taskList.addGeneralTask("T001", "撰寫專案企劃書");
        taskList.addGeneralTask("T002", "整理會議紀錄");
        taskList.addGeneralTask("T003", "發送每週報表");
        taskList.printAllTasks();

        System.out.println("--- 3. 測試緊急工作加到前端 (addUrgentTask) ---");
        taskList.addUrgentTask("T999", "修復伺服器崩潰 Bug [緊急]");
        taskList.addUrgentTask("T888", "回覆 VIP 客戶郵件 [緊急]");
        taskList.printAllTasks();

        System.out.println("--- 4. 測試工作代碼不重複 ---");
        taskList.addGeneralTask("T001", "重複代碼測試工作");
        System.out.println();

        System.out.println("--- 5. 測試完成工作 (completeTask) ---");
        taskList.completeTask("T888");
        taskList.completeTask("T002");
        System.out.println();
        taskList.printPendingTasks();

        System.out.println("--- 6. 測試刪除工作 (removeTask) ---");
        taskList.removeTask("T888");
        taskList.printAllTasks();

        taskList.removeTask("T001"); 
        taskList.printAllTasks();

        taskList.removeTask("T003"); 
        taskList.printAllTasks();

        taskList.removeTask("T555"); 
        System.out.println();

        System.out.println("--- 7. 標記剩餘工作完成並輸出統計 ---");
        taskList.completeTask("T999");
        taskList.printPendingTasks();

        System.out.println("--- 8. 刪除最後的工作 ---");
        taskList.removeTask("T999");
        taskList.printAllTasks();
    }
}