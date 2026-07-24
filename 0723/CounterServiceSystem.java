import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class CounterServiceSystem {

    static class Customer {
        private int number;
        private String name;

        public Customer(int number, String name) {
            this.number = number;
            this.name = name;
        }

        public int getNumber() {
            return number;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return String.format("號碼 [%03d] - %s", number, name);
        }
    }

    // 核心資料結構
    private Queue<Customer> waitingQueue;   
    private List<Customer> historyList;   
    private int nextNumber;             

    public CounterServiceSystem() {
        this.waitingQueue = new ArrayDeque<>();
        this.historyList = new ArrayList<>();
        this.nextNumber = 1;
    }

    public Customer takeNumber(String name) {
        Customer newCustomer = new Customer(nextNumber++, name);
        waitingQueue.offer(newCustomer);
        System.out.println("成功取號: " + newCustomer);
        return newCustomer;
    }

    public Customer callNext() {

        if (waitingQueue.isEmpty()) {
            System.out.println("叫號失敗：目前隊列中沒有等待的顧客。");
            return null;
        }

        Customer calledCustomer = waitingQueue.poll(); 
        historyList.add(calledCustomer);                
        System.out.println("請 " + calledCustomer + " 到櫃檯辦理！");
        return calledCustomer;
    }

    public void peekNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("下一位：目前無人等待。");
        } else {
            System.out.println("下一位等待顧客: " + waitingQueue.peek());
        }
    }

    public int getWaitingCount() {
        int count = waitingQueue.size();
        System.out.println("當前等待人數: " + count + " 人");
        return count;
    }

    public void printHistory() {
        System.out.println("\n--- 已服務紀錄明細 (共 " + historyList.size() + " 筆) ---");
        if (historyList.isEmpty()) {
            System.out.println("（尚無已服務的顧客）");
        } else {
            for (int i = 0; i < historyList.size(); i++) {
                System.out.println((i + 1) + ". " + historyList.get(i));
            }
        }
        System.out.println("------------------------------------");
    }

    public static void main(String[] args) {
        CounterServiceSystem system = new CounterServiceSystem();

        System.out.println("=== 櫃檯叫號服務系統測試 (CounterServiceSystem) ===\n");
        System.out.println("[測試 1] 空隊列操作安全性測試");
        system.peekNext();
        system.callNext(); 
        system.getWaitingCount();
        System.out.println("------------------------------------\n");

        // 測試 2: 顧客陸續取號
        System.out.println("[測試 2] 顧客取號");
        system.takeNumber("張小明");
        system.takeNumber("李美玲");
        system.takeNumber("陳大華");
        system.getWaitingCount();
        System.out.println("------------------------------------\n");

        System.out.println("[測試 3] 叫號服務測試");
        system.peekNext(); 
        system.callNext(); 
        system.getWaitingCount();
        System.out.println("------------------------------------\n");

        System.out.println("[測試 4] 新顧客加入與連續叫號");
        system.takeNumber("林志豪");
        system.callNext(); 
        system.callNext(); 
        system.getWaitingCount();
        System.out.println("------------------------------------\n");


        System.out.println("[測試 5] 消化剩餘顧客與過載叫號");
        system.callNext(); 
        system.getWaitingCount();
        system.callNext(); 
        System.out.println("------------------------------------\n");

        System.out.println("[測試 6] 檢視獨立保存的服務歷史紀錄");
        system.printHistory();
    }
}