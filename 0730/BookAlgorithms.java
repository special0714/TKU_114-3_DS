import java.util.ArrayList;
import java.util.List;

public class BookAlgorithms {

    public static void mergeSortByIdAsc(List<Book> books, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSortByIdAsc(books, left, mid);
        mergeSortByIdAsc(books, mid + 1, right);
        mergeById(books, left, mid, right);
    }

    private static void mergeById(List<Book> books, int left, int mid, int right) {
        List<Book> temp = new ArrayList<>();
        int i = left, j = mid + 1;

        while (i <= mid && j <= right) {
            if (books.get(i).getId().compareTo(books.get(j).getId()) <= 0) {
                temp.add(books.get(i++));
            } else {
                temp.add(books.get(j++));
            }
        }
        while (i <= mid) temp.add(books.get(i++));
        while (j <= right) temp.add(books.get(j++));

        for (int m = 0; m < temp.size(); m++) {
            books.set(left + m, temp.get(m));
        }
    }

    public static void mergeSortByBorrowCountDesc(List<Book> books, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSortByBorrowCountDesc(books, left, mid);
        mergeSortByBorrowCountDesc(books, mid + 1, right);
        mergeByBorrowCount(books, left, mid, right);
    }

    private static void mergeByBorrowCount(List<Book> books, int left, int mid, int right) {
        List<Book> temp = new ArrayList<>();
        int i = left, j = mid + 1;

        while (i <= mid && j <= right) {
            if (books.get(i).getBorrowCount() >= books.get(j).getBorrowCount()) {
                temp.add(books.get(i++));
            } else {
                temp.add(books.get(j++));
            }
        }
        while (i <= mid) temp.add(books.get(i++));
        while (j <= right) temp.add(books.get(j++));

        for (int m = 0; m < temp.size(); m++) {
            books.set(left + m, temp.get(m));
        }
    }

    public static Book binarySearchById(List<Book> books, String targetId) {
        if (books == null || books.isEmpty() || targetId == null) return null;

        int low = 0;
        int high = books.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = targetId.compareTo(books.get(mid).getId());

            if (cmp == 0) {
                return books.get(mid);
            } else if (cmp < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return null;
    }

    public static List<Book> sequentialSearchByCategory(List<Book> books, String category) {
        List<Book> result = new ArrayList<>();
        if (books == null || books.isEmpty() || category == null) return result;

        for (Book book : books) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                result.add(book);
            }
        }
        return result;
    }
}