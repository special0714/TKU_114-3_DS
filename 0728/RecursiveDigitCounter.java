public class RecursiveDigitCounter {

    public static int countDigit(int number, int target) {
        if (target < 0 || target > 9) {
            throw new IllegalArgumentException("target 必須介於 0 到 9 之間");
        }

        if (number == 0) {
            return (target == 0) ? 1 : 0;
        }

        return countDigitHelper(number, target);
    }

    private static int countDigitHelper(int number, int target) {
        if (number == 0) {
            return 0;
        }

        int currentMatch = (number % 10 == target) ? 1 : 0;

        return currentMatch + countDigitHelper(number / 10, target);
    }

    public static void main(String[] args) {
        System.out.println("=== countDigit 遞迴測試結果 ===");

        runTest(732313, 3);  
        runTest(123456, 9); 
        runTest(0, 0);        
        runTest(100203, 0);  
        runTest(88888, 8);  
        runTest(5, 5);       
    }

    private static void runTest(int number, int target) {
        int count = countDigit(number, target);
        System.out.printf("countDigit(%d, %d) -> 出現次數: %d%n", number, target, count);
    }
}