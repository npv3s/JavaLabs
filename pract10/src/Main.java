import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File f = new File("lib.txt");
        Library lib = Library.read(f);
        System.out.println(lib);

        f = new File("lib2.txt");
        Library.write(f, lib);
    }
}
