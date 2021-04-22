import java.sql.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Service s = new Service(1, "Aaa", 1.1, 10, "Aaa", 2.2);
        ServiceManager.insert(s);

        System.out.println(ServiceManager.getById(1));
    }
}
