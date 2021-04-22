import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    String title;
    String address;
    List<Book> books = new ArrayList<>();
    Map<String, Integer> booksCache = new HashMap<>();

    public Library(String title, String address) {
        this.title = title;
        this.address = address;
    }

    public boolean hasBook(String title) {
        return booksCache.containsKey(title);
    }

    public Book takeBook(String title) {
        var amount = booksCache.get(title);
        if (amount != null)
            for (Book book : books)
                if (book.getTitle().equals(title)) {
                    amount -= 1;
                    if (amount > 0)
                        booksCache.put(title, amount);
                    else {
                        booksCache.remove(book.getTitle());
                        books.remove(book);
                    }
                    return book;
                }
        return null;
    }

    public void addBook(Book book) {
        var amount = booksCache.get(book.getTitle());
        if (amount != null)
            booksCache.put(book.getTitle(), amount + 1);
        else {
            booksCache.put(book.getTitle(), 1);
            books.add(book);
        }
    }

    public int bookCount(String title) {
        return booksCache.get(title);
    }

    @Override
    public String toString() {
        return "Library{" +
                "title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", books=" + books +
                ", booksCache=" + booksCache +
                '}';
    }
}
