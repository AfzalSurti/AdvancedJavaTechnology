import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class FileServer {

    public static void main(String[] args) {
        try {
            // Create object
            FileServiceImpl obj = new FileServiceImpl();

            // Start RMI Registry on port 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            // Bind object with name "FileService"
            registry.rebind("FileService", obj);

            System.out.println("Server is ready...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}