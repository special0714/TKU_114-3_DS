import java.util.Arrays;

public class RangeSearchSystem {

    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int firstIndex = findFirstOccurrence(nums, target);

        if (firstIndex == -1) {
            return new int[]{-1, -1};
        }

        int lastIndex = findLastOccurrence(nums, target);

        return new int[]{firstIndex, lastIndex};
    }

    private static int findFirstOccurrence(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                result = mid;     
                high = mid - 1;   
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    private static int findLastOccurrence(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                result = mid;    
                low = mid + 1;   
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] sortedData = {10, 20, 20, 20, 30, 40, 50, 50, 50, 50, 60, 70};

        System.out.println("=== 系統測試資料庫 (已排序含重複值) ===");
        for (int i = 0; i < sortedData.length; i++) {
            System.out.printf("[%d]:%d ", i, sortedData[i]);
        }
        System.out.println("\n------------------------------------------------------------------");

        runTestCase(sortedData, 20, "搜尋出現多次的資料");
        runTestCase(sortedData, 50, "搜尋出現多次的資料 (較長連續)");
        runTestCase(sortedData, 30, "搜尋僅出現一次的資料");
        runTestCase(sortedData, 99, "搜尋不存在的資料");
        runTestCase(new int[]{}, 20, "搜尋空陣列");
    }

    private static void runTestCase(int[] data, int target, String testName) {
        System.out.println("【測試項目: " + testName + "】搜尋目標值: " + target);

        int[] range = searchRange(data, target);

        if (range[0] != -1) {
            int count = range[1] - range[0] + 1; 
            System.out.println(" 搜尋成功！");
            System.out.println("   - 第一個出現位置 (First Index) : [" + range[0] + "]");
            System.out.println("   - 最後一個出現位置 (Last Index)  : [" + range[1] + "]");
            System.out.println("   - 索引範圍                     : " + Arrays.toString(range));
            System.out.println("   - 總共出現次數                 : " + count + " 次");
        } else {
            System.out.println(" 搜尋失敗！");
            System.out.println("   - 索引範圍結果                 : " + Arrays.toString(range));
            System.out.println("   - 訊息                         : 目標值 " + target + " 不存在於陣列中。");
        }
        System.out.println("------------------------------------------------------------------");
    }
}