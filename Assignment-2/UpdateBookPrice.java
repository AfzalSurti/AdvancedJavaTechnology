import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UpdateBookPrice {

    public static void main(String[] args) {

        Connection con = null;
        Statement stmt = null;

        try {
            // Load SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Connect to database
            con = DriverManager.getConnection("jdbc:sqlite:Books.db");
            System.out.println("Connected to database");

            // Create statement
            stmt = con.createStatement();
                    BookDBOperations.displayBooks();

            // Update price logic
            String sql = """
                UPDATE Book
                SET priceOfBook =
                    CASE
                        WHEN priceOfBook > 500 THEN priceOfBook * 1.10
                        ELSE priceOfBook * 1.05
                    END;
            """;

            int rows = stmt.executeUpdate(sql);

            System.out.println("Price updated successfully");
            System.out.println("Rows affected: " + rows);
                    BookDBOperations.displayBooks();

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
