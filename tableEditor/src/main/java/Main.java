import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) {
        TableEditorServiceInterface service = getService();
        new View(service);

    }

    private static TableEditorServiceInterface getService() {
        TableEditorServiceInterface service = null;
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 2099);
            service = (TableEditorServiceInterface) registry.lookup("tableEditorService");
        }catch (Exception e){
            e.printStackTrace();
        }

        return service;
    }
}
