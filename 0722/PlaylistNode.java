public class PlaylistNode {
    String songId;
    String title;
    PlaylistNode next;

    public PlaylistNode(String songId, String title) {
        this.songId = songId;
        this.title = title;
        this.next = null;
    }

    @Override
    public String toString() {
        return "[" + songId + "] " + title;
    }
}