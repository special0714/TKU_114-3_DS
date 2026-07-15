import java.util.Scanner;

public class TextAnalyzer {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== 步驟 1: 輸入文字 ===");
        String inputText = readNonEmptyLine(sc);

        System.out.println("\n=== 步驟 2: 字元統計 ===");
        System.out.println("原始字元數（含前後空白）: " + inputText.length());
        String trimmedText = inputText.trim();
        System.out.println("有效字元數（使用 trim() 後）: " + trimmedText.length());

        String[] words = splitWords(trimmedText);
        System.out.println("\n=== 步驟 3: 單字與字母統計 ===");
        System.out.println("單字總數量: " + words.length);

        int vowelCount = countVowels(trimmedText);
        System.out.println("英文字母母音 (a, e, i, o, u) 總數: " + vowelCount);

        String longestWord = findLongestWord(words);
        System.out.println("最長單字為: \"" + longestWord + "\" (長度: " + longestWord.length() + ")");
        System.out.println("\n=== 步驟 4: 關鍵字搜尋 ===");
        System.out.print("請輸入要搜尋的關鍵字: ");
        String keyword = sc.next(); 

        int occurrences = countKeywordOccurrences(words, keyword);
        System.out.println("關鍵字 \"" + keyword + "\" 出現次數（忽略大小寫）: " + occurrences + " 次");

        sc.close();
    }

    public static String readNonEmptyLine(Scanner sc) {
        String input = "";
        while (true) {
            System.out.print("請輸入一行非空白文字: ");
            input = sc.nextLine();

            if (input != null && !input.trim().isEmpty()) {
                break;
            }
            System.out.println("錯誤：輸入內容不能為空或全空白，請重新輸入！");
        }
        return input;
    }

    public static String[] splitWords(String text) {
        if (text == null || text.trim().isEmpty()) {
            return new String[0];
        }

        return text.trim().split("\\s+");
    }

    public static int countVowels(String text) {
        int count = 0;
        String lowerText = text.toLowerCase();
        for (int i = 0; i < lowerText.length(); i++) {
            char ch = lowerText.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }
        return count;
    }

    public static String findLongestWord(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        String longest = words[0];
        for (int i = 1; i < words.length; i++) {
            if (words[i].length() > longest.length()) {
                longest = words[i];
            }
        }
        return longest;
    }

    public static int countKeywordOccurrences(String[] words, String keyword) {
        if (words == null || keyword == null) {
            return 0;
        }
        int count = 0;
        for (String word : words) {
            String cleanWord = word.replaceAll("[.,!?\"';:]", "");
            if (cleanWord.equalsIgnoreCase(keyword)) {
                count++;
            }
        }
        return count;
    }
}