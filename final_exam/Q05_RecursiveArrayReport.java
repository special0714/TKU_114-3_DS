public class Q05_RecursiveArrayReport {

    public static void main(String[] args) {
        int[] data = {12, -3, 25, 8, 25, 40, 5};
        System.out.println("10～30 筆數：" + countInRange(data, 0, 10, 30));
        System.out.println("正數總和：" + sumPositive(data, 0));
        System.out.println("25 最後索引：" + findLast(data, 0, 25));
        System.out.println("99 最後索引：" + findLast(data, 0, 99));
    }

    public static int countInRange(
        int[] data,
        int index,
        int minimum,
        int maximum
    ) {
        if (index >= data.length) {
            return 0;
        }
        
        int currentCount = (data[index] >= minimum && data[index] <= maximum) ? 1 : 0;
        return currentCount + countInRange(data, index + 1, minimum, maximum);
    }

    public static int sumPositive(int[] data, int index) {
        if (index >= data.length) {
            return 0;
        }

        int currentValue = (data[index] > 0) ? data[index] : 0;
        return currentValue + sumPositive(data, index + 1);
    }

    public static int findLast(
        int[] data,
        int index,
        int target
    ) {

        if (index >= data.length) {
            return -1;
        }

        int lastInRest = findLast(data, index + 1, target);

        if (lastInRest != -1) {
            return lastInRest;
        }

        if (data[index] == target) {
            return index;
        }

        return -1;
    }
}