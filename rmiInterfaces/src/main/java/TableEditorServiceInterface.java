import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface TableEditorServiceInterface extends Remote {
    public int getNextListNumber() throws RemoteException;

    public String getOrganizationName() throws RemoteException;

    public String getOrganizationAddress() throws RemoteException;

    public String getOrganizationPhoneNumber() throws RemoteException;

    public String getCodeOKPO() throws RemoteException;

    public List<String> getBusBrandList() throws RemoteException;

    public List<String> getBusLicensePlateList() throws RemoteException;

    public List<String> getBusDriverFullNameList() throws RemoteException;

    public List<String> getBusConductorFullNameList() throws RemoteException;

    public List<String> getRouteNameList() throws RemoteException;

    public void setNextListNumber(Integer nextListNumber) throws RemoteException;

    public void setOrganizationName(String organizationName) throws RemoteException;

    public void setOrganizationAddress(String organizationAddress) throws RemoteException;

    public void setOrganizationPhoneNumber(String organizationPhoneNumber) throws RemoteException;

    public void setCodeOKPO(String codeOKPO) throws RemoteException;

    public void addBusBrand(String newBusBrand) throws RemoteException;

    public void deleteBusBrand(String busBrandToBeDeleted) throws RemoteException;

    public void addBusLicensePlate(String newBusLicensePlate) throws RemoteException;

    public void deleteBusLicensePlate(String busLicensePlateToBeDeleted) throws RemoteException;

    public void addBusDriverFullName(String newBusDriverFullName) throws RemoteException;

    public void deleteBusDriverFullName(String busDriverFullNameToBeDeleted) throws RemoteException;

    public void addBusConductorFullName(String newBusConductorFullName) throws RemoteException;

    public void deleteBusConductorFullName(String busConductorFullNameToBeDeleted) throws RemoteException;

    public void addRouteName(String newRouteName) throws RemoteException;

    public void deleteRouteName(String routeNameToBeDeleted) throws RemoteException;
}
