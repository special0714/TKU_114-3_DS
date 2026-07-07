import java.util.Scanner;

public class ScoreMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入姓名：");
        String name = sc.next();

        System.out.print("請輸入 Java 成績：");
        int javaScore = sc.nextInt();

        System.out.print("請輸入 English 成績：");
        int englishScore = sc.nextInt();

        System.out.print("請輸入 Math 成績：");
        int mathScore = sc.nextInt();

        double average = (javaScore + englishScore + mathScore) / 3.0;

        String status = (average >= 60) ? "及格" : "不及格";

        char grade;
        if (average >= 90) {
            grade = 'A';
        } else if (average >= 80) {
            grade = 'B';
        } else if (average >= 70) {
            grade = 'C';
        } else if (average >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        int choice = -1;
        while (choice != 0) {
            System.out.println("\n=== 成績查詢選單 ===");
            System.out.println("1：顯示平均分數");
            System.out.println("2：顯示及格狀態");
            System.out.println("3：顯示等第");
            System.out.println("0：離開");
            System.out.print("請輸入選項：");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n[查詢結果] " + name + " 的平均分數為：" + average);
                    break;
                case 2:
                    System.out.println("\n[查詢結果] " + name + " 的及格狀態為：" + status);
                    break;
                case 3:
                    System.out.println("\n[查詢結果] " + name + " 的總體等第為：" + grade);
                    break;
                case 0:
                    System.out.println("\n程式已結束，謝謝使用！");
                    break;
                default:
                    System.out.println("\n[錯誤] 無此選項，請重新輸入。");
                    break;
            }
        }

        sc.close();
    }
}