import sun.misc.Unsafe;
import static sun.misc.Unsafe.getUnsafe;

public class Main {
    public static void main(String[] args) {
        Unsafe unsafe = getUnsafe();
        System.out.println(unsafe.pageSize());
    }
}