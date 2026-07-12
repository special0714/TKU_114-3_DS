import java.util.Scanner;

public class PersonalProfileApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入姓名：");
        String name = sc.nextLine();

        int age = readPositiveInt(sc, "請輸入年齡：");
        double height = readPositiveDouble(sc, "請輸入身高（公尺）：");
        double weight = readPositiveDouble(sc, "請輸入體重（公斤）：");

        boolean adult = isAdult(age);
        double bmi = calculateBmi(height, weight);
        String level = getBmiLevel(bmi);

        printReport(name, age, adult, height, weight, bmi, level);

        sc.close();
    }

    public static int readPositiveInt(Scanner sc, String message) {
        int value;
        while (true) {
            System.out.print(message);
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                if (value > 0) {
                    break; 
                }
            } else {
                sc.next(); 
            }
            System.out.println("輸入錯誤，年齡必須大於 0，請重新輸入。");
        }
        return value;
    }

    public static double readPositiveDouble(Scanner sc, String message) {
        double value;
        while (true) {
            System.out.print(message);
            if (sc.hasNextDouble()) {
                value = sc.nextDouble();
                if (value > 0) {
                    break; 
                }
            } else {
                sc.next();
            }
            System.out.println("輸入錯誤，數值必須大於 0，請重新輸入。");
        }
        return value;
    }

    public static double calculateBmi(double height, double weight) {
        return weight / (height * height);
    }

    public static String getBmiLevel(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 24) {
            return "Normal";
        } else if (bmi < 27) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public static boolean isAdult(int age) {
        return age >= 18;
    }

    public static void printReport(String name, int age, boolean adult, double height, double weight, double bmi, String level) {
        System.out.println();
        System.out.println("=== Personal Health Report ===");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Adult: " + adult);
        System.out.println("Height: " + height);
        System.out.println("Weight: " + weight);
        System.out.println("BMI: " + bmi);
        System.out.println("Level: " + level);
    }
}