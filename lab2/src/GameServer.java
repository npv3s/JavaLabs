public class GameServer {
    private static GameServer instance;
    protected String ip;
    protected int difficulty;
    protected World serverWorld;
    private int serverTicks;

    public GameServer() {
        instance = this;

        this.ip = "127.0.0.1";
        this.difficulty = 3;
        this.serverTicks = 0;
        this.serverWorld = new World(1, "Earth");
        serverWorld.addEntity(new Entity(serverWorld, "Zombie", 1, 2, true, 100, 100, 10));
        serverWorld.addEntity(new EntityPlayer(serverWorld, "Ra4ok", 11, 12, 100, 100, 15));

        System.out.println(serverWorld);

        for (int i = 0; i < 30; i++) {
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

    public static void main(String[] args) {
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
}
