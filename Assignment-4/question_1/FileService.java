import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FileService extends Remote {

    // Remote method to read file
    String readFile(String fileName) throws RemoteException;
}