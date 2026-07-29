import java.util.Scanner;

public class ProductIdSearchPractice {

    public static void main(String[] args) {
        int[] productIds = {105, 302, 101, 888, 450, 210, 666, 512};

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== 商品編號搜尋系統 ===");
        System.out.print("請輸入要搜尋的商品編號：");
        int targetId = scanner.nextInt();

        int foundIndex = -1;
        int compareCount = 0;

        for (int i = 0; i < productIds.length; i++) {
            compareCount++; 
            if (productIds[i] == targetId) {
                foundIndex = i; 
                break;         
            }
        }

        System.out.println("------------------------");
        if (foundIndex != -1) {
            System.out.println("找到商品！");
            System.out.println("商品編號：" + productIds[foundIndex]);
            System.out.println("索引位置 (Index)：" + foundIndex);
        } else {
            System.out.println("搜尋結果：找不到編號 " + targetId + " 的商品。");
        }
        System.out.println("實際比對次數：" + compareCount + " 次");

        scanner.close();
    }
}