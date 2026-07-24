public class TaskLinkedList {
    private TaskNode head;
    private int totalTasks;

    public TaskLinkedList() {
        this.head = null;
        this.totalTasks = 0;
    }

    public boolean containsId(String taskId) {
        TaskNode current = head;
        while (current != null) {
            if (current.taskId.equalsIgnoreCase(taskId)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean addUrgentTask(String taskId, String description) {
        if (containsId(taskId)) {
            System.out.println("新增失敗：工作代碼 [" + taskId + "] 已存在！");
            return false;
        }

        TaskNode newNode = new TaskNode(taskId, description);
        newNode.next = head;
        head = newNode;
        totalTasks++;
        System.out.println("[緊急工作新增至前端] " + newNode);
        return true;
    }

    public boolean addGeneralTask(String taskId, String description) {
        if (containsId(taskId)) {
            System.out.println("新增失敗：工作代碼 [" + taskId + "] 已存在！");
            return false;
        }

        TaskNode newNode = new TaskNode(taskId, description);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        totalTasks++;
        System.out.println("[一般工作新增至尾端] " + newNode);
        return true;
    }

    public boolean completeTask(String taskId) {
        if (head == null) {
            System.out.println("操作失敗：工作清單為空。");
            return false;
        }

        TaskNode current = head;
        while (current != null) {
            if (current.taskId.equalsIgnoreCase(taskId)) {
                if (current.isCompleted) {
                    System.out.println("提示：工作 [" + taskId + "] 原本就已是完成狀態。");
                } else {
                    current.isCompleted = true;
                    System.out.println("成功標記工作為已完成: " + current);
                }
                return true;
            }
            current = current.next;
        }

        System.out.println("操作失敗：找不到工作代碼 [" + taskId + "]。");
        return false;
    }

    public boolean removeTask(String taskId) {
        if (head == null) {
            System.out.println("刪除失敗：工作清單為空。");
            return false;
        }

        if (head.taskId.equalsIgnoreCase(taskId)) {
            System.out.println("成功刪除頭部工作: " + head);
            head = head.next;
            totalTasks--;
            return true;
        }

        TaskNode current = head;
        while (current.next != null && !current.next.taskId.equalsIgnoreCase(taskId)) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("刪除失敗：找不到工作代碼 [" + taskId + "]。");
            return false;
        }

        TaskNode target = current.next;
        System.out.println("成功刪除工作: " + target);
        current.next = target.next;
        totalTasks--;
        return true;
    }

    public int getPendingCount() {
        int pendingCount = 0;
        TaskNode current = head;
        while (current != null) {
            if (!current.isCompleted) {
                pendingCount++;
            }
            current = current.next;
        }
        return pendingCount;
    }

    public void printPendingTasks() {
        System.out.println("=== 待辦未完成工作清單 ===");
        if (head == null) {
            System.out.println("[ 清單為空 (Empty List) ]");
            printSummary();
            return;
        }

        TaskNode current = head;
        int count = 0;
        while (current != null) {
            if (!current.isCompleted) {
                count++;
                System.out.println("  " + count + ". " + current);
            }
            current = current.next;
        }

        if (count == 0) {
            System.out.println("  🎉太棒了！目前沒有未完成的工作！");
        }
        printSummary();
    }

    public void printAllTasks() {
        System.out.println("=== 完整工作清單 ===");
        if (head == null) {
            System.out.println("[ 清單為空 (Empty List) ]");
            printSummary();
            return;
        }

        TaskNode current = head;
        int index = 1;
        while (current != null) {
            System.out.println("  " + index + ". " + current);
            current = current.next;
            index++;
        }
        printSummary();
    }

    public void printSummary() {
        System.out.println("【統計】工作總數: " + totalTasks + " | 未完成數量: " + getPendingCount());
        System.out.println("--------------------------------------------------");
    }
}