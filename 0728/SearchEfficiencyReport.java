public class SearchEfficiencyReport {

    // 紀錄搜尋結果的資料類別
    static class SearchResult {
        int index;     
        int comparisons;  

        SearchResult(int index, int comparisons) {
            this.index = index;
            this.comparisons = comparisons;
        }
    }

    public static SearchResult sequentialSearch(int[] data, int target) {
        int comparisons = 0;
        for (int i = 0; i < data.length; i++) {
            comparisons++;
            if (data[i] == target) {
                return new SearchResult(i, comparisons);
            }
        }
        return new SearchResult(-1, comparisons);
    }

    public static SearchResult binarySearch(int[] data, int target) {
        int comparisons = 0;
        int low = 0;
        int high = data.length - 1;

        while (low <= high) {
            comparisons++;
            int mid = low + (high - low) / 2;

            if (data[mid] == target) {
                return new SearchResult(mid, comparisons);
            } else if (data[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return new SearchResult(-1, comparisons);
    }

    public static void main(String[] args) {
        int[] sizes = {16, 128, 1024};

        System.out.println("==========================================================================================");
        System.out.println("                         搜尋演算法效率比較報告 (Search Efficiency Report)                ");
        System.out.println("==========================================================================================");

        for (int size : sizes) {
            int[] data = new int[size];
            for (int i = 0; i < size; i++) {
                data[i] = (i + 1) * 2; 
            }

            int firstItem = data[0];      
            int lastItem = data[size - 1];         
            int nonexistentItem = data[size - 1] + 99; 

            System.out.println("\n【 資料規模 N = " + size + " 】");
            System.out.printf("%-15s | %-20s | %-20s%n", "測試情境", "循序搜尋 (Seq) 比較次數", "二分搜尋 (Binary) 比較次數");
            System.out.println("------------------------------------------------------------------------------------------");

            SearchResult seqResult1 = sequentialSearch(data, firstItem);
            SearchResult binResult1 = binarySearch(data, firstItem);
            System.out.printf("%-17s | %-24d | %-24d%n", "第一筆資料 (" + firstItem + ")", seqResult1.comparisons, binResult1.comparisons);

            SearchResult seqResult2 = sequentialSearch(data, lastItem);
            SearchResult binResult2 = binarySearch(data, lastItem);
            System.out.printf("%-17s | %-24d | %-24d%n", "最後一筆資料 (" + lastItem + ")", seqResult2.comparisons, binResult2.comparisons);

            SearchResult seqResult3 = sequentialSearch(data, nonexistentItem);
            SearchResult binResult3 = binarySearch(data, nonexistentItem);
            System.out.printf("%-17s | %-24d | %-24d%n", "不存在資料 (" + nonexistentItem + ")", seqResult3.comparisons, binResult3.comparisons);
        }

        printObservations();
    }

    private static void printObservations() {
        System.out.println("\n==========================================================================================");
        System.out.println("                                      分析與觀察結果報告                                   ");
        System.out.println("==========================================================================================");
        System.out.println("1. 時間複雜度與最壞情況 (Worst-case) 比較：");
        System.out.println("   - 循序搜尋 (Sequential Search) 的時間複雜度為 O(N)。當搜尋「最後一筆」或「不存在資料」時，");
        System.out.println("     必須將陣列全部比對完畢，比較次數恰好等於資料量 N（如 N=1024 時需要 1024 次）。");
        System.out.println("   - 二分搜尋 (Binary Search) 的時間複雜度為 O(log2 N)。搜尋最壞情況下，比較次數不超過");
        System.out.println("     floor(log2 N) + 1。即便資料量擴大 64 倍（16 -> 1024），比較次數僅從 4-5 次微幅增加至 10-11 次。");
        System.out.println();
        System.out.println("2. 最佳情況 (Best-case) 差異：");
        System.out.println("   - 當目標正好是「第一筆資料」時，循序搜尋能在 1 次比較後立刻找到，表現優於二分搜尋。");
        System.out.println("   - 二分搜尋的第一筆比較始終鎖定在中央點 (mid)，因此即便目標在第一筆，仍需進行 log2 N 次縮小範圍。");
        System.out.println();
        System.out.println("3. 綜合結論：");
        System.out.println("   - 在資料量較大且已經排序的前提下，二分搜尋展現出極高的對數級（Logarithmic）搜尋效率。");
        System.out.println("   - 以「比較次數」作為評估指標比以「執行時間」更具客觀性與重現性，因為執行時間易受硬體效能、");
        System.out.println("     作業系統排程及背景程式干擾，而比較次數則是理論與演算法邏輯上的精確度量。");
        System.out.println("==========================================================================================");
    }
}