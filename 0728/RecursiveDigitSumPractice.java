public class RecursiveDigitSumPractice {

    public static int digitSum(int number) {
        if (number < 10) {
            return number;
        }
        
        return (number % 10) + digitSum(number / 10);
    }

    public static void main(String[] args) {
        int[] testCases = {5729, 0, 8, 99999, 123456789};

        System.out.println("=== digitSum 遞迴測試結果 ===");
        for (int testNumber : testCases) {
            int result = digitSum(testNumber);
            System.out.println("digitSum(" + testNumber + ") = " + result);
        }
    }
}