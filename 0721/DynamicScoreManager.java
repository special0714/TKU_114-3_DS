import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DynamicScoreManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Double> scores = new ArrayList<>();

        System.out.println("=== 動態成績管理系統 ===");
        System.out.println("請開始輸入成績（範圍：0 ~ 100，輸入 -1 結束）：");

        while (true) {
            System.out.print("請輸入成績：");
            String input = scanner.nextLine().trim();

            try {
                double score = Double.parseDouble(input);

                if (score == -1) {
                    break;
                }

                if (score < 0 || score > 100) {
                    System.out.println(" 錯誤：成績必須介於 0 到 100 之間，請重新輸入！");
                    continue;
                }

                scores.add(score);

            } catch (NumberFormatException e) {
                System.out.println(" 錯誤：請輸入有效的數字格式！");
            }
        }

        System.out.println("\n------------------------------------");


        if (scores.isEmpty()) {
            System.out.println("沒有輸入任何有效成績。");
        } else {

            double avg = calculateAverage(scores);
            double max = findMax(scores);
            double min = findMin(scores);
            List<Double> passingScores = filterPassingScores(scores);

            System.out.println("=== 統計結果 ===");
            System.out.println("總筆數：" + scores.size() + " 筆");
            System.out.printf("平均分數：%.2f 分\n", avg);
            System.out.printf("最高分：%.1f 分\n", max);
            System.out.printf("最低分：%.1f 分\n", min);
            System.out.println("及格名單 (>= 60)：" + passingScores);
        }

        scanner.close();
    }

    public static double calculateAverage(List<Double> scores) {
        double sum = 0;
        for (double score : scores) {
            sum += score;
        }
        return sum / scores.size();
    }

    public static double findMax(List<Double> scores) {
        double max = scores.get(0);
        for (double score : scores) {
            if (score > max) {
                max = score;
            }
        }
        return max;
    }

    public static double findMin(List<Double> scores) {
        double min = scores.get(0);
        for (double score : scores) {
            if (score < min) {
                min = score;
            }
        }
        return min;
    }

    public static List<Double> filterPassingScores(List<Double> scores) {
        List<Double> passingList = new ArrayList<>();
        for (double score : scores) {
            if (score >= 60.0) {
                passingList.add(score);
            }
        }
        return passingList;
    }
}