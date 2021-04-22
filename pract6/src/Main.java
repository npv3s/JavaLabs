public class Main {
    public static void main(String[] args) {
        LinkedEntity l = new LinkedEntity();
        for (int i = 1; i < 10000; i++)
            l.add(new LinkedEntity());
        System.out.println("Contains 500 " + l.contains(500));
        System.out.println("Contains 65535 " + l.contains(65535));
        System.out.println("Size " + l.size());
        System.out.println("Delete 500 " + l.delete(500));
        System.out.println("Delete 500 " + l.delete(500));
        System.out.println("Size " + l.size());
    }
}
