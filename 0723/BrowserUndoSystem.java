import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserUndoSystem {

    private Deque<String> historyStack;

    public BrowserUndoSystem() {
        this.historyStack = new ArrayDeque<>();
    }

    public void visitPage(String url) {
        historyStack.push(url);
        System.out.println("開啟新頁面: " + url);
    }

    public String undo() {
        if (historyStack.isEmpty()) {
            return null;
        }
        return historyStack.pop();
    }

    public void currentPage() {
        if (historyStack.isEmpty()) {
            System.out.println("目前頁面: [空白頁 / 無瀏覽紀錄]");
        } else {
            System.out.println("目前頁面: " + historyStack.peek());
        }
    }

    public static void main(String[] args) {
        BrowserUndoSystem browser = new BrowserUndoSystem();

        System.out.println("=== 開始瀏覽器歷史紀錄測試 ===\n");

        System.out.println("[操作 1] 邊界測試：在沒有頁面時按下返回");
        browser.undo(); 
        browser.currentPage();
        System.out.println("------------------------------------");

        System.out.println("[操作 2] 開啟 Google");
        browser.visitPage("https://www.google.com");
        browser.currentPage();
        System.out.println("------------------------------------");

        System.out.println("[操作 3] 開啟 YouTube");
        browser.visitPage("https://www.youtube.com");
        browser.currentPage();
        System.out.println("------------------------------------");

        System.out.println("[操作 4] 開啟 GitHub");
        browser.visitPage("https://www.github.com");
        browser.currentPage();
        System.out.println("------------------------------------");

        System.out.println("[操作 5] 按下返回 (回到上一頁)");
        browser.undo(); 
        browser.currentPage();
        System.out.println("------------------------------------");

        System.out.println("[操作 6] 開啟 Wikipedia");
        browser.visitPage("https://www.wikipedia.org");
        browser.currentPage();
        System.out.println("------------------------------------");

        System.out.println("[操作 7] 按下返回");
        browser.undo();
        browser.currentPage();
        System.out.println("------------------------------------");

        System.out.println("[操作 8] 按下返回");
        browser.undo();
        browser.currentPage();
        System.out.println("------------------------------------");

        System.out.println("[操作 9] 再次按下返回 (清空 Stack)");
        browser.undo();
        browser.currentPage();
        System.out.println("------------------------------------");

        System.out.println("[操作 10] 再次按下返回 (Stack 已空，檢查程式是否崩潰)");
        browser.undo();
        browser.currentPage();
    }
}