public class PricePrinter {
    public static void main(String[] args) {
        printPrice(10);
        printPrice(20);
    }

    public static void printItem(String itemName, double price) {
        System.out.println(itemName + ": $" + price);
    }

    public static void printPrice(double price) {
        System.out.println("Price: $" + price);
    }
}