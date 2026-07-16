public class Q09_MatrixReport {
    public static void main(String[] args) {
        int[][] data = {
            {5, 8, 2},
            {9, 4, 7},
            {3, 6, 10}
        };
        System.out.println("第 1 列總和：" + rowSum(data, 1));
        System.out.println("第 2 欄總和：" + columnSum(data, 2));
        System.out.println("大於等於 7 的筆數：" + countAtLeast(data, 7));
        System.out.println("總和最大的列索引：" + findRowWithLargestTotal(data));
    }

    public static int rowSum(int[][] data, int row) {

        if (data == null || data.length == 0 || row < 0 || row >= data.length) {
            return -1;
        }
        
        int sum = 0;
        for (int val : data[row]) {
            sum += val;
        }
        return sum;
    }

    public static int columnSum(int[][] data, int column) {
        if (data == null || data.length == 0 || column < 0) {
            return -1;
        }
        
        boolean validColumn = false;
        int sum = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null && column < data[i].length) {
                sum += data[i][column];
                validColumn = true; 
            }
        }
        
        return validColumn ? sum : -1;
    }

    public static int countAtLeast(int[][] data, int target) {
        if (data == null || data.length == 0) {
            return 0;
        }
        
        int count = 0;
        for (int[] row : data) {
            if (row != null) {
                for (int val : row) {
                    if (val >= target) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static int findRowWithLargestTotal(int[][] data) {
        if (data == null || data.length == 0) {
            return -1;
        }

        int maxIndex = -1;
        int maxSum = Integer.MIN_VALUE;
        boolean hasValidRow = false;

        for (int i = 0; i < data.length; i++) {
            if (data[i] == null) {
                continue;
            }
            
            hasValidRow = true;
            int currentSum = 0;
            for (int val : data[i]) {
                currentSum += val;
            }
            
            if (maxIndex == -1 || currentSum > maxSum) {
                maxSum = currentSum;
                maxIndex = i;
            }
        }

        return hasValidRow ? maxIndex : -1;
    }
}