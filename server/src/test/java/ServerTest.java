import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static org.junit.Assert.*;

public class ServerTest {
    private Server server;
    private ClientServiceInterface clientService;
    private TableEditorServiceInterface tableEditorService;

    @Before
    public void setUp() throws Exception {
        server = new Server();
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 2099);
            clientService = (ClientServiceInterface) registry.lookup(Server.CLIENT_SERVICE_BINDING_NAME);
            tableEditorService = (TableEditorServiceInterface) registry.lookup(Server.TABLE_EDITOR_SERVICE_BINDING_NAME);
        } catch (Exception e) {
            fail("Cannot connect to the server");
        }
    }

    @After
    public void tearDown() throws Exception {
        server.stopServer();
    }

    @Test
    public void testAutoCompletionDataSnapshot() throws RemoteException {
        AutoCompletionDataSnapshot snapshot = clientService.getAutoCompletionDataSnapshot();
        System.out.println(snapshot);
    }

    @Test
    public void testDataTypedOnTheForm() throws RemoteException {
        DataTypedOnTheForm data = new DataTypedOnTheForm(15, "MyOrg", "Торж 15", "89522444425", "2556210", "Lexus",
                "B255BH98", "Иванов Иван Иванович", "Сидоров Сидор Сидорович", "Церковь-Библиотека");
        clientService.offerTypedDataToSave(data);
        System.out.println(data);
    }

    @Test
    public void testTableEditorService() throws RemoteException {
        System.out.println(tableEditorService.getNextListNumber());
        System.out.println(tableEditorService.getNextListNumber());
        System.out.println(tableEditorService.getNextListNumber());
        System.out.println(tableEditorService.getOrganizationName());
        System.out.println(tableEditorService.getOrganizationAddress());
        System.out.println(tableEditorService.getOrganizationPhoneNumber());
        System.out.println(tableEditorService.getCodeOKPO());
        System.out.println(tableEditorService.getBusBrandList());
        System.out.println(tableEditorService.getBusLicensePlateList());
        System.out.println(tableEditorService.getBusDriverFullNameList());
        System.out.println(tableEditorService.getBusConductorFullNameList());
        System.out.println(tableEditorService.getRouteNameList());
        tableEditorService.addBusBrand("Lexus");
        tableEditorService.addBusBrand("Lada");
        tableEditorService.addBusBrand("Mazda");
        System.out.println(tableEditorService.getBusBrandList());
        tableEditorService.deleteBusBrand("Lexus");
        tableEditorService.deleteBusBrand("Lexus");
        System.out.println(tableEditorService.getBusBrandList());
        tableEditorService.setCodeOKPO("NULL");
        System.out.println(tableEditorService.getCodeOKPO());
    }
}