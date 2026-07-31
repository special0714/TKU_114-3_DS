public class TransactionSortingSystem {

    public static void main(String[] args) {
        Transaction[] transactions = {
            new Transaction("T001", "ACC-101", 1500.00, 1620000003L),
            new Transaction("T002", "ACC-102", 5000.50, 1620000001L),
            new Transaction("T003", "ACC-103", 1500.00, 1620000001L), 
            new Transaction("T004", "ACC-104", 8200.00, 1620000005L),
            new Transaction("T005", "ACC-105", 5000.50, 1620000004L), 
            new Transaction("T006", "ACC-106", 1500.00, 1620000002L), 
            new Transaction("T007", "ACC-107", 300.00,  1620000000L)
        };

        System.out.println("==========================================================================");
        System.out.println("                         【 排序前交易紀錄列表 】                          ");
        System.out.println("==========================================================================");
        printTransactions(transactions);

        sortTransactions(transactions);

        System.out.println("\n==========================================================================");
        System.out.println("            【 排序後交易紀錄列表 (金額降冪 -> 時間序號升冪) 】            ");
        System.out.println("==========================================================================");
        printTransactions(transactions);
    }

    public static void sortTransactions(Transaction[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;

        for (int i = 1; i < n; i++) {
            Transaction key = arr[i]; 
            int j = i - 1;

            while (j >= 0 && shouldSwap(arr[j], key)) {
                arr[j + 1] = arr[j]; 
                j--;
            }

            arr[j + 1] = key;
        }
    }

    private static boolean shouldSwap(Transaction prior, Transaction current) {
        if (prior.getAmount() < current.getAmount()) {
            return true;
        } 
        else if (prior.getAmount() == current.getAmount()) {
            return prior.getTimestamp() > current.getTimestamp();
        }

        return false;
    }

    public static void printTransactions(Transaction[] transactions) {
        System.out.println("--------------------------------------------------------------------------");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
        System.out.println("--------------------------------------------------------------------------");
    }
}