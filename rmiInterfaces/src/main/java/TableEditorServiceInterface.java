import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TableEditorServiceInterface extends Remote {
    String sayGoodBye() throws RemoteException;
}
