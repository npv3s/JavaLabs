import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Library {
    String address;
    List<Book> books;

    public Library(String address) {
        this.address = address;
        this.books = new LinkedList<>();
    }

    public void readBook() throws BookReadException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите id: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Введите название: ");
        String title = sc.nextLine();

        System.out.print("Введите автора: ");
        String author = sc.nextLine();

        System.out.print("Введите год: ");
        int year = Integer.parseInt(sc.nextLine());

        if (id <= 0)
            throw new BookReadException("Неправильный id");
        if (title.length() < 3 || title.length() > 30)
            throw new BookReadException("Некорректное название");
        if (author.length() < 3 || author.length() > 30)
            throw new BookReadException("Некорректный автор");
        if (year < -3450 || year > 2022)
            throw new BookReadException("Некорректный год");

        books.add(new Book(id, title, author, year));
    }
}
