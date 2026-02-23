import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
       
public class CreateBookTable {

    public static void main(String[] args) {

        Connection con = null;
        Statement stmt = null;

        try {
            // 1. Load SQLite JDBC Driver
            Class.forName("org.sqlite.JDBC");

            // 2. Connect to SQLite Database
            con = DriverManager.getConnection("jdbc:sqlite:Books.db");
            System.out.println("Connected to database");

            // 3. Create Statement
            stmt = con.createStatement();

            // 4. SQL to create Book table
            String sql = """
                CREATE TABLE IF NOT EXISTS Book (
                    bookId INTEGER PRIMARY KEY,
                    bookName TEXT NOT NULL,
                    authorNames TEXT NOT NULL,
                    publication TEXT,
                    dateOfPublication TEXT,
                    priceOfBook REAL,
                    totalQuantityToOrder INTEGER,
                    totalCost REAL
                );
            """;

            // 5. Execute SQL
            stmt.executeUpdate(sql);
            System.out.println("Book table created successfully");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(stmt != null) stmt.close();
                if(con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
