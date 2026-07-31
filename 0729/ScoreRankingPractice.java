public class ScoreRankingPractice {

    public static void main(String[] args) {
        int[] scores = {85, 92, 58, 74, 92, 45, 85, 60};

        System.out.println("=== 原始成績列表 ===");
        printArray(scores);

        selectionSortDescending(scores);

        System.out.println("\n=== 排序與名次統計結果 ===");
        displayRankings(scores);
    }

    public static void selectionSortDescending(int[] arr) {
        int n = arr.length;

        for (int start = 0; start < n - 1; start++) {
            int maxIdx = start; 

            for (int j = start + 1; j < n; j++) {
                if (arr[j] > arr[maxIdx]) {
                    maxIdx = j;
                }
            }

            if (maxIdx != start) {
                int temp = arr[start];
                arr[start] = arr[maxIdx];
                arr[maxIdx] = temp;
            }
        }
    }

    public static void displayRankings(int[] sortedScores) {
        System.out.println("------------------------------------");
        System.out.printf("%-8s | %-8s | %-8s%n", "名次", "分數", "是否及格");
        System.out.println("------------------------------------");

        int currentRank = 1; 

        for (int i = 0; i < sortedScores.length; i++) {
            if (i > 0 && sortedScores[i] != sortedScores[i - 1]) {
                currentRank = i + 1;
            }

            int score = sortedScores[i];
            String passStatus = (score >= 60) ? "及格" : "不及格";

            System.out.printf("第 %-5d 名 | %-8d | %-8s%n", currentRank, score, passStatus);
        }
        System.out.println("------------------------------------");
    }

    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}