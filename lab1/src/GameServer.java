import java.util.Arrays;

public class GameServer {
    private static GameServer instance;
    protected String ip;
    protected int difficulty;
    protected Entity[] entities;
    private int update;

    public GameServer() {
        instance = this;

        this.ip = "127.0.0.1";
        this.difficulty = 3;
        this.update = 0;
        this.entities = new Entity[]{
                new Entity("Zombie", 1, 2, true, 100, 100, 10),
                new EntityPlayer("Ra4ok", 11, 12, 100, 100, 15)
        };

        System.out.println(this);

        for (int i = 0; i < 30; i++) {
            this.updateServer();
            update++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }

        System.out.println(this);
    }

    public static void main(String[] args) {
        new GameServer();
    }

    public static GameServer getInstance() {
        return instance;
    }

    public void updateServer() {
        for (Entity e : entities) {
            if (e != null) {
                e.update();
            }
        }
    }

    @Override
    public String toString() {
        return "GameServer{" +
                "ip='" + ip + '\'' +
                ", difficulty=" + difficulty +
                ", entities=" + Arrays.toString(entities) +
                ", update=" + update +
                '}';
    }

    int getUpdate() {
        return update;
    }
}
