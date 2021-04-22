import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library lib = new Library("Библиотека №1");

        Scanner sc = new Scanner(System.in);
        System.out.print("Введите количество книг: ");
        int amount = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < amount; i++)
            try {
                lib.readBook();
            } catch (Exception e) {
                System.out.println("Ошибочка вышла... " + e.getMessage());
            }

        System.out.println(lib.books);
    }
}
