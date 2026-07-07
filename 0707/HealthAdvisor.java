import java.util.Scanner;

public class HealthAdvisor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String repeat = "y";

        while (repeat.equalsIgnoreCase("y")) {
            
            System.out.print("請輸入姓名：");
            String name = sc.next(); 

            System.out.print("請輸入身高（公尺）：");
            double height = sc.nextDouble();

            System.out.print("請輸入體重（公斤）：");
            double weight = sc.nextDouble();

            double bmi = weight / (height * height);

            String level;
            if (bmi < 18.5) {
                level = "Underweight";
            } else if (bmi < 24) {
                level = "Normal";
            } else if (bmi < 27) {
                level = "Overweight";
            } else {
                level = "Obese";
            }

            System.out.println("\n=== BMI Report ===");
            System.out.println("Name: " + name);
            System.out.println("BMI: " + bmi);
            System.out.println("Level: " + level);
            System.out.println();

            System.out.print("是否繼續輸入下一筆？(y/n)：");
            repeat = sc.next();
        }

        System.out.println("程式已結束，祝您身體健康！");
        sc.close();
    }
}