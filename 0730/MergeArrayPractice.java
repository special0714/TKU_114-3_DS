import java.util.Arrays;

public class MergeArrayPractice {

    public static void main(String[] args) {
        System.out.println("=== 測試 1：標準測試（包含重複值、負數、長度不同）===");
        int[] arr1 = {-10, -5, 0, 5, 10, 15, 20}; 
        int[] arr2 = {-5, 0, 3, 10, 12};          
        testMerge(arr1, arr2);

        System.out.println("\n=== 測試 2：第一個陣列為空 ===");
        int[] empty1 = {};
        int[] arr3 = {-3, -1, 4, 8};
        testMerge(empty1, arr3);

        System.out.println("\n=== 測試 3：第二個陣列為空 ===");
        int[] arr4 = {-8, -2, 0, 9};
        int[] empty2 = {};
        testMerge(arr4, empty2);

        System.out.println("\n=== 測試 4：兩個陣列皆為空 ===");
        testMerge(new int[]{}, new int[]{});
    }

    private static void testMerge(int[] arr1, int[] arr2) {
        System.out.println("陣列 1: " + Arrays.toString(arr1));
        System.out.println("陣列 2: " + Arrays.toString(arr2));

        int[] result = mergeAndDeduplicate(arr1, arr2);

        System.out.println("合併去重結果: " + Arrays.toString(result));
        System.out.println("結果陣列長度: " + result.length);
    }

    public static int[] mergeAndDeduplicate(int[] arr1, int[] arr2) {
        if (arr1 == null) arr1 = new int[0];
        if (arr2 == null) arr2 = new int[0];

        int n1 = arr1.length;
        int n2 = arr2.length;

        int[] temp = new int[n1 + n2];

        int i = 0, j = 0, k = 0;

        while (i < n1 || j < n2) {
            int val;

            if (i < n1 && j < n2) {
                if (arr1[i] < arr2[j]) {
                    val = arr1[i++];
                } else if (arr2[j] < arr1[i]) {
                    val = arr2[j++];
                } else { 
                    val = arr1[i];
                    i++;
                    j++;
                }
            } else if (i < n1) { 
                val = arr1[i++];
            } else {           
                val = arr2[j++];
            }

            if (k == 0 || val > temp[k - 1]) {
                temp[k++] = val;
            }
        }

        int[] result = new int[k];
        for (int m = 0; m < k; m++) {
            result[m] = temp[m];
        }

        return result;
    }
}