import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NameListManager {

    private static final List<String> nameList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("請選擇操作項目 (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addName();
                    break;
                case "2":
                    searchName();
                    break;
                case "3":
                    updateName();
                    break;
                case "4":
                    deleteName();
                    break;
                case "5":
                    listAllNames();
                    break;
                case "6":
                    running = false;
                    System.out.println("系統已結束，再見！");
                    break;
                default:
                    System.out.println(" 無效的選擇，請輸入 1 到 6 之間的數字。");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("====================================");
        System.out.println("        姓名名單管理系統");
        System.out.println("====================================");
        System.out.println("1. 新增姓名");
        System.out.println("2. 搜尋姓名");
        System.out.println("3. 修改姓名");
        System.out.println("4. 刪除姓名");
        System.out.println("5. 列出全部姓名");
        System.out.println("6. 離開系統");
        System.out.println("====================================");
    }

    private static void addName() {
        System.out.print("請輸入要新增的姓名: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println(" 錯誤：不能新增空白或純空格的姓名！");
            return;
        }

        nameList.add(name);
        System.out.println(" 成功新增姓名：「" + name + "」");
    }

    private static void searchName() {
        if (nameList.isEmpty()) {
            System.out.println(" 目前名單為空，無法搜尋。");
            return;
        }

        System.out.print("請輸入要搜尋的姓名: ");
        String target = scanner.nextLine().trim();

        List<Integer> foundIndices = findIndicesIgnoreCase(target);

        if (foundIndices.isEmpty()) {
            System.out.println(" 找不到符合「" + target + "」的姓名。");
        } else {
            System.out.println(" 找到以下符合的項目 (忽略大小寫)：");
            for (int index : foundIndices) {
                System.out.println(" - 索引號 [" + index + "]: " + nameList.get(index));
            }
        }
    }

    private static void updateName() {
        if (nameList.isEmpty()) {
            System.out.println(" 目前名單為空，無法修改。");
            return;
        }

        System.out.print("請輸入要修改的原姓名: ");
        String oldName = scanner.nextLine().trim();

        int index = findFirstIndexIgnoreCase(oldName);

        if (index == -1) {
            System.out.println(" 找不到原姓名：「" + oldName + "」，修改失敗。");
            return;
        }

        System.out.println("找到現有姓名：「" + nameList.get(index) + "」");
        System.out.print("請輸入新的姓名: ");
        String newName = scanner.nextLine().trim();

        if (newName.isEmpty()) {
            System.out.println(" 錯誤：新姓名不能為空白！");
            return;
        }

        String originalName = nameList.set(index, newName);
        System.out.println(" 已將「" + originalName + "」成功修改為「" + newName + "」！");
    }

    private static void deleteName() {
        if (nameList.isEmpty()) {
            System.out.println(" 目前名單為空，無法刪除。");
            return;
        }

        System.out.print("請輸入要刪除的姓名: ");
        String target = scanner.nextLine().trim();

        int index = findFirstIndexIgnoreCase(target);

        if (index != -1) {
            String removedName = nameList.remove(index);
            System.out.println(" 成功刪除姓名：「" + removedName + "」！");
        } else {
            System.out.println(" 找不到姓名：「" + target + "」，刪除失敗。");
        }
    }

    // 5. 列出全部功能
    private static void listAllNames() {
        if (nameList.isEmpty()) {
            System.out.println(" 目前名單無任何記錄。");
            return;
        }

        System.out.println(" 目前所有姓名列表 (共 " + nameList.size() + " 筆):");
        for (int i = 0; i < nameList.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + nameList.get(i));
        }
    }

    private static int findFirstIndexIgnoreCase(String target) {
        for (int i = 0; i < nameList.size(); i++) {
            if (nameList.get(i).equalsIgnoreCase(target)) {
                return i;
            }
        }
        return -1;
    }

    private static List<Integer> findIndicesIgnoreCase(String target) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < nameList.size(); i++) {
            if (nameList.get(i).equalsIgnoreCase(target)) {
                indices.add(i);
            }
        }
        return indices;
    }
}