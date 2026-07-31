import java.util.Arrays;
import java.util.Random;

public class AlgorithmComparisonReport {

    private static class Metric {
        long comparisons = 0;
        void reset() { comparisons = 0; }
    }

    public static void main(String[] args) {
        int[] sizes = {16, 128, 1024};
        Metric metric = new Metric();

        System.out.printf("%-10s | %-12s | %-16s | %-16s | %-16s%n", 
                "Data Size", "Data State", "Selection Sort", "Insertion Sort", "Merge Sort");
        System.out.println("----------------------------------------------------------------------------------");

        for (int size : sizes) {
            int[] sorted = generateSortedData(size);
            int[] reversed = generateReversedData(size);
            int[] random = generateRandomData(size, 42);

            testAndPrintRow(size, "Sorted", sorted, metric);
            testAndPrintRow(size, "Reversed", reversed, metric);
            testAndPrintRow(size, "Random", random, metric);
            System.out.println("----------------------------------------------------------------------------------");
        }

        generateConclusion(sizes);
    }

    private static void testAndPrintRow(int size, String stateName, int[] baseData, Metric metric) {
        int[] dataForSelection = Arrays.copyOf(baseData, baseData.length);
        int[] dataForInsertion = Arrays.copyOf(baseData, baseData.length);
        int[] dataForMerge = Arrays.copyOf(baseData, baseData.length);

        metric.reset();
        selectionSort(dataForSelection, metric);
        long selComp = metric.comparisons;

        metric.reset();
        insertionSort(dataForInsertion, metric);
        long insComp = metric.comparisons;

        metric.reset();
        mergeSort(dataForMerge, 0, dataForMerge.length - 1, metric);
        long mergeComp = metric.comparisons;

        System.out.printf("%-10d | %-12s | %-16d | %-16d | %-16d%n", 
                size, stateName, selComp, insComp, mergeComp);
    }

    private static void selectionSort(int[] arr, Metric metric) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                metric.comparisons++;
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    private static void insertionSort(int[] arr, Metric metric) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                metric.comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
    }

    private static void mergeSort(int[] arr, int left, int right, Metric metric) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid, metric);
        mergeSort(arr, mid + 1, right, metric);
        merge(arr, left, mid, right, metric);
    }

    private static void merge(int[] arr, int left, int mid, int right, Metric metric) {
        int[] leftArr = Arrays.copyOfRange(arr, left, mid + 1);
        int[] rightArr = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left;

        while (i < leftArr.length && j < rightArr.length) {
            metric.comparisons++;
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        while (i < leftArr.length) {
            arr[k++] = leftArr[i++];
        }
        while (j < rightArr.length) {
            arr[k++] = rightArr[j++];
        }
    }

    private static int[] generateSortedData(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    private static int[] generateReversedData(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = size - i;
        }
        return arr;
    }

    private static int[] generateRandomData(int size, long seed) {
        int[] arr = new int[size];
        Random rand = new Random(seed);
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(10000);
        }
        return arr;
    }

    private static void generateConclusion(int[] sizes) {
        System.out.println("\nOBSERVATIONS AND CONCLUSIONS");
        
        Metric metric = new Metric();
        for (int size : sizes) {
            int[] sorted = generateSortedData(size);
            int[] reversed = generateReversedData(size);
            int[] random = generateRandomData(size, 42);

            metric.reset();
            selectionSort(Arrays.copyOf(sorted, size), metric);
            long selSorted = metric.comparisons;

            metric.reset();
            selectionSort(Arrays.copyOf(reversed, size), metric);
            long selReversed = metric.comparisons;

            metric.reset();
            insertionSort(Arrays.copyOf(sorted, size), metric);
            long insSorted = metric.comparisons;

            metric.reset();
            insertionSort(Arrays.copyOf(reversed, size), metric);
            long insReversed = metric.comparisons;

            metric.reset();
            mergeSort(Arrays.copyOf(random, size), 0, size - 1, metric);
            long mergeRandom = metric.comparisons;

            System.out.println("Data Size " + size + " Analysis:");
            
            if (selSorted == selReversed) {
                System.out.println("  Selection Sort comparison count remains constant regardless of input state due to its O(N^2) complexity (" + selSorted + " comparisons).");
            }

            if (insSorted == size - 1) {
                System.out.println("  Insertion Sort performs exceptionally well on sorted data with O(N) comparisons (" + insSorted + " comparisons).");
            }

            if (insReversed > selReversed) {
                System.out.println("  Insertion Sort reaches its worst case O(N^2) on reversed data (" + insReversed + " comparisons).");
            }

            System.out.println("  Merge Sort maintains consistent O(N log N) growth on random data (" + mergeRandom + " comparisons).");
        }

        System.out.println("\nGeneral Conclusion:");
        System.out.println("Selection Sort comparison count depends strictly on data size N, calculated as N*(N-1)/2, regardless of initial array ordering.");
        System.out.println("Insertion Sort is highly sensitive to input state, operating at optimal O(N) for nearly-sorted data but degrading to O(N^2) for reversed data.");
        System.out.println("Merge Sort scales efficiently as N grows, significantly outperforming Selection Sort and Insertion Sort on large unstructured datasets.");
    }
}