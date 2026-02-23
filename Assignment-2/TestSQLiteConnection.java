import java.sql.Connection;
import java.sql.DriverManager;

public class TestSQLiteConnection {

    public static void main(String[] args) {
        try {
            // Load SQLite Driver
            Class.forName("org.sqlite.JDBC");

            // Connect to SQLite DB (creates file automatically)
            Connection con = DriverManager.getConnection("jdbc:sqlite:test.db");

            System.out.println("SQLite connected successfully!");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
