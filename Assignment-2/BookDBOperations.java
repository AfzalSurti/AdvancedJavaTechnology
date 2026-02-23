import java.sql.*;

public class BookDBOperations {

    static final String DB_URL = "jdbc:sqlite:Books.db";

    // 1️⃣ INSERT BOOK
    public static void insertBook(int id, String name, String author,
                                  String pub, String date,
                                  double price, int qty) {

        String sql = "INSERT INTO Book VALUES(?,?,?,?,?,?,?,?)";

        try (Connection con = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, author);
            ps.setString(4, pub);
            ps.setString(5, date);
            ps.setDouble(6, price);
            ps.setInt(7, qty);
            ps.setDouble(8, price * qty);

            ps.executeUpdate();
            System.out.println("Book inserted successfully");

        } catch (SQLException e) {
            System.out.println("Insert failed: " + e.getMessage());
        }
    }

    // 2️⃣ QUERY ALL BOOKS
    public static void displayBooks() {

        String sql = "SELECT * FROM Book";

        try (Connection con = DriverManager.getConnection(DB_URL);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\nBOOK TABLE RECORDS");
            System.out.println("-----------------------------------------");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("bookId") + " | " +
                        rs.getString("bookName") + " | " +
                        rs.getString("authorNames") + " | " +
                        rs.getString("publication") + " | " +
                        rs.getDouble("priceOfBook") + " | " +
                        rs.getInt("totalQuantityToOrder") + " | " +
                        rs.getDouble("totalCost")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3️⃣ UPDATE BOOK
    public static void updateBookPrice(int id, double newPrice) {

        String sql = """
                UPDATE Book
                SET priceOfBook = ?, totalCost = ? * totalQuantityToOrder
                WHERE bookId = ?
                """;

        try (Connection con = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, newPrice);
            ps.setDouble(2, newPrice);
            ps.setInt(3, id);

            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Book updated successfully");
            else
                System.out.println("Book ID not found");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 4️⃣ DELETE BOOK
    public static void deleteBook(int id) {

        String sql = "DELETE FROM Book WHERE bookId = ?";

        try (Connection con = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Book deleted successfully");
            else
                System.out.println("Book ID not found");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
