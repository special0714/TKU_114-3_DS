import java.util.ArrayDeque;
import java.util.Deque;

public class BracketValidationSystem {

    public static boolean isValid(String input) {

        if (input == null || input.isEmpty()) {
            return true;
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);


            if (isLeftBracket(ch)) {
                stack.push(ch);
            } 

            else if (isRightBracket(ch)) {
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();
                if (!isMatchingPair(top, ch)) {
                    return false; 
                }
            }
        }

        return stack.isEmpty();
    }

    private static boolean isLeftBracket(char ch) {
        return ch == '(' || ch == '[' || ch == '{';
    }

    private static boolean isRightBracket(char ch) {
        return ch == ')' || ch == ']' || ch == '}';
    }

    private static boolean isMatchingPair(char left, char right) {
        return (left == '(' && right == ')') ||
               (left == '[' && right == ']') ||
               (left == '{' && right == '}');
    }

    public static void main(String[] args) {
        System.out.println("=== 括號檢查系統測試 (BracketValidationSystem) ===\n");

        testCase("正解範例 1 (含普通字元)", "System.out.println(array[list{0}]);", true);

        testCase("正解範例 2 (多層巢狀)", "{ [ ( ) ] ( ) }", true);

        testCase("錯誤範例 1 (順序錯誤)", "([)]", false);

        testCase("錯誤範例 2 (缺少左括號)", "int x = 10);", false);

        testCase("錯誤範例 3 (缺少右括號)", "function test() { if (true) { x = 1; }", false);

        testCase("安全邊界 1 (空字串)", "", true);

        testCase("安全邊界 2 (無括號純文字)", "Hello World 123!", true);

        testCase("安全邊界 3 (極端右括號)", "]", false);
    }

    private static void testCase(String title, String input, boolean expected) {
        boolean result = isValid(input);
        String status = (result == expected) ? "PASS" : "FAIL";
        System.out.printf("[%s] %-25s | 結果: %-5b (預期: %b)\n", 
                          status, title, result, expected);
    }
}