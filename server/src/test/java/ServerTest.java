import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static org.junit.Assert.*;

public class ServerTest {
    private Server server;

    @Before
    public void setUp() throws Exception {
        server = new Server();
    }

    @After
    public void tearDown() throws Exception {
        server.stopServer();
    }

    @Test
    public void testRMIConnection() {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 2099);
            ClientServiceInterface service = (ClientServiceInterface) registry.lookup(Server.CLIENT_SERVICE_BINDING_NAME);
            service.getAutoCompletionDataSnapshot();
        } catch (Exception e) {
            fail("Cannot connect to the server");
        }
    }
}