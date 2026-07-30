class Q11_Job {
    private String id;
    private String owner;
    private int priority;

    public Q11_Job(String id, String owner, int priority) {
        this.id = id;
        this.owner = owner;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return id + " " + owner + " priority=" + priority;
    }
}

public class Q11_JobSorter {

    public static void main(String[] args) {
        Q11_Job[] jobs = {
            new Q11_Job("J201", "Amy", 3),
            new Q11_Job("J105", "Ben", 5),
            new Q11_Job("J330", "Cara", 3),
            new Q11_Job("J118", "Dan", 5),
            new Q11_Job("J450", "Amy", 1)
        };

        mergeSortByPriority(jobs);
        for (Q11_Job job : jobs) {
            System.out.println(job);
        }
        System.out.println("搜尋 Amy：" + findFirstByOwner(jobs, "amy"));
    }

    public static void mergeSortByPriority(Q11_Job[] jobs) {
        if (jobs == null || jobs.length <= 1) {
            return;
        }
        Q11_Job[] temp = new Q11_Job[jobs.length];
        mergeSort(jobs, temp, 0, jobs.length - 1);
    }

    private static void mergeSort(
        Q11_Job[] jobs,
        Q11_Job[] temp,
        int left,
        int right
    ) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(jobs, temp, left, mid);        
            mergeSort(jobs, temp, mid + 1, right);    
            merge(jobs, temp, left, mid, right);     
        }
    }

    private static void merge(
        Q11_Job[] jobs,
        Q11_Job[] temp,
        int left,
        int mid,
        int right
    ) {
        int i = left;  
        int j = mid + 1; 
        int k = left;    

        // 進行比較與合併
        while (i <= mid && j <= right) {
            if (jobs[i].getPriority() >= jobs[j].getPriority()) {
                temp[k++] = jobs[i++];
            } else {
                temp[k++] = jobs[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = jobs[i++];
        }
        while (j <= right) {
            temp[k++] = jobs[j++];
        }

        for (int idx = left; idx <= right; idx++) {
            jobs[idx] = temp[idx];
        }
    }

    public static Q11_Job findFirstByOwner(
        Q11_Job[] jobs,
        String owner
    ) {
        if (jobs == null || owner == null) {
            return null;
        }
        for (Q11_Job job : jobs) {
            if (job != null && job.getOwner() != null &&
                job.getOwner().equalsIgnoreCase(owner)) {
                return job;
            }
        }
        return null;
    }
}