import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.io.*;

public class FileServiceImpl extends UnicastRemoteObject implements FileService {

    // Constructor required
    protected FileServiceImpl() throws RemoteException {
        super();
    }

    // Actual logic: read file from server machine
    public String readFile(String fileName) throws RemoteException {
        StringBuilder content = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
            br.close();

        } catch (IOException e) {
            return "Error reading file: " + e.getMessage();
        }

        return content.toString();
    }
}