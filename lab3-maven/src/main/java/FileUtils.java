import com.google.gson.*;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.*;
import java.nio.file.Files;
import java.util.LinkedList;

public class FileUtils {
    static Gson gson;
    static Kryo kryo;

    public static void init() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        kryo = new Kryo();
        kryo.register(World.class);
        kryo.register(LinkedList.class);
        kryo.register(Entity.class);
        kryo.register(EntityPlayer.class);
        kryo.setReferences(true);
    }

    public static void saveConfig(File path, GameConfig config) throws IOException {
        Files.write(path.toPath(), gson.toJson(config).getBytes());
    }

    public static GameConfig loadConfig(File path) throws IOException {
        return gson.fromJson(new String(Files.readAllBytes(path.toPath())), GameConfig.class);
    }

    public static void saveWorld(File path, World world) throws IOException {
        Output output = new Output(new FileOutputStream(path));
        kryo.writeObject(output, world);
        output.close();
    }

    public static World loadWorld(File path) throws IOException {
        Input input = new Input(new FileInputStream(path));
        World world = kryo.readObject(input, World.class);
        input.close();
        return world;
    }
}
