public class RecursiveNameSearchPractice {

    public static int search(String[] names, String target, int index) {
        if (names == null || index >= names.length) {
            return -1;
        }

        if (names[index] != null && names[index].equals(target)) {
            return index;
        }

        return search(names, target, index + 1);
    }

    public static void main(String[] args) {
        String[] nameList = {"Alice", "Bob", "Charlie", "David", "Eve"};
        
        String[] emptyList = {};

        System.out.println("=== RecursiveNameSearchPractice 遞迴測試結果 ===");

        int testFirst = search(nameList, "Alice", 0);
        System.out.println("測試第一筆資料 (\"Alice\")    -> 索引結果: " + testFirst);

        int testLast = search(nameList, "Eve", 0);
        System.out.println("測試最後一筆資料 (\"Eve\")      -> 索引結果: " + testLast);

        int testNotFound = search(nameList, "Frank", 0);
        System.out.println("測試不存在的資料 (\"Frank\")   -> 索引結果: " + testNotFound);

        int testEmpty = search(emptyList, "Alice", 0);
        System.out.println("測試空陣列              -> 索引結果: " + testEmpty);
    }
}