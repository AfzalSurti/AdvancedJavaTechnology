import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SystemInfoClient {
    public static void main(String[] args) {
        try {
            // Change to remote IP if server is on another machine
            String host = "localhost";

            Registry registry = LocateRegistry.getRegistry(host, 1099);
            SystemInfoService service = (SystemInfoService) registry.lookup("SystemInfoService");

            SystemInfo info = service.getSystemInfo();
            System.out.println(info);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}