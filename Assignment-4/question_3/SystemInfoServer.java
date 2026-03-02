import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SystemInfoServer {
    public static void main(String[] args) {
        try {
            // Start registry safely
            try {
                LocateRegistry.createRegistry(1099);
                System.out.println("✅ RMI Registry started on port 1099");
            } catch (Exception e) {
                System.out.println("ℹ️ Registry already running on port 1099");
            }

            SystemInfoService service = new SystemInfoServiceImpl();

            Registry registry = LocateRegistry.getRegistry(1099);
            registry.rebind("SystemInfoService", service);

            System.out.println("✅ SystemInfoServer ready (service name: SystemInfoService)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}