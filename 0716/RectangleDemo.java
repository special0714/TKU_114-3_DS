public class RectangleDemo {
    public static void main(String[] args) {
        Rectangle rect1 = new Rectangle(5.0, 8.0); 
        Rectangle rect2 = new Rectangle(6.5, 6.5); 
        Rectangle rect3 = new Rectangle(-3.0, 4.0); 

        System.out.println("=== 矩形測試結果 ===\n");
        System.out.println(rect1);
        System.out.println("面積: " + rect1.calculateArea());
        System.out.println("周長: " + rect1.calculatePerimeter());
        System.out.println("是否為正方形: " + (rect1.isSquare() ? "是" : "否"));
        System.out.println("--------------------------------");
        System.out.println(rect2);
        System.out.println("面積: " + rect2.calculateArea());
        System.out.println("周長: " + rect2.calculatePerimeter());
        System.out.println("是否為正方形: " + (rect2.isSquare() ? "是" : "否"));
        System.out.println("--------------------------------");
        System.out.println(rect3);
        System.out.println("面積: " + rect3.calculateArea());
        System.out.println("周長: " + rect3.calculatePerimeter());
        System.out.println("是否為正方形: " + (rect3.isSquare() ? "是" : "否"));
        System.out.println("--------------------------------");
        System.out.println("嘗試將第一個矩形的寬度修改為 -2.5...");
        rect1.setWidth(-2.5);
        System.out.println("修改後的寬度（應該維持原判 5.0）: " + rect1.getWidth());
    }
}