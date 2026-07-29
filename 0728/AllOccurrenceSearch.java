import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AllOccurrenceSearch {

    public static void main(String[] args) {
        int[] numbers = {15, 23, 8, 23, 42, 10, 23, 4, 15, 90};

        System.out.println("=== 目前陣列內容 ===");
        for (int i = 0; i < numbers.length; i++) {
            System.out.printf("[%d]:%d ", i, numbers[i]);
        }
        System.out.println("\n");

        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入要搜尋的整數：");
        int target = scanner.nextInt();

        List<Integer> foundIndices = new ArrayList<>();
        int compareCount = 0;

        for (int i = 0; i < numbers.length; i++) {
            compareCount++;
            if (numbers[i] == target) {
                foundIndices.add(i); 
            }
        }

        System.out.println("----------------------------------------");
        if (!foundIndices.isEmpty()) {
            System.out.println("搜尋成功！");
            System.out.println("找到的索引位置 (Indices)：" + foundIndices);
            System.out.println("總共出現次數：" + foundIndices.size() + " 次");
        } else {
            System.out.println("搜尋結果：找不到數值 " + target + "。");
            System.out.println("總共出現次數：0 次");
        }
        System.out.println("實際比較次數：" + compareCount + " 次");

        scanner.close();
    }
}