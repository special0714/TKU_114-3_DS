class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class NumberHistoryList {

    private Node head;
    private int size;

    public NumberHistoryList() {
        this.head = null;
        this.size = 0;
    }

    public void addFirst(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        size++;
        System.out.println("[操作] 前端新增: " + value);
    }

    public void addLast(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        System.out.println("[操作] 尾端新增: " + value);
    }

    public boolean contains(int target) {
        Node current = head;
        while (current != null) {
            if (current.data == target) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean removeValue(int target) {
        if (head == null) {
            System.out.println("[操作] 刪除失敗 (" + target + "): 鏈結串列為空 (Empty List)");
            return false;
        }

        if (head.data == target) {
            head = head.next;
            size--;
            System.out.println("[操作] 刪除成功: " + target + " (原頭節點)");
            return true;
        }

        Node current = head;
        while (current.next != null && current.next.data != target) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("[操作] 刪除失敗: 找不到資料 " + target);
            return false;
        }

        current.next = current.next.next;
        size--;
        System.out.println("[操作] 刪除成功: " + target);
        return true;
    }

    public void printStatus() {
        System.out.print(" -> 串列內容: ");
        if (head == null) {
            System.out.println("[ 空串列 (Empty) ]");
            System.out.println(" -> 統計資訊: Size = 0, 總和 = 0, 最大值 = N/A, 最小值 = N/A");
            System.out.println("--------------------------------------------------");
            return;
        }

        Node current = head;
        int sum = 0;
        int max = head.data;
        int min = head.data;

        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" -> ");
            }

            sum += current.data;
            if (current.data > max) max = current.data;
            if (current.data < min) min = current.data;

            current = current.next;
        }
        System.out.println();
        System.out.println(" -> 統計資訊: Size = " + size + ", 總和 = " + sum + ", 最大值 = " + max + ", 最小值 = " + min);
        System.out.println("--------------------------------------------------");
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   NumberHistoryList 鏈結串列綜合操作與統計測試");
        System.out.println("==================================================\n");

        NumberHistoryList list = new NumberHistoryList();

        System.out.println("--- 初始空串列測試 ---");
        list.printStatus();

        System.out.println("--- 開始執行至少 8 次操作 ---");

        list.addFirst(30);
        list.printStatus();

        list.addFirst(10);
        list.printStatus();

        list.addLast(50);
        list.printStatus();

        list.addLast(20);
        list.printStatus();

        System.out.println("[操作] 搜尋 50: " + (list.contains(50) ? "找到" : "找不到"));
        System.out.println("[操作] 搜尋 99: " + (list.contains(99) ? "找到" : "找不到"));
        System.out.println("--------------------------------------------------");

        list.removeValue(10);
        list.printStatus();

        list.removeValue(50);
        list.printStatus();

        list.removeValue(99);
        list.printStatus();

        list.removeValue(20);
        list.printStatus();

        list.removeValue(30);
        list.printStatus();

        list.removeValue(100);
        list.printStatus();
    }
}