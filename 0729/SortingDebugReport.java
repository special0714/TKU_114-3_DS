import java.util.Arrays;

public class SortingDebugReport {

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("                  【 排序演算法 Bug 調試與修正報告 】                       ");
        System.out.println("==========================================================================");

        testBug1();

        testBug2();

        testBug3();
    }

    public static void selectionSortBug1(int[] arr) {
        int n = arr.length;
        for (int start = 0; start < n - 1; start++) {
            int minIdx = start;
            for (int j = 0; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr[start];
            arr[start] = arr[minIdx];
            arr[minIdx] = temp;
        }
    }

    public static void selectionSortFixed1(int[] arr) {
        int n = arr.length;
        for (int start = 0; start < n - 1; start++) {
            int minIdx = start;
            for (int j = start + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != start) {
                int temp = arr[start];
                arr[start] = arr[minIdx];
                arr[minIdx] = temp;
            }
        }
    }

    private static void testBug1() {
        System.out.println("\n--------------------------------------------------------------------------");
        System.out.println("【Bug 1：Selection Sort 內層迴圈範圍錯誤 (j 從 0 開始)】");
        int[] testDataBug = {42, 18, 35, 7, 29};
        int[] testDataFixed = testDataBug.clone();

        System.out.println("原始測試資料: " + Arrays.toString(testDataBug));

        selectionSortBug1(testDataBug);
        System.out.println("錯誤版本執行結果: " + Arrays.toString(testDataBug));

        selectionSortFixed1(testDataFixed);
        System.out.println("修正版本執行結果: " + Arrays.toString(testDataFixed));
    }

    public static void insertionSortBug2(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int j = i - 1;
            while (j >= 0 && arr[j] > arr[i]) {
                arr[j + 1] = arr[j]; 
                j--;
            }
            arr[j + 1] = arr[i]; 
        }
    }

    public static void insertionSortFixed2(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i]; 
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key; 
        }
    }

    private static void testBug2() {
        System.out.println("\n--------------------------------------------------------------------------");
        System.out.println("【Bug 2：Insertion Sort 未先保存 key (導致數值被覆蓋與遺失)】");
        int[] testDataBug = {30, 10, 20, 50, 40};
        int[] testDataFixed = testDataBug.clone();

        System.out.println("原始測試資料: " + Arrays.toString(testDataBug));

        insertionSortBug2(testDataBug);
        System.out.println("錯誤版本執行結果: " + Arrays.toString(testDataBug));

        insertionSortFixed2(testDataFixed);
        System.out.println("修正版本執行結果: " + Arrays.toString(testDataFixed));
    }

    public static void selectionSortBug3(int[] arr) {
        int n = arr.length;
        for (int start = 0; start < n - 1; start++) {
            int minIdx = start;
            for (int j = start + 1; j < n; j++) {
                if (arr[j] > arr[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr[start];
            arr[start] = arr[minIdx];
            arr[minIdx] = temp;
        }
    }

    public static void selectionSortFixed3(int[] arr) {
        int n = arr.length;
        for (int start = 0; start < n - 1; start++) {
            int minIdx = start;
            for (int j = start + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != start) {
                int temp = arr[start];
                arr[start] = arr[minIdx];
                arr[minIdx] = temp;
            }
        }
    }

    private static void testBug3() {
        System.out.println("\n--------------------------------------------------------------------------");
        System.out.println("【Bug 3：Selection Sort 比較方向錯誤 (升冪需求錯寫成降冪條件)】");
        int[] testDataBug = {15, 80, 23, 4, 67};
        int[] testDataFixed = testDataBug.clone();

        System.out.println("原始測試資料（要求升冪）: " + Arrays.toString(testDataBug));

        selectionSortBug3(testDataBug);
        System.out.println("錯誤版本執行結果: " + Arrays.toString(testDataBug));

        selectionSortFixed3(testDataFixed);
        System.out.println("修正版本執行結果: " + Arrays.toString(testDataFixed));
        System.out.println("--------------------------------------------------------------------------");
    }
}