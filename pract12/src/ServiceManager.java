import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceManager {
    static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/pract1?characterEncoding=UTF8&serverTimeZone=UTC",
                    "root",
                    "tlf96602"
                    );

            String create_sql = "CREATE TABLE IF NOT EXISTS service (" +
                    "id INT(10) NOT NULL AUTO_INCREMENT," +
                    "title VARCHAR(128) NOT NULL," +
                    "cost DECIMAL(19,4) NOT NULL," +
                    "durationInSeconds INT NOT NULL," +
                    "description TEXT(1024) DEFAULT NULL," +
                    "discount DOUBLE DEFAULT 0," +
                    "PRIMARY KEY(id)" +
                    ");";

            Statement s = conn.createStatement();
            s.executeUpdate(create_sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    static Service getById(int id) {
        try {
            String sql = "SELECT * FROM service WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                return new Service(
                        result.getInt("id"),
                        result.getString("title"),
                        result.getDouble("cost"),
                        result.getInt("durationInSeconds"),
                        result.getString("description"),
                        result.getDouble("discount")
                );
            } else
                return null;

        } catch (SQLException e) {
            return null;
        }
    }

    static List<Service> getAll() {
        try {
            String sql = "SELECT * FROM service";
            Statement s = conn.createStatement();
            ResultSet result = s.executeQuery(sql);

            List<Service> services = new ArrayList<>();

            while (result.next()) {
                services.add(new Service(
                        result.getInt("id"),
                        result.getString("title"),
                        result.getDouble("cost"),
                        result.getInt("durationInSeconds"),
                        result.getString("description"),
                        result.getDouble("discount")
                ));
            }
            return services;
        } catch (SQLException e) {
            return null;
        }
    }

    static void insert(Service service) {
        try {
            String sql = "INSERT INTO service VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, service.id);
            ps.setString(2, service.title);
            ps.setDouble(3, service.cost);
            ps.setInt(4, service.durationInSeconds);
            ps.setString(5, service.description);
            ps.setDouble(6, service.discount);
            ps.executeUpdate();
        } catch (SQLException ignored) {
        }
    }

    static void update(Service service) {
        try {
            String sql = "UPDATE service SET title=?, cost=?, durationInSeconds=?, description=?, discount=? WHERE id=?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, service.title);
            ps.setDouble(2, service.cost);
            ps.setInt(3, service.durationInSeconds);
            ps.setString(4, service.description);
            ps.setDouble(5, service.discount);
            ps.setInt(6, service.id);
            ps.executeUpdate();
        } catch (SQLException ignored) {
        }
    }
}
