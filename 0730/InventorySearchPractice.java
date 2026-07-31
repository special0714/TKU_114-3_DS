import java.util.Arrays;

public class InventorySearchPractice {

    public static void main(String[] args) {
        String[] inventory = {
            "INV-808", "INV-101", "INV-505", "INV-202", 
            "INV-909", "INV-303", "INV-707", "INV-404", 
            "INV-606", "INV-000", "INV-111", "INV-999"
        };

        System.out.println("==========================================================================");
        System.out.println("                        【 庫存編號排序與搜尋系統 】                      ");
        System.out.println("==========================================================================");
        
        System.out.println("排序前庫存編號: " + Arrays.toString(inventory));

        mergeSort(inventory, 0, inventory.length - 1);

        System.out.println("排序後庫存編號: " + Arrays.toString(inventory));
        System.out.println("==========================================================================\n");

        System.out.println("--- 開始進行 Binary Search 測試 ---");

        testBinarySearch(inventory, "INV-000", "邊界案例：第一筆資料");

        testBinarySearch(inventory, "INV-999", "邊界案例：最後一筆資料");

        testBinarySearch(inventory, "INV-505", "一般案例：中間資料");

        testBinarySearch(inventory, "INV-888", "例外案例：不存在的編號");

        testBinarySearch(inventory, "INV-0000", "例外案例：界外不存在編號");
    }

    private static void testBinarySearch(String[] arr, String target, String testCaseName) {
        int index = binarySearch(arr, target);
        System.out.printf("[%s]%n", testCaseName);
        System.out.printf("搜尋目標: %-10s | 搜尋結果索引 (Index): %d", target, index);
        if (index != -1) {
            System.out.printf(" (對應元素: %s)%n", arr[index]);
        } else {
            System.out.println(" (狀態: 查無此庫存編號)");
        }
        System.out.println("--------------------------------------------------------------------------");
    }

    public static void mergeSort(String[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(String[] arr, int left, int mid, int right) {
        String[] temp = new String[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            if (arr[i].compareTo(arr[j]) <= 0) {
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
    }

    public static int binarySearch(String[] arr, String target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = target.compareTo(arr[mid]);

            if (cmp == 0) {
                return mid; 
            } else if (cmp < 0) {
                high = mid - 1; 
            } else {
                low = mid + 1; 
            }
        }

        return -1; 
    }
}