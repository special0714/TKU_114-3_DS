import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EquipmentManager {

    private static final List<Equipment> equipmentList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("請選擇操作項目 (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addEquipment();
                    break;
                case "2":
                    searchEquipmentById();
                    break;
                case "3":
                    borrowEquipment();
                    break;
                case "4":
                    returnEquipment();
                    break;
                case "5":
                    listAvailableEquipment();
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
        System.out.println("        設備借還管理系統");
        System.out.println("====================================");
        System.out.println("1. 新增設備");
        System.out.println("2. 依代碼搜尋設備");
        System.out.println("3. 借出設備");
        System.out.println("4. 歸還設備");
        System.out.println("5. 列出所有可借用設備");
        System.out.println("6. 離開系統");
        System.out.println("====================================");
    }

    private static void addEquipment() {
        System.out.print("請輸入設備代碼: ");
        String id = scanner.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println(" 錯誤：設備代碼不能為空白！");
            return;
        }

        if (findEquipmentById(id) != null) {
            System.out.println(" 錯誤：設備代碼「" + id + "」已存在，不可重複新增！");
            return;
        }

        System.out.print("請輸入設備名稱: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println(" 錯誤：設備名稱不能為空白！");
            return;
        }

        equipmentList.add(new Equipment(id, name));
        System.out.println(" 成功新增設備：「" + name + "」 (代碼: " + id + ")");
    }

    private static void searchEquipmentById() {
        if (equipmentList.isEmpty()) {
            System.out.println(" 目前系統內無任何設備記錄。");
            return;
        }

        System.out.print("請輸入要搜尋的設備代碼: ");
        String id = scanner.nextLine().trim();

        Equipment eq = findEquipmentById(id);

        if (eq != null) {
            System.out.println(" 搜尋結果：");
            System.out.println(eq);
        } else {
            System.out.println(" 找不到代碼為「" + id + "」的設備。");
        }
    }

    private static void borrowEquipment() {
        if (equipmentList.isEmpty()) {
            System.out.println(" 目前系統內無任何設備記錄。");
            return;
        }

        System.out.print("請輸入要借出的設備代碼: ");
        String id = scanner.nextLine().trim();

        Equipment eq = findEquipmentById(id);

        if (eq == null) {
            System.out.println(" 找不到代碼為「" + id + "」的設備，借出失敗。");
        } else if (!eq.isAvailable()) {
            System.out.println(" 設備「" + eq.getName() + "」目前已被借出，無法重複借用！");
        } else {
            eq.setAvailable(false);
            System.out.println(" 成功借出設備：「" + eq.getName() + "」 (代碼: " + eq.getId() + ")");
        }
    }

    private static void returnEquipment() {
        if (equipmentList.isEmpty()) {
            System.out.println(" 目前系統內無任何設備記錄。");
            return;
        }

        System.out.print("請輸入要歸還的設備代碼: ");
        String id = scanner.nextLine().trim();

        Equipment eq = findEquipmentById(id);

        if (eq == null) {
            System.out.println(" 找不到代碼為「" + id + "」的設備，歸還失敗。");
        } else if (eq.isAvailable()) {
            System.out.println(" 設備「" + eq.getName() + "」原本就是「可借用」狀態，無須歸還！");
        } else {
            eq.setAvailable(true);
            System.out.println(" 成功歸還設備：「" + eq.getName() + "」 (代碼: " + eq.getId() + ")");
        }
    }

    private static void listAvailableEquipment() {
        List<Equipment> availableList = new ArrayList<>();

        for (Equipment eq : equipmentList) {
            if (eq.isAvailable()) {
                availableList.add(eq);
            }
        }

        if (availableList.isEmpty()) {
            System.out.println("目前沒有任何可借用的設備。");
            return;
        }

        System.out.println(" 目前可借用設備列表 (共 " + availableList.size() + " 件):");
        for (int i = 0; i < availableList.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + availableList.get(i));
        }
    }

    private static Equipment findEquipmentById(String id) {
        for (Equipment eq : equipmentList) {
            if (eq.getId().equalsIgnoreCase(id)) {
                return eq;
            }
        }
        return null;
    }
}