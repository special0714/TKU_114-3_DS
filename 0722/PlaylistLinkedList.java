public class PlaylistLinkedList {
    private PlaylistNode head;
    private int count;

    public PlaylistLinkedList() {
        this.head = null;
        this.count = 0;
    }

    public boolean containsId(String songId) {
        PlaylistNode current = head;
        while (current != null) {
            if (current.songId.equalsIgnoreCase(songId)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean addLast(String songId, String title) {
        if (containsId(songId)) {
            System.out.println("新增失敗：歌曲代碼 [" + songId + "] 已存在，不可重複新增！");
            return false;
        }

        PlaylistNode newNode = new PlaylistNode(songId, title);
        if (head == null) {
            head = newNode;
        } else {
            PlaylistNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        count++;
        System.out.println("成功新增歌曲: " + newNode);
        return true;
    }

    public PlaylistNode searchById(String songId) {
        if (head == null) {
            System.out.println("搜尋失敗：播放清單為空。");
            return null;
        }

        PlaylistNode current = head;
        int index = 1;
        while (current != null) {
            if (current.songId.equalsIgnoreCase(songId)) {
                System.out.println("找到歌曲 (第 " + index + " 首): " + current);
                return current;
            }
            current = current.next;
            index++;
        }

        System.out.println("搜尋失敗：找不到代碼為 [" + songId + "] 的歌曲。");
        return null;
    }

    public boolean removeById(String songId) {
        if (head == null) {
            System.out.println("刪除失敗：播放清單為空。");
            return false;
        }

        if (head.songId.equalsIgnoreCase(songId)) {
            System.out.println("成功刪除 [第一首歌曲]: " + head);
            head = head.next;
            count--;
            return true;
        }

        PlaylistNode current = head;
        while (current.next != null && !current.next.songId.equalsIgnoreCase(songId)) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("刪除失敗：找不到代碼為 [" + songId + "] 的歌曲。");
            return false;
        }

        PlaylistNode target = current.next;
        if (target.next == null) {
            System.out.println("成功刪除 [最後一首歌曲]: " + target);
        } else {
            System.out.println("成功刪除 [中間歌曲]: " + target);
        }

        current.next = target.next;
        count--;
        return true;
    }

    public void printPlaylist() {
        System.out.print("=== 播放清單順序 (共 " + count + " 首) ===\n");
        if (head == null) {
            System.out.println("[ 清單為空 (Empty Playlist) ]");
            System.out.println("----------------------------------------------");
            return;
        }

        PlaylistNode current = head;
        int index = 1;
        while (current != null) {
            System.out.println(index + ". " + current);
            current = current.next;
            index++;
        }
        System.out.println("----------------------------------------------");
    }

    public int getCount() {
        return count;
    }
}