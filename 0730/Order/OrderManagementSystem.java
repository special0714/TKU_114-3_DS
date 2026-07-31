import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class OrderManagementSystem {
    public static void main(String[] args) {
        ArrayList<Order> allOrders = new ArrayList<>();
        Deque<Order> waiting = new ArrayDeque<>();
        Deque<Order> completed = new ArrayDeque<>();

        addOrder(allOrders, waiting,
            new Order("O205", "Amy", 1800));
        addOrder(allOrders, waiting,
            new Order("O101", "Ben", 650));
        addOrder(allOrders, waiting,
            new Order("O330", "Amy", 2400));
        addOrder(allOrders, waiting,
            new Order("O150", "Cara", 990));

        Order[] sorted = allOrders.toArray(new Order[0]);
        OrderAlgorithms.mergeSortById(sorted);

        System.out.println("依編號排序：");
        printOrders(sorted);

        int index = OrderAlgorithms.binarySearchById(
            sorted, "O150"
        );
        System.out.println("查詢 O150：" +
            (index == -1 ? "找不到" : sorted[index]));

        System.out.println("Amy 的訂單：");
        for (Order order :
             OrderAlgorithms.findByCustomer(allOrders, "Amy")) {
            System.out.println(order);
        }

        processNext(waiting, completed);
        processNext(waiting, completed);
        undoLast(waiting, completed);

        System.out.println("等待處理：" + waiting);
        System.out.println("完成紀錄：" + completed);
    }

    public static void addOrder(
        ArrayList<Order> allOrders,
        Deque<Order> waiting,
        Order order
    ) {
        allOrders.add(order);
        waiting.offer(order);
    }

    public static void processNext(
        Deque<Order> waiting,
        Deque<Order> completed
    ) {
        Order order = waiting.poll();
        if (order == null) {
            System.out.println("目前沒有待處理訂單");
            return;
        }
        completed.push(order);
        System.out.println("完成：" + order);
    }

    public static void undoLast(
        Deque<Order> waiting,
        Deque<Order> completed
    ) {
        Order order = completed.poll();
        if (order == null) {
            System.out.println("沒有可以復原的訂單");
            return;
        }
        waiting.offerFirst(order);
        System.out.println("復原：" + order);
    }

    public static void printOrders(Order[] orders) {
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}