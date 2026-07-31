import java.util.ArrayList;
import java.util.List;

public class OrderAlgorithms {

    public static void mergeSortByAmountDesc(List<Order> orders, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSortByAmountDesc(orders, left, mid);
        mergeSortByAmountDesc(orders, mid + 1, right);
        merge(orders, left, mid, right);
    }

    private static void merge(List<Order> orders, int left, int mid, int right) {
        List<Order> temp = new ArrayList<>();
        int i = left;
        int j = mid + 1;

        while (i <= mid && j <= right) {
            Order o1 = orders.get(i);
            Order o2 = orders.get(j);

            if (shouldBeBefore(o1, o2)) {
                temp.add(o1);
                i++;
            } else {
                temp.add(o2);
                j++;
            }
        }

        while (i <= mid) {
            temp.add(orders.get(i++));
        }

        while (j <= right) {
            temp.add(orders.get(j++));
        }

        for (int m = 0; m < temp.size(); m++) {
            orders.set(left + m, temp.get(m));
        }
    }

    private static boolean shouldBeBefore(Order o1, Order o2) {
        if (o1.getAmount() > o2.getAmount()) {
            return true;
        } else if (o1.getAmount() == o2.getAmount()) {
            return o1.getTimestamp() <= o2.getTimestamp();
        }
        return false;
    }

    public static List<Order> searchByCustomerName(List<Order> orders, String customerName) {
        List<Order> result = new ArrayList<>();
        if (orders == null || customerName == null) return result;

        for (Order order : orders) {
            if (order.getCustomerName().equalsIgnoreCase(customerName)) {
                result.add(order);
            }
        }
        return result;
    }
}