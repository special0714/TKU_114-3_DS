import java.util.Scanner;

public class WhileInputDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("請輸入一個整數 (輸入 0 結束): ");
        int input = scanner.nextInt();

        while (input != 0) {
            System.out.println("你輸入了: " + input);
            
            System.out.print("請輸入一個整數 (輸入 0 結束): ");
            input = scanner.nextInt();
        }

        System.out.println("偵測到輸入為 0，程式結束。");
        scanner.close();
    }
}