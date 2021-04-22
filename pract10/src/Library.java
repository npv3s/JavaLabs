import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Library {
    int id;
    String address;
    List<Book> books;

    public Library(int id, String address, List<Book> books) {
        this.id = id;
        this.address = address;
        this.books = books;
    }

    public static Library read(File file) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(file));
        StringBuilder s = new StringBuilder();

        String line;
        line = bf.readLine();

        String []buf;
        buf = line.split(";");

        int id = Integer.parseInt(buf[0]);
        String title = buf[1];

        List<Book> books = new LinkedList<>();
        while ((line = bf.readLine()) != null) {
            buf = line.split(";");
            Book book = new Book(Integer.parseInt(buf[0]), buf[1], buf[2], Integer.parseInt(buf[3]));
            books.add(book);
        }

        bf.close();

        return new Library(id, title, books);
    }

    public static void write(File file, Library lib) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(lib.id + ";" + lib.address + "\n");
        for (Book book : lib.books) {
            fw.write(book.id + ";" + book.title + ";" + book.author + ";" + book.year + "\n");
        }
        fw.close();
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", books=" + books +
                '}';
    }
}
