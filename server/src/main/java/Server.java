import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    private ClientServiceInterface clientService;
    private TableEditorServiceInterface tableEditorService;
    private DAO dao;
    public static final String CLIENT_SERVICE_BINDING_NAME = "clientService";
    public static final String TABLE_EDITOR_SERVICE_BINDING_NAME = "tableEditorService";

    public Server() {
        dao = (DAO) AbstractXmlApplicationContextSingleton.getInstance().getBean("DAOImpl");
        clientService = new ClientServiceImpl(dao);
        tableEditorService = new TableEditorServiceImpl(dao);
        try {
            startRMI();
        } catch (RemoteException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void stopServer(){
        stopRMI();
    }

    private void startRMI() throws RemoteException {
        Registry registry;

        try {
            registry = LocateRegistry.createRegistry(2099);
        } catch (Exception e) {
            registry = LocateRegistry.getRegistry("127.0.0.1", 2099);
        }

        Remote clientStub = UnicastRemoteObject.exportObject(clientService, 0);
        Remote tableEditorStub = UnicastRemoteObject.exportObject(tableEditorService, 0);
        // bind the object to the server
        registry.rebind(CLIENT_SERVICE_BINDING_NAME, clientStub);
        registry.rebind(TABLE_EDITOR_SERVICE_BINDING_NAME, tableEditorStub);
    }

    private void stopRMI() {
        try {
            // access the service
            Registry rmiRegistry = LocateRegistry.getRegistry("127.0.0.1", 2099);
            ClientServiceInterface clientService = (ClientServiceInterface) rmiRegistry.lookup(CLIENT_SERVICE_BINDING_NAME);
            TableEditorServiceInterface tableEditorService = (TableEditorServiceInterface) rmiRegistry.lookup(TABLE_EDITOR_SERVICE_BINDING_NAME);

            rmiRegistry.unbind(CLIENT_SERVICE_BINDING_NAME);
            rmiRegistry.unbind(TABLE_EDITOR_SERVICE_BINDING_NAME);

            // get rid of the service object
            UnicastRemoteObject.unexportObject(clientService, true);
            UnicastRemoteObject.unexportObject(tableEditorService, true);

            // get rid of the rmi registry
            UnicastRemoteObject.unexportObject(rmiRegistry, true);

        } catch (Exception e){}
    }
}
