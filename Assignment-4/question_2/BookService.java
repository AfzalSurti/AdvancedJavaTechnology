import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BookService extends Remote {
    Book getBookById(int id) throws RemoteException;
    Book getBookByTitle(String title) throws RemoteException;
}