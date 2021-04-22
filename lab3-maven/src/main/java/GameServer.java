import java.io.File;
import java.io.IOException;

public class GameServer {
    protected static GameServer instance;
    protected World serverWorld;
    protected GameConfig config;
    protected int serverTicks;

    public GameServer() throws IOException {
        instance = this;

        FileUtils.init();

        config = new GameConfig();

        FileUtils.saveConfig(new File("conf.json"), config);

        config = FileUtils.loadConfig(new File("conf.json"));

        System.out.println(config);

        try {
            this.config = FileUtils.loadConfig(new File("conf.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.serverTicks = 0;
        this.serverWorld = new World(1, "Earth");

        serverWorld.addEntity(new Entity(serverWorld, "Zombie", 11, 18, true, 100, 100, 10));
        serverWorld.addEntity(new EntityPlayer(serverWorld, "Ra4ok", 11, 12, 100, 100, 15));

        FileUtils.saveWorld(new File("world.bin"), serverWorld);

        serverWorld = FileUtils.loadWorld(new File("world.bin"));

        System.out.println(serverWorld);

        int saveCounter = 0;
        for (int i = 0; i < 30; i++) {
            this.updateServer();
            serverTicks++;
            saveCounter++;
            if (saveCounter == config.savePeriod) {
                FileUtils.saveWorld(new File("world.bin"), serverWorld);
                saveCounter = 0;
            }
            try {
                Thread.sleep(config.updatePeriod);
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
