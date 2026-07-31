| 案例編號 | 測試分類 | 測試情境與輸入 (Inputs) | 執行操作 (Operations) | 預期結果 (Expected Results) | 實際結果 (Actual Results) | 測試結果 | 修正內容與重新測試結果 |
| :---: | :---: | :--- | :--- | :--- | :--- | :---: | :--- |
| **TC-01** | 空資料 | 系統初始無任何資料 | 執行 `sortById()` | 系統安全返回，不發生任何例外錯誤或崩潰 | 系統安全返回，無例外發送 | **PASS** | 無需修正 |
| **TC-02** | 空資料 | 系統初始無任何資料 | 搜尋編號 `"R001"` (`searchById`) | 回傳 `null`，不引發 `NullPointerException` | 回傳 `null` | **PASS** | 無需修正 |
| **TC-03** | 空資料 | 系統初始無任何資料 | 取消編號 `"R001"` (`cancelRegistration`) | 回傳 `false`，顯示查無資料錯誤提示 | 顯示 `Error: Registration ID not found - R001` 並回傳 `false` | **PASS** | 無需修正 |
| **TC-04** | 空資料 | 系統初始無取消紀錄 | 執行復原操作 (`undoLastCancellation`) | 回傳 `false`，顯示無取消紀錄提示 | 顯示 `Error: No cancellation record to undo.` 並回傳 `false` | **PASS** | 無需修正 |
| **TC-05** | 單筆 | 輸入 `("R001", "Alice", "0911")` (容量設為 3) | 執行 `register("R001", "Alice", "0911")` | 成功加入主清單，回傳 `true` | 顯示 `Success: Registered` 並加入主清單 | **PASS** | 無需修正 |
| **TC-06** | 單筆 | 主清單僅有 1 筆資料 `R001` | 搜尋編號 `"R001"` (`searchById`) | 成功找到並回傳該筆 `Registration` 物件 | 正確定位並回傳 `R001` 物件 | **PASS** | 無需修正 |
| **TC-07** | 重複 | 已存在 `R001`；再輸入 `("R001", "Bob", "0922")` | 執行 `register("R001", "Bob", "0922")` | 拒絕新增，顯示編號重複錯誤，主清單數量保持 1 | 顯示 `Error: Duplicate ID R001`，拒絕新增 | **PASS** | 無需修正 |
| **TC-08** | 邊界 | 容量容量上限為 3，已滿 3 筆；再輸入 `("R004", "David", "0944")` | 執行 `register("R004", "David", "0944")` | 正確辨識名額已滿，轉入 `waitingQueue` 保存 | 顯示 `Capacity full. Placed in waiting queue` | **PASS** | 無需修正 |
| **TC-09** | 找不到 | 主清單有 `R001`, `R002`, `R003`；搜尋 `"R099"` | 執行 `searchById("R099")` | 搜尋結束並回傳 `null` | 回傳 `null` | **PASS** | 無需修正 |
| **TC-10** | 找不到 | 主清單有 `R001`, `R002`；搜尋姓名 `"Eve"` | 執行 `searchByName("Eve")` | 回傳空列表 (`List` 大小為 0) | 回傳空列表 | **PASS** | 無需修正 |
| **TC-11** | 取消 | 主清單有 `R001`, `R002`；`waitingQueue` 有 `R004` | 執行 `cancelRegistration("R001")` | `R001` 移至 `cancelledStack`，`R004` 自動遞補至主清單 | `R001` 移至 Stack，`R004` 成功遞補進入主清單 | **PASS** | 無需修正 |
| **TC-12** | 取消 | 僅存在於 `waitingQueue` 中的 `R005` | 執行 `cancelRegistration("R005")` | 從 `waitingQueue` 移除並存入 `cancelledStack` | 成功從 Queue 移除並移至 Stack | **FAIL** | **問題**：原邏輯從 `waitingQueue` 移除資料後未清除 `registeredIds` 集合中的紀錄，導致該 ID 無法再次報名。<br>**修正**：於 `cancelRegistration` 的 Queue 處理區段補上 `registeredIds.remove(id)`。<br>**重新測試結果**：**PASS** |
| **TC-13** | 復原 | `cancelledStack` 有 `R001`；主清單尚有空位 | 執行 `undoLastCancellation()` | `R001` 從 Stack 彈出並還原至主清單 | 顯示 `Restored to main list: R001` | **PASS** | 無需修正 |
| **TC-14** | 復原 | `cancelledStack` 有 `R001`；主清單已滿 (3/3) | 執行 `undoLastCancellation()` | `R001` 還原至主清單，主清單最後一筆擠回 `waitingQueue` 隊首 | `R001` 還原至主清單，原本最後一筆被擠回 Queue 隊首 | **FAIL** | **問題**：擠回 Queue 時誤用 `offer()` 加入隊尾，破壞原本優先序。<br>**修正**：將 `waitingQueue` 轉型為 `ArrayDeque` 並使用 `addFirst()` 插回隊首。<br>**重新測試結果**：**PASS** |
| **TC-15** | 複合 | 主清單與 Queue 均有資料，且有同名者 "Alice" | 執行 `searchByName("Alice")` | 傳回包含主清單與 Queue 中所有名為 "Alice" 的完整列表 | 正確傳回跨主清單與 Queue 的所有符合紀錄 | **PASS** | 無需修正 |
總測試案例數**：15
初次通過案例數**：13
初次未通過案例數**：2 (TC-12, TC-14)
修正後最終通過率**：100% (15/15)