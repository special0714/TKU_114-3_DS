public class PriceMaxMin {
    public static void main(String[] args) {
        int commodity1 = 100;
        int commodity2 = 200;
        int commodity3 = 300;

        int max = commodity1;
        int min = commodity1;

        if (commodity2 > max) {
            max = commodity2;
        }

        if (commodity3 > max) {
            max = commodity3;
        }

        if (commodity2 < min) {
            min = commodity2;
        }

        if (commodity3 < min) {
            min = commodity3;
        }

        System.out.println("Max: " + max);
        System.out.println("Min: " + min);
    }
}