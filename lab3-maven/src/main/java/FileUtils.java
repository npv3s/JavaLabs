import com.google.gson.*;
import org.nustaq.serialization.FSTConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtils {
    static Gson gson;

    static FSTConfiguration FSTConfig() {
        return FSTConfiguration.createUnsafeBinaryConfiguration();
    }

    public static void init() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public static void saveConfig(File path, GameConfig config) throws IOException {
        Files.write(path.toPath(), gson.toJson(config).getBytes());
    }

    public static GameConfig loadConfig(File path) throws IOException {
        return gson.fromJson(new String(Files.readAllBytes(path.toPath())), GameConfig.class);
    }

    public static void saveWorld(File path, World world) throws IOException {
        Files.write(path.toPath(), FSTConfig().asByteArray(world));
    }

    public static World loadWorld(File path) throws IOException {
        return (World) FSTConfig().asObject(Files.readAllBytes(path.toPath()));
    }
}
