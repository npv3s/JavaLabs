public class GameConfig {
    String ip = "127.0.0.1";
    int port = 25655;
    int difficulty = 2;
    long updatePeriod = 1000;
    int savePeriod = 5;

    public GameConfig() {
    }

    public GameConfig(String ip, int port, int difficulty, long updatePeriod, int savePeriod) {
        this.ip = ip;
        this.port = port;
        this.difficulty = difficulty;
        this.updatePeriod = updatePeriod;
        this.savePeriod = savePeriod;
    }

    @Override
    public String toString() {
        return "GameConfig{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", difficulty=" + difficulty +
                ", updatePeriod=" + updatePeriod +
                ", savePeriod=" + savePeriod +
                '}';
    }
}
