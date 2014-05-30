import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    private ClientServiceInterface clientService;
    private DAO dao;
    public static final String CLIENT_SERVICE_BINDING_NAME = "clientService";

    public Server() {
        dao = (DAO) AbstractXmlApplicationContextSingleton.getInstance().getBean("DAOImpl");
        clientService = new ClientServiceImpl(dao);
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

        Remote stub = UnicastRemoteObject.exportObject(clientService, 0);
        // bind the object to the server
        registry.rebind(CLIENT_SERVICE_BINDING_NAME, stub);
    }

    private void stopRMI() {
        try {
            // access the service
            Registry rmiRegistry = LocateRegistry.getRegistry("127.0.0.1", 2099);
            ClientServiceInterface myService = (ClientServiceInterface) rmiRegistry.lookup(CLIENT_SERVICE_BINDING_NAME);

            rmiRegistry.unbind(CLIENT_SERVICE_BINDING_NAME);

            // get rid of the service object
            UnicastRemoteObject.unexportObject(myService, true);

            // get rid of the rmi registry
            UnicastRemoteObject.unexportObject(rmiRegistry, true);

        } catch (Exception e){}
    }
}
