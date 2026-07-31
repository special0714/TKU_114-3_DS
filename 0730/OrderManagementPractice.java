import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class OrderManagementPractice {

    private final Set<String> registeredOrderIds = new HashSet<>(); 
    private final List<Order> masterOrders = new ArrayList<>();    
    private final Queue<Order> pendingQueue = new ArrayDeque<>();  
    private final Deque<Order> completedStack = new ArrayDeque<>(); 

    public boolean addOrder(Order order) {
        if (order == null) return false;

        if (registeredOrderIds.contains(order.getOrderId())) {
            System.out.println("[錯誤] 訂單編號重複，拒絕新增: " + order.getOrderId());
            return false;
        }

        registeredOrderIds.add(order.getOrderId());
        masterOrders.add(order);
        pendingQueue.offer(order);
        System.out.println("[成功] 訂單已新增至佇列: " + order.getOrderId() + " (顧客: " + order.getCustomerName() + ")");
        return true;
    }

    public Order peekNextPendingOrder() {
        if (pendingQueue.isEmpty()) {
            System.out.println("[提示] 目前沒有待處理的訂單 (Queue 為空)。");
            return null;
        }
        Order nextOrder = pendingQueue.peek();
        System.out.println("[下一筆待處理訂單]: " + nextOrder);
        return nextOrder;
    }

    public Order processNextOrder() {
        if (pendingQueue.isEmpty()) {
            System.out.println("[提示] 無法處理訂單：待處理 Queue 為空。");
            return null;
        }
        Order processed = pendingQueue.poll();
        completedStack.push(processed);
        System.out.println("[已處理訂單]: " + processed.getOrderId() + " -> 已移至完成 Stack");
        return processed;
    }

    public Order peekLastCompletedOrder() {
        if (completedStack.isEmpty()) {
            System.out.println("[提示] 目前沒有已完成的訂單 (Stack 為空)。");
            return null;
        }
        Order lastCompleted = completedStack.peek();
        System.out.println("[最後一筆已完成訂單]: " + lastCompleted);
        return lastCompleted;
    }

    public void sortMasterOrdersByAmountDesc() {
        if (masterOrders.isEmpty()) return;
        OrderAlgorithms.mergeSortByAmountDesc(masterOrders, 0, masterOrders.size() - 1);
    }

    public List<Order> searchCustomerOrders(String name) {
        return OrderAlgorithms.searchByCustomerName(masterOrders, name);
    }

    public void printMasterOrders() {
        System.out.println("--------------------------------------------------------------------------");
        for (Order o : masterOrders) {
            System.out.println(o);
        }
        System.out.println("--------------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        OrderManagementPractice system = new OrderManagementPractice();

        System.out.println("==========================================================================");
        System.out.println("                       【 訂單管理系統功能測試 】                         ");
        System.out.println("==========================================================================\n");

        System.out.println("=== 測試 1：空 Queue 與空 Stack 操作 ===");
        system.peekNextPendingOrder();
        system.processNextOrder();
        system.peekLastCompletedOrder();

        System.out.println("\n=== 測試 2：新增訂單與重複編號防止機制 ===");
        system.addOrder(new Order("ORD-01", "Alice",   1200.00, 1001L));
        system.addOrder(new Order("ORD-02", "Bob",     3500.50, 1002L));
        system.addOrder(new Order("ORD-03", "Alice",   3500.50, 1000L)); 
        system.addOrder(new Order("ORD-01", "Charlie", 9999.00, 1003L)); 
        system.addOrder(new Order("ORD-04", "David",    800.00, 1004L));

        System.out.println("\n=== 測試 3：顯示待處理訂單與處理流程 ===");
        system.peekNextPendingOrder(); 
        system.processNextOrder();    
        system.peekNextPendingOrder(); 
        system.peekLastCompletedOrder(); 

        System.out.println("\n=== 測試 4：主資料 Merge Sort (金額降冪) ===");
        System.out.println("【排序前主資料】");
        system.printMasterOrders();

        system.sortMasterOrdersByAmountDesc();

        System.out.println("【排序後主資料 (金額降冪 -> 時間升冪)】");
        system.printMasterOrders();

        System.out.println("\n=== 測試 5：依顧客姓名搜尋全部訂單 ===");
        
        String searchTarget1 = "Alice";
        System.out.println("搜尋顧客: \"" + searchTarget1 + "\"");
        List<Order> aliceOrders = system.searchCustomerOrders(searchTarget1);
        for (Order o : aliceOrders) {
            System.out.println("  -> " + o);
        }

        String searchTarget2 = "Eve";
        System.out.println("\n搜尋顧客: \"" + searchTarget2 + "\"");
        List<Order> eveOrders = system.searchCustomerOrders(searchTarget2);
        if (eveOrders.isEmpty()) {
            System.out.println("  查無顧客 \"" + searchTarget2 + "\" 的訂單紀錄。");
        }
    }
}