public class BankAccount {

    private String accountNumber;
    private String accountName;
    private int balance;

    public BankAccount(String accountNumber, String accountName, int initialBalance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;

        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0;
            System.out.println("警告：初始餘額不能為負數，已設定為 0 元。");
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public int getBalance() {
        return balance;
    }

    public boolean deposit(int amount) {
        if (amount > 0) {
            this.balance += amount;
            return true;
        }
        System.out.println("【存款失敗】金額必須大於 0 元。");
        return false;
    }

    public boolean withdraw(int amount) {
        if (amount <= 0) {
            System.out.println("【提款失敗】金額必須大於 0 元。");
            return false;
        }
        if (amount > this.balance) {
            System.out.println("【提款失敗】餘額不足。（當前餘額: " + this.balance + " 元）");
            return false;
        }
        this.balance -= amount;
        return true;
    }

    public boolean transferTo(BankAccount target, int amount) {
        if (target == null) {
            System.out.println("【轉帳失敗】目標帳戶不存在。");
            return false;
        }
        if (amount <= 0) {
            System.out.println("【轉帳失敗】金額必須大於 0 元。");
            return false;
        }
        if (amount > this.balance) {
            System.out.println("【轉帳失敗】餘額不足，無法轉帳。（當前餘額: " + this.balance + " 元）");
            return false;
        }

        this.balance -= amount;
        target.deposit(amount); 
        return true;
    }

    @Override
    public String toString() {
        return String.format("帳戶[%s] 戶名:%s | 餘額:%d元", accountNumber, accountName, balance);
    }
}