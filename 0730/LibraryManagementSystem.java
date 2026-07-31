import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LibraryManagementSystem {

    private final List<Book> bookList = new ArrayList<>();  
    private final Set<String> registeredIds = new HashSet<>(); 
    private boolean isSortedById = false;                  

    public boolean addBook(Book book) {
        if (book == null) return false;

        if (registeredIds.contains(book.getId())) {
            System.out.println("[錯誤] 書籍編號重複，拒絕新增: " + book.getId() + " (" + book.getTitle() + ")");
            return false;
        }

        registeredIds.add(book.getId());
        bookList.add(book);
        isSortedById = false; 
        System.out.println("[成功] 書籍已加入館藏: " + book.getId() + " - " + book.getTitle());
        return true;
    }

    public void sortByIdAsc() {
        if (bookList.isEmpty()) {
            System.out.println("[提示] 館藏為空，無法執行排序。");
            return;
        }
        BookAlgorithms.mergeSortByIdAsc(bookList, 0, bookList.size() - 1);
        isSortedById = true;
        System.out.println("[排序完成] 館藏已依「書籍編號升冪」排序。");
    }

    public void sortByBorrowCountDesc() {
        if (bookList.isEmpty()) {
            System.out.println("[提示] 館藏為空，無法執行排序。");
            return;
        }
        BookAlgorithms.mergeSortByBorrowCountDesc(bookList, 0, bookList.size() - 1);
        isSortedById = false; 
        System.out.println("[排序完成] 館藏已依「借閱次數降冪」排序。");
    }

    public Book searchById(String id) {
        if (bookList.isEmpty()) {
            System.out.println("[提示] 館藏為空，無法執行 Binary Search。");
            return null;
        }

        if (!isSortedById) {
            sortByIdAsc();
        }

        return BookAlgorithms.binarySearchById(bookList, id);
    }

    public List<Book> searchByCategory(String category) {
        if (bookList.isEmpty()) {
            System.out.println("[提示] 館藏為空，無法執行 Sequential Search。");
            return new ArrayList<>();
        }
        return BookAlgorithms.sequentialSearchByCategory(bookList, category);
    }

    public void printBooks() {
        if (bookList.isEmpty()) {
            System.out.println("目前館藏清單為空。");
            return;
        }
        System.out.println("--------------------------------------------------------------------------");
        for (Book b : bookList) {
            System.out.println(b);
        }
        System.out.println("--------------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();

        System.out.println("==========================================================================");
        System.out.println("                        【 圖書管理系統功能測試 】                        ");
        System.out.println("==========================================================================\n");

        System.out.println("=== 測試 1：空館藏測試 ===");
        library.sortByIdAsc();
        library.searchById("BK-101");
        library.searchByCategory("資訊科學");

        System.out.println("\n=== 測試 2：新增書籍與重複編號防止機制 ===");
        library.addBook(new Book("BK-303", "Java 程式設計", "資訊科學", 150));
        library.addBook(new Book("BK-101", "演算法圖解",   "資訊科學", 320));
        library.addBook(new Book("BK-505", "心理學導論",   "社會科學", 80));
        library.addBook(new Book("BK-202", "資料結構實作", "資訊科學", 210));
        library.addBook(new Book("BK-101", "重複編號測試", "文學",     50)); 
        library.addBook(new Book("BK-404", "被討厭的勇氣", "心理勵志", 450));

        System.out.println("\n【原始館藏列表】");
        library.printBooks();

        System.out.println("=== 測試 3：Merge Sort (編號升冪) 與 Binary Search ===");
        library.sortByIdAsc();
        library.printBooks();

        String targetId1 = "BK-202";
        System.out.println("🔍 Binary Search 查詢編號: " + targetId1);
        Book b1 = library.searchById(targetId1);
        System.out.println(b1 != null ? "  -> 找到書籍: " + b1 : "  查無此書籍");

        String targetId2 = "BK-999";
        System.out.println("\n🔍 Binary Search 查詢編號: " + targetId2);
        Book b2 = library.searchById(targetId2);
        System.out.println(b2 != null ? "  -> 找到書籍: " + b2 : "  查無此書籍");

        System.out.println("\n=== 測試 4：Merge Sort (依借閱次數降冪) ===");
        library.sortByBorrowCountDesc();
        library.printBooks();

        System.out.println("=== 測試 5：Sequential Search 依分類搜尋全部書籍 ===");
        
        String cat1 = "資訊科學";
        System.out.println("🔍 Sequential Search 查詢分類: \"" + cat1 + "\"");
        List<Book> catList1 = library.searchByCategory(cat1);
        for (Book b : catList1) {
            System.out.println("  -> " + b);
        }

        String cat2 = "藝術設計";
        System.out.println("\n🔍 Sequential Search 查詢分類: \"" + cat2 + "\"");
        List<Book> catList2 = library.searchByCategory(cat2);
        if (catList2.isEmpty()) {
            System.out.println("  查無分類為 \"" + cat2 + "\" 的書籍。");
        }
    }
}