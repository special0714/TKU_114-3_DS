import java.util.ArrayList;
import java.util.List;

public class RegistrationAlgorithms {

    public static void mergeSortById(List<Registration> list, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSortById(list, left, mid);
        mergeSortById(list, mid + 1, right);
        merge(list, left, mid, right);
    }

    private static void merge(List<Registration> list, int left, int mid, int right) {
        List<Registration> temp = new ArrayList<>();
        int i = left;
        int j = mid + 1;

        while (i <= mid && j <= right) {
            if (list.get(i).getId().compareTo(list.get(j).getId()) <= 0) {
                temp.add(list.get(i++));
            } else {
                temp.add(list.get(j++));
            }
        }

        while (i <= mid) temp.add(list.get(i++));
        while (j <= right) temp.add(list.get(j++));

        for (int k = 0; k < temp.size(); k++) {
            list.set(left + k, temp.get(k));
        }
    }

    public static Registration binarySearchById(List<Registration> list, String targetId) {
        if (list == null || list.isEmpty() || targetId == null) return null;

        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = targetId.compareTo(list.get(mid).getId());

            if (cmp == 0) {
                return list.get(mid);
            } else if (cmp < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return null;
    }

    public static List<Registration> sequentialSearchByName(List<Registration> list, String name) {
        List<Registration> result = new ArrayList<>();
        if (list == null || name == null) return result;

        for (Registration reg : list) {
            if (reg.getName().equalsIgnoreCase(name)) {
                result.add(reg);
            }
        }
        return result;
    }
}