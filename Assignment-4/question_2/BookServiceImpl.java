import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.sql.*;

public class BookServiceImpl extends UnicastRemoteObject implements BookService {

    private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:sqlite:books.db";

    protected BookServiceImpl() throws RemoteException {
        super();
        initDatabase(); // create table + insert sample data (only if empty)
    }

    private void initDatabase() {
        try (Connection con = DriverManager.getConnection(DB_URL)) {

            // 1) Create table if not exists
            try (Statement st = con.createStatement()) {
                st.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS books (
                        id INTEGER PRIMARY KEY,
                        title TEXT NOT NULL,
                        author TEXT NOT NULL,
                        price REAL NOT NULL
                    )
                """);
            }

            // 2) Insert sample data if table is empty
            int count = 0;
            try (Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery("SELECT COUNT(*) AS c FROM books")) {
                if (rs.next()) count = rs.getInt("c");
            }

            if (count == 0) {
                try (PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO books(id, title, author, price) VALUES (?, ?, ?, ?)")) {

                    insert(ps, 1, "Clean Code", "Robert C. Martin", 499);
                    insert(ps, 2, "Effective Java", "Joshua Bloch", 650);
                    insert(ps, 3, "Design Patterns", "GoF", 799);

                    System.out.println("✅ Sample books inserted into SQLite DB (books.db)");
                }
            }

        } catch (SQLException e) {
            System.out.println("DB init error: " + e.getMessage());
        }
    }

    private void insert(PreparedStatement ps, int id, String title, String author, double price) throws SQLException {
        ps.setInt(1, id);
        ps.setString(2, title);
        ps.setString(3, author);
        ps.setDouble(4, price);
        ps.executeUpdate();
    }

    @Override
    public Book getBookById(int id) throws RemoteException {
        String sql = "SELECT id, title, author, price FROM books WHERE id = ?";
        try (Connection con = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RemoteException("DB error: " + e.getMessage(), e);
        }
        return null; // not found
    }

    @Override
    public Book getBookByTitle(String title) throws RemoteException {
        String sql = "SELECT id, title, author, price FROM books WHERE LOWER(title) = LOWER(?)";
        try (Connection con = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, title);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RemoteException("DB error: " + e.getMessage(), e);
        }
        return null; // not found
    }
}