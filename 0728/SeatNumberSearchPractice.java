import java.util.Scanner;

public class SeatNumberSearchPractice {

    public static int binarySearch(int[] seats, int target) {
        int low = 0;
        int high = seats.length - 1;
        int round = 1;

        System.out.println("\n--- 開始二分搜尋追蹤 ---");
        System.out.printf("%-6s | %-6s | %-6s | %-6s | %-10s%n", "Round", "low", "mid", "high", "seats[mid]");
        System.out.println("--------------------------------------------------");

        while (low <= high) {
            int mid = low + (high - low) / 2;

            System.out.printf("第 %-2d 輪 | %-6d | %-6d | %-6d | %-10d%n", 
                              round++, low, mid, high, seats[mid]);

            if (seats[mid] == target) {
                return mid; 
            } else if (seats[mid] < target) {
                low = mid + 1; 
            } else {
                high = mid - 1; 
            }
        }

        return -1; 
    }

    public static void main(String[] args) {
        int[] seatNumbers = {101, 105, 108, 112, 115, 120, 125, 130, 138, 142, 150, 155};

        System.out.println("=== 目前系統內的已排序座位編號 ===");
        for (int i = 0; i < seatNumbers.length; i++) {
            System.out.printf("[%d]:%d ", i, seatNumbers[i]);
        }
        System.out.println("\n");

        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入要搜尋的座位編號：");
        int target = scanner.nextInt();

        int resultIndex = binarySearch(seatNumbers, target);

        System.out.println("--------------------------------------------------");
        if (resultIndex != -1) {
            System.out.println("搜尋成功！座位編號 " + target + " 位於索引 (Index) [" + resultIndex + "]。");
        } else {
            System.out.println("搜尋失敗！座位編號 " + target + " 不存在於列表中。");
        }

        scanner.close();
    }
}