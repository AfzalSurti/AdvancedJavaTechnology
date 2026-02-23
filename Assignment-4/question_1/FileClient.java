import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class FileClient {

    public static void main(String[] args) {
        try {
            // Connect to remote machine (change IP!)
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Lookup remote object
            FileService service = (FileService) registry.lookup("FileService");

            // Call remote method
            String data = service.readFile("test.txt");

            System.out.println("File Content:\n" + data);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}