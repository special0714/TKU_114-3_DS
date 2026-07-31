public class Contestant {
    private String id;        
    private String name;      
    private int score;        
    private double seconds;   

    public Contestant(String id, String name, int score, double seconds) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.seconds = seconds;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public double getSeconds() {
        return seconds;
    }

    @Override
    public String toString() {
        return String.format("編號: %-5s | 姓名: %-6s | 分數: %-3d | 時間: %5.2f 秒", id, name, score, seconds);
    }
}