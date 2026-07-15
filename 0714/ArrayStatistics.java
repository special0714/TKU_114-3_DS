import java.util.Scanner;

public class ArrayStatistics {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== 步驟 1: 輸入資料筆數 ===");
        int count = readCount(sc);

        int[] scores = new int[count];

        System.out.println("\n=== 步驟 2: 輸入成績 ===");
        inputScores(sc, scores);

        System.out.println("\n=== 步驟 3: 成績統計結果 ===");
        System.out.print("全部成績為：");
        for (int i = 0; i < scores.length; i++) {
            System.out.print(scores[i] + (i == scores.length - 1 ? "" : ", "));
        }
        System.out.println();

        int total = calculateTotal(scores);
        double average = (double) total / scores.length;
        int max = findMax(scores);
        int min = findMin(scores);

        System.out.println("總分：" + total);
        System.out.printf("平均：%.2f\n", average);
        System.out.println("最高分：" + max);
        System.out.println("最低分：" + min);

        int passCount = countPass(scores);
        int failCount = scores.length - passCount;
        System.out.println("及格人數 (>= 60分)：" + passCount + " 人");
        System.out.println("不及格人數 (< 60分)：" + failCount + " 人");

        System.out.println("\n=== 步驟 4: 搜尋成績 ===");
        System.out.print("請輸入欲搜尋的目標成績 (0-100)：");
        int target = -1;
        while (true) {
            if (sc.hasNextInt()) {
                target = sc.nextInt();
                if (target >= 0 && target <= 100) {
                    break;
                }
            } else {
                sc.next(); 
            }
            System.out.print("請輸入有效的搜尋成績 (0 到 100 之間的整數)：");
        }

        int index = findIndex(scores, target);
        if (index != -1) {
            System.out.println("找到成績 " + target + "，第一次出現的索引位置（Index）為：" + index);
        } else {
            System.out.println("找不到成績 " + target + "。");
        }

        sc.close();
        System.out.println("\n程式執行結束。");
    }

    public static int readCount(Scanner sc) {
        int count = 0;
        while (true) {
            System.out.print("請輸入資料筆數 (1 ~ 50)：");
            if (sc.hasNextInt()) {
                count = sc.nextInt();
                if (count >= 1 && count <= 50) {
                    break;
                }
                System.out.println("錯誤：筆數必須在 1 到 50 之間！");
            } else {
                System.out.println("錯誤：請輸入整數數字！");
                sc.next(); 
            }
        }
        return count;
    }

    public static void inputScores(Scanner sc, int[] scores) {
        for (int i = 0; i < scores.length; i++) {
            while (true) {
                System.out.print("請輸入第 " + (i + 1) + " 筆成績 (0 ~ 100)：");
                if (sc.hasNextInt()) {
                    int score = sc.nextInt();
                    if (score >= 0 && score <= 100) {
                        scores[i] = score;
                        break;
                    }
                    System.out.println("錯誤：成績必須在 0 到 100 之間！");
                } else {
                    System.out.println("錯誤：請輸入整數數字！");
                    sc.next(); 
                }
            }
        }
    }

    public static int calculateTotal(int[] scores) {
        int total = 0;
        for (int score : scores) {
            total += score;
        }
        return total;
    }

    public static int findMax(int[] scores) {
        int max = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > max) {
                max = scores[i];
            }
        }
        return max;
    }

    public static int findMin(int[] scores) {
        int min = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] < min) {
                min = scores[i];
            }
        }
        return min;
    }

    public static int countPass(int[] scores) {
        int count = 0;
        for (int score : scores) {
            if (score >= 60) {
                count++;
            }
        }
        return count;
    }

    public static int findIndex(int[] scores, int target) {
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == target) {
                return i;
            }
        }
        return -1;
    }
}