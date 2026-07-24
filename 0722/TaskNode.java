public class TaskNode {
    String taskId;
    String description;
    boolean isCompleted;
    TaskNode next;

    public TaskNode(String taskId, String description) {
        this.taskId = taskId;
        this.description = description;
        this.isCompleted = false;
        this.next = null;
    }

    @Override
    public String toString() {
        String status = isCompleted ? "[已完成]" : "[未完成]";
        return status + " ID: " + taskId + " | 說明: " + description;
    }
}