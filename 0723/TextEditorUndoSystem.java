import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorUndoSystem {

    private StringBuilder currentText;         
    private Deque<String> historyStack;       
    public TextEditorUndoSystem() {
        this.currentText = new StringBuilder();
        this.historyStack = new ArrayDeque<>();
    }

    private void saveState() {
        historyStack.push(currentText.toString());
    }

    public void type(String newText) {
        if (newText == null || newText.isEmpty()) {
            return;
        }
        saveState(); 
        currentText.append(newText);
        System.out.println("新增文字: \"" + newText + "\"");
    }

    public void delete(int count) {
        if (count <= 0 || currentText.length() == 0) {
            return;
        }
        saveState();

        int actualDeleteCount = Math.min(count, currentText.length());
        int start = currentText.length() - actualDeleteCount;
        currentText.delete(start, currentText.length());

        System.out.println("刪除最後 " + actualDeleteCount + " 個字元");
    }

    public void undo() {
        if (historyStack.isEmpty()) {
            System.out.println("【提示】無法撤銷：目前沒有可回滾的歷史紀錄！");
            return;
        }

        String previousState = historyStack.pop();
        currentText = new StringBuilder(previousState);
        System.out.println("執行撤銷 (Undo) 成功！");
    }

    public void display() {
        System.out.println(">>> 目前文字內容: \"" + currentText.toString() + "\"\n");
    }

    public static void main(String[] args) {
        TextEditorUndoSystem editor = new TextEditorUndoSystem();

        System.out.println("=== 編輯器 Undo 系統測試 (TextEditorUndoSystem) ===\n");

        System.out.println("[測試 1] 空歷史紀錄測試");
        editor.undo();
        editor.display();


        System.out.println("[測試 2] 進行編輯操作");
        editor.type("Hello");       
        editor.display();

        editor.type(" World");      
        editor.display();

        editor.type("!!!");        
        editor.display();

        editor.delete(3);          
        editor.display();

        editor.type(" Java");      
        editor.display();

        System.out.println("====================================");
        System.out.println("[測試 3] 驗證連續撤銷 3 次");
        System.out.println("====================================");

        System.out.println("--- 撤銷第 1 次 ---");
        editor.undo();
        editor.display();

        System.out.println("--- 撤銷第 2 次 ---");
        editor.undo();
        editor.display();

        System.out.println("--- 撤銷第 3 次 ---");
        editor.undo();
        editor.display();

        System.out.println("[測試 4] 繼續撤銷至初始狀態並測試邊界");
        editor.undo(); 
        editor.display();

        editor.undo(); 
        editor.display();

        editor.undo();
        editor.display();
    }
}