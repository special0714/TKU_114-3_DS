public class GradeMethod {
    public static void main(String[] args) {
        int javaScore = 85;
        int englishScore = 90;
        int mathScore = 80;

        double average = calculateAverage(javaScore, englishScore, mathScore);
        String grade = getGrade(average);

        System.out.println("Average: " + average);
        System.out.println("Grade: " + grade);
    }

    public static double calculateAverage(int javaScore, int englishScore, int mathScore){
        return (javaScore + englishScore + mathScore) / 3.0;
    }

    public static String getGrade(double average){
        if (average < 60) {
            return "F";
        } else if (average < 70) {
            return "D";
        } else if (average < 80) {
            return "C";
        } else if (average < 90) {
            return "B";
        } else {
            return "A";
        }
    }
}