import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactBookSystem {

    private static final List<Contact> contactList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("請選擇操作項目 (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addContact();
                    break;
                case "2":
                    searchContact();
                    break;
                case "3":
                    updatePhone();
                    break;
                case "4":
                    deleteContact();
                    break;
                case "5":
                    listAllContacts();
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
        System.out.println("         通訊錄管理系統");
        System.out.println("====================================");
        System.out.println("1. 新增聯絡人");
        System.out.println("2. 搜尋聯絡人 (代碼或姓名)");
        System.out.println("3. 修改電話");
        System.out.println("4. 刪除聯絡人");
        System.out.println("5. 列出完整通訊錄");
        System.out.println("6. 離開系統");
        System.out.println("====================================");
    }

    private static void addContact() {
        System.out.print("請輸入聯絡人代碼: ");
        String id = scanner.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println(" 錯誤：代碼不能為空白！");
            return;
        }

        if (findContactById(id) != null) {
            System.out.println(" 錯誤：代碼「" + id + "」已存在，不可重複新增！");
            return;
        }

        System.out.print("請輸入姓名: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println(" 錯誤：姓名不能為空白！");
            return;
        }

        System.out.print("請輸入電話: ");
        String phone = scanner.nextLine().trim();

        System.out.print("請輸入電子郵件: ");
        String email = scanner.nextLine().trim();

        contactList.add(new Contact(id, name, phone, email));
        System.out.println(" 成功新增聯絡人：「" + name + "」 (代碼: " + id + ")");
    }

    private static void searchContact() {
        if (contactList.isEmpty()) {
            System.out.println(" 目前通訊錄為空。");
            return;
        }

        System.out.print("請輸入要搜尋的代碼或姓名: ");
        String keyword = scanner.nextLine().trim();

        if (keyword.isEmpty()) {
            System.out.println(" 錯誤：關鍵字不能為空白！");
            return;
        }

        List<Contact> matchedContacts = new ArrayList<>();
        for (Contact c : contactList) {
            if (c.getId().equalsIgnoreCase(keyword) || c.getName().equalsIgnoreCase(keyword)) {
                matchedContacts.add(c);
            }
        }

        if (matchedContacts.isEmpty()) {
            System.out.println("🔍 找不到符合「" + keyword + "」的聯絡人。");
        } else {
            System.out.println("🔍 搜尋結果 (共 " + matchedContacts.size() + " 筆):");
            for (Contact c : matchedContacts) {
                System.out.println(" - " + c);
            }
        }
    }

    private static void updatePhone() {
        if (contactList.isEmpty()) {
            System.out.println(" 目前通訊錄為空，無法修改。");
            return;
        }

        System.out.print("請輸入要修改電話的聯絡人代碼: ");
        String id = scanner.nextLine().trim();

        Contact contact = findContactById(id);

        if (contact == null) {
            System.out.println(" 找不到代碼為「" + id + "」的聯絡人。");
            return;
        }

        System.out.println("找到聯絡人：「" + contact.getName() + "」，目前電話: " + contact.getPhone());
        System.out.print("請輸入新電話: ");
        String newPhone = scanner.nextLine().trim();

        String oldPhone = contact.getPhone();
        contact.setPhone(newPhone);
        System.out.println(" 已將「" + contact.getName() + "」的電話從 「" + oldPhone + "」 修改為 「" + newPhone + "」！");
    }

    private static void deleteContact() {
        if (contactList.isEmpty()) {
            System.out.println(" 目前通訊錄為空，無法刪除。");
            return;
        }

        System.out.print("請輸入要刪除的聯絡人代碼: ");
        String id = scanner.nextLine().trim();

        Contact contact = findContactById(id);

        if (contact != null) {
            contactList.remove(contact);
            System.out.println(" 成功刪除聯絡人：「" + contact.getName() + "」 (代碼: " + id + ")！");
        } else {
            System.out.println(" 找不到代碼為「" + id + "」的聯絡人，刪除失敗。");
        }
    }

    private static void listAllContacts() {
        if (contactList.isEmpty()) {
            System.out.println(" 目前通訊錄無任何資料。");
            return;
        }

        System.out.println(" 完整通訊錄列表 (共 " + contactList.size() + " 筆):");
        for (int i = 0; i < contactList.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + contactList.get(i));
        }
    }

    private static Contact findContactById(String id) {
        for (Contact contact : contactList) {
            if (contact.getId().equalsIgnoreCase(id)) {
                return contact;
            }
        }
        return null;
    }
}