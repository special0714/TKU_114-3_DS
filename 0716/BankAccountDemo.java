public class BankAccountDemo {
    public static void main(String[] args) {

        BankAccount accountA = new BankAccount("123-456", "陳小明", 1000);
        BankAccount accountB = new BankAccount("987-654", "林小華", 500);

        System.out.println("=== 初始帳戶狀態 ===");
        System.out.println(accountA);
        System.out.println(accountB);
        System.out.println("--------------------------------------------------\n");

        System.out.println("--- 測試 1：小明存款 500 元 ---");
        if (accountA.deposit(500)) {
            System.out.println("成功！" + accountA);
        }
        System.out.println();

        System.out.println("--- 測試 2：小華提款 200 元 ---");
        if (accountB.withdraw(200)) {
            System.out.println("成功！" + accountB);
        }
        System.out.println();

        System.out.println("--- 測試 3：小明轉帳 400 元給小華 ---");
        if (accountA.transferTo(accountB, 400)) {
            System.out.println("【轉帳成功！】");
            System.out.println(accountA);
            System.out.println(accountB);
        }
        System.out.println();

        System.out.println("--- 測試 4：小華嘗試轉帳 2000 元給小明（預期失敗） ---");
        if (!accountB.transferTo(accountA, 2000)) {
            System.out.println("狀態確認（雙方餘額不應改變）：");
            System.out.println(accountA);
            System.out.println(accountB);
        }
        System.out.println();

        System.out.println("--- 測試 5：小明嘗試轉帳 -100 元給小華（預期失敗） ---");
        if (!accountA.transferTo(accountB, -100)) {
            System.out.println("狀態確認（雙方餘額不應改變）：");
            System.out.println(accountA);
            System.out.println(accountB);
        }
        System.out.println("--------------------------------------------------\n");
        
        System.out.println("=== 最終帳戶狀態 ===");
        System.out.println(accountA);
        System.out.println(accountB);
    }
}