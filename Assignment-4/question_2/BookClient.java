import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BookClient {

    public static void main(String[] args) {
        try {
            // Change "localhost" to server IP if running on another machine
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            BookService service = (BookService) registry.lookup("BookService");

            // Example request
            int bookId = 2;
            Book b = service.getBookById(bookId);

            if (b == null) {
                System.out.println("❌ Book not found for ID: " + bookId);
            } else {
                System.out.println(b);
            }

            // Another example (title)
            Book b2 = service.getBookByTitle("Clean Code");
            System.out.println("\n---\nTitle Search Result:\n" + (b2 == null ? "❌ Not found" : b2));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}