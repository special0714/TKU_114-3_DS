public class PlaylistSystem {

    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("       音樂播放清單管理系統 (Playlist)");
        System.out.println("==============================================\n");

        PlaylistLinkedList playlist = new PlaylistLinkedList();

        System.out.println("--- 1. 測試空串列狀態 ---");
        playlist.printPlaylist();
        playlist.searchById("S001");
        playlist.removeById("S001");
        System.out.println();

        System.out.println("--- 2. 測試尾端新增歌曲 ---");
        playlist.addLast("S001", "周傑倫 - 晴天");
        playlist.addLast("S002", "五月天 - 溫柔");
        playlist.addLast("S003", "告五人 - 披星戴月的想你");
        playlist.addLast("S004", "韋禮安 - 如果可以");
        playlist.printPlaylist();

        System.out.println("--- 3. 測試代碼不可重複 ---");
        playlist.addLast("S002", "重複代碼測試歌曲");
        playlist.printPlaylist();

        System.out.println("--- 4. 測試依代碼搜尋 ---");
        playlist.searchById("S003");
        playlist.searchById("S999");
        System.out.println();

        System.out.println("--- 5. 測試刪除第一首歌曲 (S001) ---");
        playlist.removeById("S001");
        playlist.printPlaylist();

        System.out.println("--- 6. 測試刪除中間歌曲 (S003) ---");
        playlist.removeById("S003");
        playlist.printPlaylist();

        System.out.println("--- 7. 測試刪除最後一首歌曲 (S004) ---");
        playlist.removeById("S004");
        playlist.printPlaylist();

        System.out.println("--- 8. 測試刪除不存在的代碼 (S888) ---");
        playlist.removeById("S888");
        playlist.printPlaylist();

        System.out.println("--- 9. 刪除唯一的最後一首歌曲 (S002) ---");
        playlist.removeById("S002");
        playlist.printPlaylist();
    }
}