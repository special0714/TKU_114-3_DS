public class DeliveryTask {

    private String taskId;       
    private String destination;    
    private int requiredSteps;     
    public DeliveryTask(String taskId, String destination, int requiredSteps) {
        this.taskId = taskId;
        this.destination = destination;
        this.requiredSteps = Math.max(1, requiredSteps); 
    }

    public String getTaskId() {
        return taskId;
    }

    public String getDestination() {
        return destination;
    }

    public int getRequiredSteps() {
        return requiredSteps;
    }

    public void processStep() {
        if (this.requiredSteps > 0) {
            this.requiredSteps--;
        }
    }

    public boolean isCompleted() {
        return this.requiredSteps <= 0;
    }

    @Override
    public String toString() {
        return String.format("任務[%s] 目的地/報表: %s (剩餘階段: %d)", 
                             taskId, destination, requiredSteps);
    }
}