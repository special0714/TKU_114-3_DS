import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseRegistrationSystem {

    private static final List<Course> courseList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("請選擇操作項目 (1-8): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addCourse();
                    break;
                case "2":
                    enrollCourse();
                    break;
                case "3":
                    dropCourse();
                    break;
                case "4":
                    deleteCourse();
                    break;
                case "5":
                    searchCourse();
                    break;
                case "6":
                    listAllCourses();
                    break;
                case "7":
                    showStatistics();
                    break;
                case "8":
                    running = false;
                    System.out.println("系統已結束，再見！");
                    break;
                default:
                    System.out.println(" 無效的選擇，請輸入 1 到 8 之間的數字。");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("====================================");
        System.out.println("        選課與課程管理系統");
        System.out.println("====================================");
        System.out.println("1. 新增課程");
        System.out.println("2. 學生選課");
        System.out.println("3. 學生退選");
        System.out.println("4. 刪除課程");
        System.out.println("5. 搜尋課程 (代碼或名稱)");
        System.out.println("6. 列出所有課程");
        System.out.println("7. 輸出統計資訊 (總數/總人次/額滿清單)");
        System.out.println("8. 離開系統");
        System.out.println("====================================");
    }

    private static void addCourse() {
        System.out.print("請輸入課程代碼: ");
        String code = scanner.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println(" 錯誤：課程代碼不能為空白！");
            return;
        }

        if (findCourseByCode(code) != null) {
            System.out.println(" 錯誤：課程代碼「" + code + "」已存在，不可重複！");
            return;
        }

        System.out.print("請輸入課程名稱: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println(" 錯誤：課程名稱不能為空白！");
            return;
        }

        try {
            System.out.print("請輸入課程容量上限: ");
            int capacity = Integer.parseInt(scanner.nextLine().trim());

            if (capacity <= 0) {
                System.out.println(" 錯誤：課程容量必須大於 0！");
                return;
            }

            courseList.add(new Course(code, name, capacity));
            System.out.println(" 成功新增課程：「" + name + "」 (代碼: " + code + ", 容量: " + capacity + " 人)");

        } catch (NumberFormatException e) {
            System.out.println(" 錯誤：容量必須為有效的數字！");
        }
    }

    private static void enrollCourse() {
        if (courseList.isEmpty()) {
            System.out.println(" 目前系統無任何課程可供選課。");
            return;
        }

        System.out.print("請輸入要選課的課程代碼: ");
        String code = scanner.nextLine().trim();

        Course course = findCourseByCode(code);

        if (course == null) {
            System.out.println(" 找不到代碼為「" + code + "」的課程。");
            return;
        }

        if (course.enroll()) {
            System.out.println(" 選課成功！課程「" + course.getName() + "」目前人數: " 
                    + course.getEnrolled() + "/" + course.getCapacity());
        } else {
            System.out.println(" 選課失敗：課程「" + course.getName() + "」已經額滿！(" 
                    + course.getCapacity() + "/" + course.getCapacity() + ")");
        }
    }

    // 3. 學生退選
    private static void dropCourse() {
        if (courseList.isEmpty()) {
            System.out.println(" 目前系統無任何課程。");
            return;
        }

        System.out.print("請輸入要退選的課程代碼: ");
        String code = scanner.nextLine().trim();

        Course course = findCourseByCode(code);

        if (course == null) {
            System.out.println(" 找不到代碼為「" + code + "」的課程。");
            return;
        }

        if (course.drop()) {
            System.out.println(" 退選成功！課程「" + course.getName() + "」目前人數: " 
                    + course.getEnrolled() + "/" + course.getCapacity());
        } else {
            System.out.println(" 退選失敗：課程「" + course.getName() + "」目前無人選課。");
        }
    }

    private static void deleteCourse() {
        if (courseList.isEmpty()) {
            System.out.println(" 目前系統無任何課程可供刪除。");
            return;
        }

        System.out.print("請輸入要刪除的課程代碼: ");
        String code = scanner.nextLine().trim();

        Course course = findCourseByCode(code);

        if (course != null) {
            courseList.remove(course);
            System.out.println(" 成功刪除課程：「" + course.getName() + "」 (代碼: " + code + ")！");
        } else {
            System.out.println(" 找不到代碼為「" + code + "」的課程，刪除失敗。");
        }
    }

    private static void searchCourse() {
        if (courseList.isEmpty()) {
            System.out.println(" 目前系統無任何課程記錄。");
            return;
        }

        System.out.print("請輸入要搜尋的課程代碼或名稱: ");
        String keyword = scanner.nextLine().trim();

        if (keyword.isEmpty()) {
            System.out.println(" 錯誤：關鍵字不能為空白！");
            return;
        }

        List<Course> matches = new ArrayList<>();
        for (Course c : courseList) {
            if (c.getCode().equalsIgnoreCase(keyword) || c.getName().equalsIgnoreCase(keyword)) {
                matches.add(c);
            }
        }

        if (matches.isEmpty()) {
            System.out.println(" 找不到符合「" + keyword + "」的課程。");
        } else {
            System.out.println(" 搜尋結果 (共 " + matches.size() + " 筆):");
            for (Course c : matches) {
                System.out.println(" - " + c);
            }
        }
    }

    private static void listAllCourses() {
        if (courseList.isEmpty()) {
            System.out.println(" 目前系統內無任何課程。");
            return;
        }

        System.out.println(" 目前所有課程列表 (共 " + courseList.size() + " 門):");
        for (int i = 0; i < courseList.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + courseList.get(i));
        }
    }

    private static void showStatistics() {
        if (courseList.isEmpty()) {
            System.out.println(" 目前無課程資料，無法計算統計。");
            return;
        }

        int totalCourses = courseList.size();
        int totalEnrolled = 0;
        List<Course> fullCourses = new ArrayList<>();

        for (Course c : courseList) {
            totalEnrolled += c.getEnrolled();
            if (c.isFull()) {
                fullCourses.add(c);
            }
        }

        System.out.println(" === 系統統計資訊 ===");
        System.out.println("總課程數：" + totalCourses + " 門");
        System.out.println("總選課人次：" + totalEnrolled + " 人次");
        System.out.println("額滿課程數：" + fullCourses.size() + " 門");

        if (!fullCourses.isEmpty()) {
            System.out.println("------------------------------------");
            System.out.println(" 額滿課程清單：");
            for (Course fc : fullCourses) {
                System.out.println(" - " + fc.getName() + " (代碼: " + fc.getCode() + ", 容量: " + fc.getCapacity() + " 人)");
            }
        }
    }

    private static Course findCourseByCode(String code) {
        for (Course c : courseList) {
            if (c.getCode().equalsIgnoreCase(code)) {
                return c;
            }
        }
        return null;
    }
}