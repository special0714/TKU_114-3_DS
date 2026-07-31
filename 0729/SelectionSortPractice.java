import java.util.Arrays;

public class SelectionSortPractice {

    public static void main(String[] args) {
        System.out.println("=== 測試 1：標準陣列排序 ===");
        int[] arr1 = {42, 18, 35, 7, 29, 14};
        selectionSort(arr1);

        System.out.println("\n=== 測試 2：空陣列 ===");
        int[] arr2 = {};
        selectionSort(arr2);

        System.out.println("\n=== 測試 3：單一元素陣列 ===");
        int[] arr3 = {42};
        selectionSort(arr3);
    }

    public static void selectionSort(int[] arr) {
        if (arr == null) {
            System.out.println("陣列為 null，無法排序。");
            return;
        }

        int n = arr.length;
        System.out.println("初始陣列: " + Arrays.toString(arr));

        if (n <= 1) {
            System.out.println("陣列長度 <= 1，不需要進行排序。");
            System.out.println("總比較次數: 0, 實際交換次數: 0");
            return;
        }

        int comparisons = 0; 
        int swaps = 0;      
        for (int start = 0; start < n - 1; start++) {
            int minIdx = start; 

            for (int j = start + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            if (minIdx != start) {
                int temp = arr[start];
                arr[start] = arr[minIdx];
                arr[minIdx] = temp;
                swaps++;
            }

            System.out.printf("第 %d 輪 -> start: %d, 選中的最小值索引: %d, 陣列內容: %s%n",
                    start + 1, start, minIdx, Arrays.toString(arr));
        }

        System.out.println("\n排序完成結果: " + Arrays.toString(arr));
        System.out.printf("統計結果 -> 總比較次數: %d, 實際交換次數: %d%n", comparisons, swaps);
    }
}