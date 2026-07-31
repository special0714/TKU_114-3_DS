import java.util.ArrayList;
import java.util.List;

public class RepairAlgorithms {

    public static void mergeSortByPriorityDesc(List<RepairTask> tasks, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        mergeSortByPriorityDesc(tasks, left, mid);
        mergeSortByPriorityDesc(tasks, mid + 1, right);
        merge(tasks, left, mid, right);
    }

    private static void merge(List<RepairTask> tasks, int left, int mid, int right) {
        List<RepairTask> temp = new ArrayList<>();
        int i = left;
        int j = mid + 1;

        while (i <= mid && j <= right) {
            RepairTask t1 = tasks.get(i);
            RepairTask t2 = tasks.get(j);

            if (t1.getPriority() > t2.getPriority()) {
                temp.add(tasks.get(i++));
            } else if (t1.getPriority() < t2.getPriority()) {
                temp.add(tasks.get(j++));
            } else {
                if (t1.getRegistrationSeq() <= t2.getRegistrationSeq()) {
                    temp.add(tasks.get(i++));
                } else {
                    temp.add(tasks.get(j++));
                }
            }
        }

        while (i <= mid) temp.add(tasks.get(i++));
        while (j <= right) temp.add(tasks.get(j++));

        for (int m = 0; m < temp.size(); m++) {
            tasks.set(left + m, temp.get(m));
        }
    }

    public static RepairTask searchByTaskId(List<RepairTask> tasks, String taskId) {
        if (tasks == null || taskId == null) return null;
        for (RepairTask task : tasks) {
            if (task.getTaskId().equalsIgnoreCase(taskId)) {
                return task;
            }
        }
        return null;
    }

    public static List<RepairTask> searchByDeviceName(List<RepairTask> tasks, String deviceName) {
        List<RepairTask> result = new ArrayList<>();
        if (tasks == null || deviceName == null) return result;

        for (RepairTask task : tasks) {
            if (task.getDeviceName().equalsIgnoreCase(deviceName)) {
                result.add(task);
            }
        }
        return result;
    }
}