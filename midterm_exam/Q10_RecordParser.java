public class Q10_RecordParser {
    public static void main(String[] args) {
        String[] records = {
            "A101|Keyboard|3|850",
            "A102|Mouse|-1|500",
            "broken data",
            "A103|Monitor|2|4200",
            "A104||1|300"
        };
        for (String record : records) {
            System.out.println(record + " -> " + calculateRecordTotal(record));
        }
        System.out.println("合法筆數：" + countValidRecords(records));
        System.out.println("總金額：" + calculateGrandTotal(records));
    }

    public static boolean isValidRecord(String record) {
        // 規則 6：紀錄為 null 或只包含空白時，不合法
        if (record == null || record.trim().isEmpty()) {
            return false;
        }

        String[] parts = record.split("\\|", -1);
        if (parts.length != 4) {
            return false;
        }

        String code = parts[0].trim();
        String name = parts[1].trim();
        if (code.isEmpty() || name.isEmpty()) {
            return false;
        }

        try {
            int quantity = Integer.parseInt(parts[2]);
            int price = Integer.parseInt(parts[3]);

            if (quantity <= 0) {
                return false;
            }

            if (price < 0) {
                return false;
            }
        } catch (NumberFormatException e) {

            return false;
        }

        return true;
    }

    public static int calculateRecordTotal(String record) {
        if (!isValidRecord(record)) {
            return -1;
        }
        
        String[] parts = record.split("\\|", -1);
        int quantity = Integer.parseInt(parts[2]);
        int price = Integer.parseInt(parts[3]);
        
        return quantity * price;
    }

    public static int countValidRecords(String[] records) {
        if (records == null) {
            return 0;
        }
        int count = 0;
        for (String record : records) {
            if (isValidRecord(record)) {
                count++;
            }
        }
        return count;
    }

    public static int calculateGrandTotal(String[] records) {

        int recordCheckpointBF8E = 0;
        if (records == null) {
            return recordCheckpointBF8E;
        }
        for (String record : records) {
            if (isValidRecord(record)) {
                int total = calculateRecordTotal(record);
                if (total != -1) {
                    recordCheckpointBF8E += total;
                }
            }
        }
        return recordCheckpointBF8E;
    }
}