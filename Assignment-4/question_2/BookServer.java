import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BookServer {

    public static void main(String[] args) {
        try {
            // Start registry (avoid crash if already running)
            try {
                LocateRegistry.createRegistry(1099);
                System.out.println("✅ RMI Registry started on port 1099");
            } catch (Exception e) {
                System.out.println("ℹ️ Registry already running on port 1099");
            }

            BookService service = new BookServiceImpl();

            Registry registry = LocateRegistry.getRegistry(1099);
            registry.rebind("BookService", service);

            System.out.println("✅ BookServer is ready. Service name: BookService");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}