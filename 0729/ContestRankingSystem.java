public class ContestRankingSystem {

    public static void main(String[] args) {
        Contestant[] contestants = {
            new Contestant("C001", "張三", 85, 120.5),
            new Contestant("C002", "李四", 95, 110.0),
            new Contestant("C003", "王五", 85, 98.2),   
            new Contestant("C004", "趙六", 95, 105.4), 
            new Contestant("C005", "孫七", 70, 150.0),
            new Contestant("C006", "周八", 85, 98.2),   
            new Contestant("C007", "吳九", 90, 115.0)
        };

        System.out.println("=== 排序前比賽資料 ===");
        printContestants(contestants);

        insertionSortContestants(contestants);

        System.out.println("\n=== 比賽最終排名榜 ===");
        displayRankings(contestants);
    }

    public static void insertionSortContestants(Contestant[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;

        for (int i = 1; i < n; i++) {
            Contestant key = arr[i]; 
            int j = i - 1;

            while (j >= 0 && shouldSwap(arr[j], key)) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    private static boolean shouldSwap(Contestant prior, Contestant current) {
        if (prior.getScore() < current.getScore()) {
            return true;
        } else if (prior.getScore() == current.getScore()) {
            return prior.getSeconds() > current.getSeconds();
        }
        return false;
    }

    public static void displayRankings(Contestant[] sortedArr) {
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-6s | %s%n", "名次", "參賽者完整資料");
        System.out.println("------------------------------------------------------------------");

        int currentRank = 1;

        for (int i = 0; i < sortedArr.length; i++) {
            if (i > 0) {
                Contestant prev = sortedArr[i - 1];
                Contestant curr = sortedArr[i];

                if (curr.getScore() != prev.getScore() || curr.getSeconds() != prev.getSeconds()) {
                    currentRank = i + 1;
                }
            }

            System.out.printf("第 %-3d 名 | %s%n", currentRank, sortedArr[i]);
        }
        System.out.println("------------------------------------------------------------------");
    }

    public static void printContestants(Contestant[] arr) {
        System.out.println("------------------------------------------------------------------");
        for (Contestant c : arr) {
            System.out.println(c);
        }
        System.out.println("------------------------------------------------------------------");
    }
}