import java.io.File;
import java.io.IOException;

public class GameServer {
    protected static GameServer instance;
    public World serverWorld;
    protected GameConfig config;
    protected int serverTicks;

    public GameServer() throws IOException {
        instance = this;

        FileUtils.init();

        GameConfig conf = new GameConfig();

        FileUtils.saveConfig(new File("conf.json"), conf);

        try {
            this.config = FileUtils.loadConfig(new File("conf.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.serverTicks = 0;
        this.serverWorld = new World(1, "Earth");

        for (int i = 0; i < 100000; i++) {
            serverWorld.addEntity(new Entity(serverWorld, "Zombie", i, i, true, 100, 100, 10));
        }
        serverWorld.addEntity(new EntityPlayer(serverWorld, "Ra4ok", 11, 12, 100, 100, 15));

        FileUtils.saveWorld(new File("world.bin"), serverWorld);

        //System.out.println(serverWorld);

        for (int i = 0; i < 0; i++) {
            this.updateServer();
            serverTicks++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }

        System.out.println(serverWorld);
    }

    public static void main(String[] args) throws IOException {
        new GameServer();
    }

    public static GameServer getInstance() {
        return instance;
    }

    public void updateServer() {
        serverWorld.update();
        serverTicks++;
    }

    public World getServerWorld() {
        return serverWorld;
    }

    int getServerTicks() {
        return serverTicks;
    }

    public GameConfig getConfig() {
        return config;
    }

    public void setConfig(GameConfig config) {
        this.config = config;
    }
}
