import java.util.Scanner;

public class ArrayStatistics {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count = readCount(sc);

        int[] scores = new int[count];

        inputScores(sc, scores);

        System.out.println("\n--- 成績統計結果 ---");
        System.out.print("全部成績為: ");
        for (int i = 0; i < scores.length; i++) {
            System.out.print(scores[i] + (i == scores.length - 1 ? "" : ", "));
        }
        System.out.println();

        int total = calculateTotal(scores);
        double average = (double) total / count;
        System.out.println("總分: " + total);
        System.out.printf("平均: %.2f\n", average);

        int max = findMax(scores);
        int min = findMin(scores);
        System.out.println("最高分: " + max);
        System.out.println("最低分: " + min);

        int passCount = countPass(scores);
        int failCount = count - passCount;
        System.out.println("及格人數: " + passCount);
        System.out.println("不及格人數: " + failCount);

        System.out.print("\n請輸入要搜尋的目標成績: ");
        while (!sc.hasNextInt()) {
            System.out.print("請輸入有效的整數成績: ");
            sc.next();
        }
        int target = sc.nextInt();

        int index = findIndex(scores, target);
        if (index != -1) {
            System.out.println("成績 " + target + " 第一次出現的索引位置（Index）為: " + index);
        } else {
            System.out.println("找不到成績為 " + target + " 的資料。");
        }

        sc.close();
    }

    public static int readCount(Scanner sc) {
        int count = 0;
        while (true) {
            System.out.print("請輸入資料筆數 (1-50): ");
            if (sc.hasNextInt()) {
                count = sc.nextInt();
                if (count >= 1 && count <= 50) {
                    break;
                } else {
                    System.out.println("錯誤：筆數超出範圍，請重新輸入。");
                }
            } else {
                System.out.println("錯誤：請輸入有效的整數。");
                sc.next(); 
            }
        }
        return count;
    }

    public static void inputScores(Scanner sc, int[] scores) {
        for (int i = 0; i < scores.length; i++) {
            while (true) {
                System.out.print("請輸入第 " + (i + 1) + " 筆成績 (0-100): ");
                if (sc.hasNextInt()) {
                    int score = sc.nextInt();
                    if (score >= 0 && score <= 100) {
                        scores[i] = score;
                        break; 
                    } else {
                        System.out.println("錯誤：成績必須在 0 到 100 之間！");
                    }
                } else {
                    System.out.println("錯誤：請輸入有效的整數。");
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
    }
    }