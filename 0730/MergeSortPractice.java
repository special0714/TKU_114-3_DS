import java.util.Arrays;

public class MergeSortPractice {

    public static void main(String[] args) {
        System.out.println("=== 測試 1：標準陣列 ===");
        int[] arr1 = {41, 12, 35, 8, 27, 19, 50, 3};
        testMergeSort(arr1);

        System.out.println("\n=== 測試 2：空陣列 ===");
        int[] arr2 = {};
        testMergeSort(arr2);

        System.out.println("\n=== 測試 3：單一元素陣列 ===");
        int[] arr3 = {42};
        testMergeSort(arr3);

        System.out.println("\n=== 測試 4：已排序陣列 ===");
        int[] arr4 = {3, 8, 12, 19, 27, 35, 41, 50};
        testMergeSort(arr4);

        System.out.println("\n=== 測試 5：反向排序陣列 ===");
        int[] arr5 = {50, 41, 35, 27, 19, 12, 8, 3};
        testMergeSort(arr5);
    }

    private static void testMergeSort(int[] arr) {
        System.out.println("排序前: " + Arrays.toString(arr));
        
        if (arr == null || arr.length <= 1) {
            System.out.println("陣列長度 <= 1，直接觸發停止條件，不需排序。");
            System.out.println("排序後: " + Arrays.toString(arr));
            return;
        }

        mergeSort(arr, 0, arr.length - 1);
        System.out.println("排序後: " + Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;

        System.out.printf("拆分範圍 [%d...%d] -> 左半部 [%d...%d], 右半部 [%d...%d]%n",
                left, right, left, mid, mid + 1, right);

        mergeSort(arr, left, mid);

        mergeSort(arr, mid + 1, right);

        merge(arr, left, mid, right);
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];

        int i = left;    
        int j = mid + 1; 
        int k = 0;      

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (int m = 0; m < temp.length; m++) {
            arr[left + m] = temp[m];
        }

        System.out.printf("合併完成 範圍 [%d...%d] -> %s%n",
                left, right, getSubArrayString(arr, left, right));
    }

    private static String getSubArrayString(int[] arr, int left, int right) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = left; i <= right; i++) {
            sb.append(arr[i]);
            if (i < right) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}