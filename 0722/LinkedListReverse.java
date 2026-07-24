class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedListReverse {

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

    public static Node reverse(Node head) {
        if (head == null) {
            System.out.println("訊息: 鏈結串列為空，無需反轉。");
            return null;
        }
        if (head.next == null) {
            System.out.println("訊息: 鏈結串列僅有一個節點，反轉後維持不變。");
            return head;
        }

        Node prev = null;
        Node current = head;
        Node nextTemp = null;

        while (current != null) {
            nextTemp = current.next; 
            current.next = prev;  
            prev = current;         
            current = nextTemp;     
        }

        return prev; 
    }

    public static Node buildList(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        Node head = new Node(values[0]);
        Node current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new Node(values[i]);
            current = current.next;
        }
        return head;
    }

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("  單向鏈結串列就地反轉 (Reverse) 測試");
        System.out.println("==========================================");

        System.out.println("\n--- 1. 測試空串列 ---");
        Node emptyHead = null;
        System.out.print("反轉前: ");
        printList(emptyHead);
        emptyHead = reverse(emptyHead);
        System.out.print("反轉後: ");
        printList(emptyHead);

        System.out.println("\n--- 2. 測試單一節點 ---");
        Node singleHead = buildList(new int[]{10});
        System.out.print("反轉前: ");
        printList(singleHead);
        singleHead = reverse(singleHead);
        System.out.print("反轉後: ");
        printList(singleHead);

        System.out.println("\n--- 3. 測試多節點串列 ---");
        Node multiHead = buildList(new int[]{10, 20, 30, 40});
        System.out.print("反轉前: ");
        printList(multiHead);
        multiHead = reverse(multiHead);
        System.out.print("反轉後: ");
        printList(multiHead);

        System.out.println("\n--- 4. 再次反轉回原始順序 ---");
        multiHead = reverse(multiHead);
        System.out.print("反轉後: ");
        printList(multiHead);
    }
}