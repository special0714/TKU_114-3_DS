public class Book {
    private String id;          
    private String title;       
    private String category;    
    private int borrowCount;    

    public Book(String id, String title, String category, int borrowCount) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.borrowCount = borrowCount;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public int getBorrowCount() {
        return borrowCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id != null && id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return String.format("編號: %-6s | 書名: %-16s | 分類: %-6s | 借閱次數: %3d", 
                id, title, category, borrowCount);
    }
}