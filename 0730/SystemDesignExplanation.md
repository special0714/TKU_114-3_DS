1. 全域報名清單儲存 (Global Storage)
對應檔案與方法：EventRegistrationSystem.java $\rightarrow$ 欄位 allRegistrations 與方法 register()
採用技術：ArrayList<Registration>
選擇原因：
快速隨機存取：報名資料需頻繁供 Merge Sort 進行雙向索引存取，以及 Binary Search 進行 $O(1)$ 的中間節點（mid）選取。ArrayList 底層為連續記憶體陣列，能夠以 $O(1)$ 時間複雜度存取任意位置。
記憶體連續性：連續記憶體具有良好 CPU 快取局部性（Cache Locality）。
未採用 LinkedList 的原因：
LinkedList 無法提供 $O(1)$ 隨機存取，尋找中間節點必須從頭走訪，時間複雜度為 $O(N)$。若採用 LinkedList，Binary Search 與 Merge Sort 的效率將嚴重衰退至 $O(N \log N)$ 甚至更高，喪失高效搜尋與排序的優勢。
2. 候補順序排隊 (Waiting List Management)
對應檔案與方法：EventRegistrationSystem.java $\rightarrow$ 欄位 waitingQueue 與方法 register() (名額已滿時)
採用技術：Queue<Registration> (以 ArrayDeque 實作)
選擇原因：
先進先出（FIFO, First-In-First-Out）公平性：當正式名額額滿時，先報名者應優先列入候補，當有正式學員取消時，系統必須優先遞補最早等待的候補者。
高效佇列操作：Queue 的入隊 offer() 與出隊 poll() 均為 $O(1)$ 時間複雜度。
未採用 Stack 或固定陣列的原因：
若採用 Stack（LIFO），將導致最後加入候補的人反而最先獲得遞補，違反活動報名的公平原則。
若採用一般陣列，移除隊首元素時需要搬移後方所有元素，時間複雜度為 $O(N)$，效率低下。
3. 歷史取消紀錄與復原功能 (Undo Functionality)
對應檔案與方法：EventRegistrationSystem.java $\rightarrow$ 欄位 cancelledStack 與方法 undoLastCancellation()
採用技術：Stack<Registration> (以 ArrayDeque 實作)
選擇原因：
後進先出（LIFO, Last-In-First-Out）特性：使用者執行「復原（Undo）」操作時，直覺上是希望還原「最近一次（最後執行）」的取消動作。Stack 的 push() 與 pop() 完美契合此場景，且操作均為 $O(1)$。
未採用 Queue 的原因：
若採用 Queue，執行復原時會取得「最早被取消」的資料，而非「剛才最後取消」的資料，這不符合軟體界標準的 Undo（復原）行為邏輯。
4. 報名資料依編號排序 (ID Sorting)
對應檔案與方法：RegistrationAlgorithms.java $\rightarrow$ 方法 mergeSortById()
採用技術：Merge Sort（合併排序法）
選擇原因：
穩定排序（Stable Sort）：Merge Sort 在處理鍵值相同的資料時，能確保原始相對順序不改變。
最壞情況的時間複雜度保證：無論輸入資料是已排序、反向排序還是亂序，Merge Sort 的時間複雜度均恆定為 $O(N \log N)$，效能非常預測且穩定。
未採用 Selection Sort 或 Quick Sort 的原因：
Selection Sort 時間複雜度高達 $O(N^2)$，在資料量較大時比較次數過多，效能差。
Quick Sort 雖然平均速度快，但在最壞情況下（如已排序資料且 Pivot 選擇不佳）會退化至 $O(N^2)$，且 Quick Sort 為不穩定排序（Unstable Sort）。
5. 報名編號精確查詢 (Specific Record Search)
對應檔案與方法：RegistrationAlgorithms.java $\rightarrow$ 方法 binarySearchById()
採用技術：Binary Search（二元搜尋法）
選擇原因：
對數級時間複雜度 $O(\log N)$：報名編號為唯一鍵值（Unique Key），透過 Merge Sort 排序後，每次比較可排除一半的搜尋範圍。以 1,024 筆資料為例，最多只需 10 次比較即可定位目標。
未採用 Sequential Search 的原因：
Sequential Search 的時間複雜度為 $O(N)$，在 1,024 筆資料中最壞需比較 1,024 次。在清單已經有序的前提下，繼續使用 Sequential Search 會浪費先前排序建立的結構優勢。
6. 姓名同名多筆查詢 (Multi-record Search)
對應檔案與方法：RegistrationAlgorithms.java $\rightarrow$ 方法 sequentialSearchByName()
採用技術：Sequential Search（順序 / 線性搜尋法）
選擇原因：
支援非唯一鍵（Non-Unique Key）與多資料收集：活動報名中同名同姓（如 "Alice"）狀況相當普遍，且資料清單並未依姓名進行排序。Sequential Search 能夠完整走訪 ArrayList 與 waitingQueue，將所有符合條件的物件收集並傳回。
未採用 Binary Search 的原因：
Binary Search 必須建立在「搜尋欄位已嚴格排序」的前提下，但目前系統僅依「編號（ID）」排序，未依「姓名」排序。
即使資料依姓名排序，Binary Search 在找到第一個符合項後，仍需向左與向右線性擴展尋找重複值，處理多筆重複結果時邏輯較為複雜，且無法涵蓋無序的 waitingQueue。因此採用 Sequential Search 完整走訪為最可靠且合理的做法。

