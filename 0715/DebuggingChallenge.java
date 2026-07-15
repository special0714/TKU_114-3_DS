/*
 * * 1. 編譯錯誤 (Compilation Error)
 * - 錯誤位置：`System.out.println("系統結束，年齡：" + age)` 行
 * - 原因：程式碼尾端漏掉了分號 `;`，導致 Java 編譯器無法成功編譯。
 * - 修正方式：在該行尾端補上分號 `;`。
 * * 2. 陣列越界錯誤 (ArrayIndexOutOfBoundsException)
 * - 錯誤位置：`for (int i = 0; i <= scores.length; i++)`
 * - 原因：迴圈條件使用了 `<=`。當 `i` 增加到與 `scores.length` (即 3) 相等時，
 * 試圖存取 `scores[3]`，但該陣列的有效索引只有 0, 1, 2，因而拋出執行期異常。
 * - 變數快照 (Breakpoint Value)：
 * * 發生錯誤瞬間：i = 3, scores.length = 3, total = 247
 * - 修正方式：將迴圈條件修改為 `i < scores.length`。
 * * 3. 整數除法造成的邏輯錯誤 (Integer Division Loss of Precision)
 * - 錯誤位置：`double average = total / scores.length;`
 * - 原因：`total` 與 `scores.length` 皆為整數（int），兩者相除會執行「整數除法」
 * （247 / 3 = 82），小數點後的值會直接被無條件捨去，隨後才隱式轉換成 double (82.00)。
 * - 變數快照 (Breakpoint Value)：
 * * 運算前：total = 247, scores.length = 3
 * * 運算後：average = 82.0 (正確平均數應為 82.333...)
 * - 修正方式：將其中一個運算元強制轉型為 double，如：`(double) total / scores.length`。
 * * 4. Scanner 換行殘留問題 (Scanner Buffer Issue)
 * - 錯誤位置：`String command = sc.nextLine();`
 * - 原因：在上一步 `sc.nextInt()` 讀取年齡時，只讀取了數字，而使用者按下的「Enter 鍵（換行符 \n）」
 * 仍殘留在緩衝區中。接下來執行 `sc.nextLine()` 時，會直接讀到這個殘留的換行符而直接結束，
 * 導致使用者根本來不及輸入指令。
 * - 變數快照 (Breakpoint Value)：
 * * 執行完 `sc.nextLine()` 後：command = "" (空字串)，未等待使用者輸入。
 * - 修正方式：在 `sc.nextInt()` 之後，先呼叫一次 `sc.nextLine()` 消耗掉殘留的換行符。
 * * 5. 字串比較錯誤 (String Comparison Logical Error)
 * - 錯誤位置：`if (command == "exit")`
 * - 原因：在 Java 中，`==` 比較的是字串的「記憶體位址（Reference）」，而不是內容。
 * 若要比較字串的實際內容，必須使用 `equals()` 方法。
 * - 變數快照 (Breakpoint Value)：
 * * 輸入 "exit" 時：command = "exit" (記憶體位址 A), "exit" 字面值 (記憶體位址 B) 
 * * `command == "exit"` 結果為 `false`。
 * - 修正方式：將條件修改為 `if ("exit".equals(command))` 或 `command.equals("exit")`。
 */

import java.util.Scanner;

public class DebuggingChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] scores = {80, 75, 92};
        int total = 0;

        // 修正 2：將 <= 改為 <，避免 ArrayIndexOutOfBoundsException
        for (int i = 0; i < scores.length; i++) {
            total += scores[i];
        }

        // 修正 3：強制轉型為 double 避免整數除法小數點丟失
        double average = (double) total / scores.length;
        System.out.printf("平均：%.2f%n", average);

        System.out.print("請輸入年齡：");
        int age = sc.nextInt();

        // 修正 4：消耗掉 sc.nextInt() 殘留在緩衝區的換行符
        sc.nextLine(); 

        System.out.print("請輸入指令：");
        String command = sc.nextLine();

        // 修正 5：使用 equals() 進行字串內容比較
        // 修正 1：在陳述句尾端補上分號「;」
        if ("exit".equals(command)) {
            System.out.println("系統結束，年齡：" + age);
        }

        sc.close();
    }
}