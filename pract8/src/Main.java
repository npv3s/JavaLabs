public class Main {
    public static void main(String[] args) {
        Library lib = new Library("№1 им. Ленина", "ул. Ленина 1");
        System.out.println(lib.hasBook("Библия"));
        lib.addBook(new Book("Библия", "Бог наш", 250));
        System.out.println(lib.hasBook("Библия"));
        lib.addBook(new Book("Библия", "Бог наш", 250));
        System.out.println(lib.takeBook("Библия"));
        System.out.println(lib);
        System.out.println(lib.takeBook("Библия"));
        System.out.println(lib);
    }
}
