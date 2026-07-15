import java.util.Scanner;

public class SalesMatrix {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] sales = new int[3][4];

        System.out.println("=== 步驟 1: 請輸入商品銷售量 ===");
        inputSales(sc, sales);

        System.out.println("\n=== 步驟 2: 銷售量資料表格 ===");
        displayMatrix(sales);

        System.out.println("\n=== 步驟 3: 銷售統計結果 ===");
        printStatistics(sales);
        
        sc.close();
    }

    public static void inputSales(Scanner sc, int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                while (true) {
                    System.out.print("請輸入 商品 " + (row + 1) + " 在第 " + (col + 1) + " 天的銷售量: ");
                    if (sc.hasNextInt()) {
                        int val = sc.nextInt();
                        if (val >= 0) {
                            matrix[row][col] = val;
                            break; 
                        }
                        System.out.println("錯誤：銷售量不得小於 0，請重新輸入。");
                    } else {
                        System.out.println("錯誤：請輸入有效的整數數字！");
                        sc.next(); 
                    }
                }
            }
            System.out.println(); 
        }
    }

    public static void displayMatrix(int[][] matrix) {
        System.out.printf("%-8s\t%-6s\t%-6s\t%-6s\t%-6s\n", "", "第1天", "第2天", "第3天", "第4天");
        System.out.println("----------------------------------------------");
        
        for (int row = 0; row < matrix.length; row++) {
            System.out.printf("商品 %d\t", (row + 1));
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.printf("%-6d\t", matrix[row][col]);
            }
            System.out.println();
        }
    }

    public static void printStatistics(int[][] matrix) {
        int rows = matrix.length;    
        int cols = matrix[0].length; 
        
        int[] productTotals = new int[rows];
        int[] dayTotals = new int[cols];
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                productTotals[row] += matrix[row][col]; 
                dayTotals[col] += matrix[row][col];     
            }
        }
        
        System.out.println("[各商品總銷售量]");
        for (int i = 0; i < productTotals.length; i++) {
            System.out.println("商品 " + (i + 1) + " 總銷售量: " + productTotals[i]);
        }
        
        System.out.println("\n[每日全部商品總銷量]");
        for (int j = 0; j < dayTotals.length; j++) {
            System.out.println("第 " + (j + 1) + " 天總銷量: " + dayTotals[j]);
        }
        
        int maxSales = productTotals[0];
        int bestProductIndex = 0;
        for (int i = 1; i < productTotals.length; i++) {
            if (productTotals[i] > maxSales) {
                maxSales = productTotals[i];
                bestProductIndex = i;
            }
        }
        
        System.out.println("\n[銷售冠軍]");
        System.out.println("總銷售量最高的商品是：商品 " + (bestProductIndex + 1) + " (總銷售量: " + maxSales + ")");
    }
}