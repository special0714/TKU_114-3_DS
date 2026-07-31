import java.util.Random;

public class SortingExperiment {

    static class Metrics {
        long comparisons = 0; 
        long swaps = 0;       
        long shifts = 0;     
        public void reset() {
            comparisons = 0;
            swaps = 0;
            shifts = 0;
        }
    }

    public static void main(String[] args) {
        int dataSize = 10; 

        int[] sortedData = generateSortedData(dataSize);
        int[] reversedData = generateReversedData(dataSize);
        int[] randomData = generateRandomData(dataSize, 42); 

        System.out.println("==========================================================================");
        System.out.println("                        【 演算法排序實驗與統計 】                        ");
        System.out.println("==========================================================================");

        System.out.println("\n[ 1. Selection Sort (選擇排序) 測試結果 ]");
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("%-15s | %-12s | %-12s | %-12s%n", "資料類型", "比較次數", "交換次數", "移動(右移)次數");
        System.out.println("--------------------------------------------------------------------------");
        
        runSelectionSortExperiment("已排序 (Sorted)", sortedData);
        runSelectionSortExperiment("反向 (Reversed)", reversedData);
        runSelectionSortExperiment("隨機 (Random)", randomData);

        System.out.println("\n[ 2. Insertion Sort (插入排序) 測試結果 ]");
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("%-15s | %-12s | %-12s | %-12s%n", "資料類型", "比較次數", "交換次數", "移動(右移)次數");
        System.out.println("--------------------------------------------------------------------------");

        runInsertionSortExperiment("已排序 (Sorted)", sortedData);
        runInsertionSortExperiment("反向 (Reversed)", reversedData);
        runInsertionSortExperiment("隨機 (Random)", randomData);

        printConclusions(dataSize);
    }

    private static void runSelectionSortExperiment(String label, int[] originalData) {
        int[] arr = originalData.clone(); 
        Metrics m = new Metrics();

        selectionSort(arr, m);

        System.out.printf("%-15s | %-12d | %-12d | %-12s%n", label, m.comparisons, m.swaps, "-");
    }

    private static void runInsertionSortExperiment(String label, int[] originalData) {
        int[] arr = originalData.clone(); 
        Metrics m = new Metrics();

        insertionSort(arr, m);

        System.out.printf("%-15s | %-12d | %-12s | %-12d%n", label, m.comparisons, "-", m.shifts);
    }

    public static void selectionSort(int[] arr, Metrics m) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                m.comparisons++;
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
                m.swaps++;
            }
        }
    }

    public static void insertionSort(int[] arr, Metrics m) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0) {
                m.comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j]; 
                    m.shifts++;
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
    }

    private static int[] generateSortedData(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (i + 1) * 10;
        }
        return arr;
    }

    private static int[] generateReversedData(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (size - i) * 10;
        }
        return arr;
    }

    private static int[] generateRandomData(int size, long seed) {
        int[] arr = new int[size];
        Random rand = new Random(seed);
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(100) + 1;
        }
        return arr;
    }

    private static void printConclusions(int n) {
        long formulaComparisons = (long) n * (n - 1) / 2;

        System.out.println("\n==========================================================================");
        System.out.println("                           【 實驗觀察結論 】                             ");
        System.out.println("==========================================================================");
        System.out.println("1. Selection Sort (選擇排序)：");
        System.out.println("   - 比較次數：不論輸入資料的初始狀態如何，比較次數恆為固定值 N*(N-1)/2 (" + formulaComparisons + " 次)。");
        System.out.println("   - 交換次數：交換成本較低，最壞情況下最多交換 N-1 次。");
        System.out.println("   - 特性總結：適合「寫入/交換成本極高」的環境，效能不受輸入資料分佈影響。");

        System.out.println("\n2. Insertion Sort (插入排序)：");
        System.out.println("   - 已排序資料 (Best Case)：只需比較 N-1 (" + (n - 1) + ") 次，右移次數為 0 次，達到 O(N) 最佳效率。");
        System.out.println("   - 反向排序資料 (Worst Case)：比較與右移次數皆達到最高點 (N*(N-1)/2 = " + formulaComparisons + " 次)。");
        System.out.println("   - 隨機資料 (Average Case)：表現顯著優於 Selection Sort，平均只需處理少量的移動與比較。");
        System.out.println("   - 特性總結：對於「幾乎已排序」或「小規模」的資料，Insertion Sort 表現極度優異。");
        System.out.println("==========================================================================");
    }
}