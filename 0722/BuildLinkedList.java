class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class BuildLinkedList {

    public static void printList(Node head) {
        if (head == null) {
            System.out.println("鏈結串列為空 (Empty List)");
            return;
        }

        System.out.print("鏈結串列內容: ");
        Node current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    public static int getCount(Node head) {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public static int getSum(Node head) {
        int sum = 0;
        Node current = head;
        while (current != null) {
            sum += current.data;
            current = current.next;
        }
        return sum;
    }

    public static boolean search(Node head, int target) {
        if (head == null) {
            System.out.println("搜尋失敗：鏈結串列為空。");
            return false;
        }

        Node current = head;
        int index = 0;
        while (current != null) {
            if (current.data == target) {
                System.out.println("找到資料 " + target + "，位於索引點 (Index): " + index);
                return true;
            }
            current = current.next;
            index++;
        }
        
        System.out.println("找不到資料: " + target);
        return false;
    }

    public static void main(String[] args) {
        System.out.println("=== 建立與操作 Single Linked List ===\n");

        Node head = new Node(10);
        Node second = new Node(20);
        Node third = new Node(30);
        Node fourth = new Node(40);

        head.next = second;
        second.next = third;
        third.next = fourth;

        printList(head);

        int totalNodes = getCount(head);
        int totalSum = getSum(head);

        System.out.println("節點總數量: " + totalNodes);
        System.out.println("數值總和: " + totalSum);
        System.out.println();

        System.out.println("--- 測試資料搜尋 ---");
        search(head, 30); 
        search(head, 50); 
        System.out.println();

        System.out.println("--- 測試空串列 (Empty List) ---");
        Node emptyHead = null;
        printList(emptyHead);
        System.out.println("空串列節點數量: " + getCount(emptyHead));
        System.out.println("空串列數值總和: " + getSum(emptyHead));
        search(emptyHead, 10);
    }
}