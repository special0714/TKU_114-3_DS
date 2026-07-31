public class RepairTask {
    private String taskId;       
    private String deviceName;   
    private int priority;        
    private long registrationSeq; 

    public RepairTask(String taskId, String deviceName, int priority, long registrationSeq) {
        this.taskId = taskId;
        this.deviceName = deviceName;
        this.priority = priority;
        this.registrationSeq = registrationSeq;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public int getPriority() {
        return priority;
    }

    public long getRegistrationSeq() {
        return registrationSeq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepairTask task = (RepairTask) o;
        return taskId != null && taskId.equals(task.taskId);
    }

    @Override
    public int hashCode() {
        return taskId != null ? taskId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return String.format("工作編號: %-6s | 設備名稱: %-12s | 優先等級: %d | 登記序號: %d", 
                taskId, deviceName, priority, registrationSeq);
    }
}