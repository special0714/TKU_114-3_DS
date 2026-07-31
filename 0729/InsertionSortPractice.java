import java.util.Arrays;

public class InsertionSortPractice {

    public static void main(String[] args) {
        System.out.println("=== 測試 1：標準陣列 ===");
        int[] arr1 = {30, 10, 20, 50, 40, 5};
        insertionSort(arr1);

        System.out.println("\n=== 測試 2：已排序陣列 (Best Case) ===");
        int[] arr2 = {5, 10, 20, 30, 40, 50};
        insertionSort(arr2);

        System.out.println("\n=== 測試 3：反向排序陣列 (Worst Case) ===");
        int[] arr3 = {50, 40, 30, 20, 10, 5};
        insertionSort(arr3);

        printExplanation();
    }

    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            System.out.println("陣列為空或長度 <= 1，不需要排序。");
            return;
        }

        int n = arr.length;
        System.out.println("初始陣列: " + Arrays.toString(arr));

        int comparisons = 0; 
        int shifts = 0;      

        for (int i = 1; i < n; i++) {
            int key = arr[i]; 
            int j = i - 1;

            while (j >= 0) {
                comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j]; 
                    shifts++;
                    j--;
                } else {
                    break;
                }
            }

            arr[j + 1] = key;

            System.out.printf("第 %d 輪 -> key: %2d, 插入位置: %d, 陣列內容: %s%n",
                    i, key, j + 1, Arrays.toString(arr));
        }

        System.out.println("排序完成結果: " + Arrays.toString(arr));
        System.out.printf("統計結果 -> 總比較次數: %d, 總右移次數: %d%n", comparisons, shifts);
    }

    public static void printExplanation() {
        System.out.println("\n==============================================");
        System.out.println("【測試資料移動次數分析說明】");
        System.out.println("1. 移動（右移）次數最多的資料為：『反向排序陣列』。");
        System.out.println("2. 原因解析：");
        System.out.println("   - 在【反向排序】( Worst Case ) 的情況下，每一次取出的 key 都比左邊所有已排序的元素還要小。");
        System.out.println("   - 因此， key 必須一路向左比較到索引 0，左側的每個元素都需要向右移動一格。");
        System.out.println("   - 長度為 N 的反向陣列，其移動總次數為 1 + 2 + ... + (N-1) = N(N-1)/2 次（以 N=6 為例為 15 次）。");
        System.out.println("   - 相對地，【已排序陣列】( Best Case ) 每次只需比較 1 次且不需要任何移動，移動次數為 0 次。");
        System.out.println("==============================================");
    }
}