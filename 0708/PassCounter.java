public class PassCounter {
    public static void main(String[] args) {
        int score1 = 80;
        int score2 = 55;
        int score3 = 70;

        int passCount = 0;

        if (score1 >= 60) {
            passCount++;
        }

        if (score2 >= 60) {
            passCount++;
        }

        if (score3 >= 60) {
            passCount++;
        }

        System.out.println("Pass count: " + passCount);
    }
}