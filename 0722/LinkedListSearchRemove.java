class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedListSearchRemove {

    public static void printList(Node head) {
        if (head == null) {
            System.out.println("鏈結串列內容: [ 空串列 (Empty) ]");
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

    public static boolean contains(Node head, int target) {
        Node current = head;
        while (current != null) {
            if (current.data == target) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public static Node removeValue(Node head, int target) {
        // 情況 1: 空串列處理
        if (head == null) {
            System.out.println("刪除失敗: 鏈結串列為空 (Empty List)。");
            return null;
        }

        if (head.data == target) {
            System.out.println("成功刪除頭節點 (Head): " + target);
            return head.next; 
        }

        Node current = head;
        while (current.next != null && current.next.data != target) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("刪除失敗: 在鏈結串列中找不到資料 " + target);
            return head;
        }

        System.out.println("成功刪除節點: " + target);
        current.next = current.next.next;

        return head;
    }

    public static Node buildSampleList() {
        Node head = new Node(10);
        Node n2 = new Node(20);
        Node n3 = new Node(30);
        Node n4 = new Node(40);

        head.next = n2;
        n2.next = n3;
        n3.next = n4;

        return head;
    }

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("  單向鏈結串列搜尋 (contains) 與刪除 (removeValue) 測試");
        System.out.println("==========================================");

        Node head = buildSampleList();

        System.out.println("\n--- 初始鏈結串列 ---");
        printList(head);

        System.out.println("\n--- 1. 測試搜尋 (contains) ---");
        System.out.println("包含 30 嗎？ " + contains(head, 30));
        System.out.println("包含 99 嗎？ " + contains(head, 99));

        System.out.println("\n--- 2. 測試刪除 Head 節點 (10) ---");
        head = removeValue(head, 10);
        printList(head);

        System.out.println("\n--- 3. 測試刪除中間節點 (30) ---");
        head = removeValue(head, 30);
        printList(head);

        System.out.println("\n--- 4. 測試刪除最後一個節點 (40) ---");
        head = removeValue(head, 40);
        printList(head);

        System.out.println("\n--- 5. 測試刪除找不到的資料 (99) ---");
        head = removeValue(head, 99);
        printList(head);

        System.out.println("\n--- 6. 測試刪除唯一的最後節點 (20) 與空串列邊界 ---");
        head = removeValue(head, 20); 
        printList(head);

        System.out.println("\n嘗試在已清空的串列中繼續刪除:");
        head = removeValue(head, 10); 
        printList(head);
    }
}