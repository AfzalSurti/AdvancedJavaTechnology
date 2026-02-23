import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SystemInfoService extends Remote {
    SystemInfo getSystemInfo() throws RemoteException;
}