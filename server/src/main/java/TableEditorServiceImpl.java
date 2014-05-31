import javax.management.RuntimeMBeanException;
import java.util.List;

public class TableEditorServiceImpl implements TableEditorServiceInterface {
    private DAO dao;

    public TableEditorServiceImpl(DAO dao) {
        this.dao = dao;
    }

    @Override
    synchronized public int getNextListNumber() {
        return Integer.parseInt(dao.getNextListNumberWithoutIncrementing());
    }

    @Override
    synchronized public String getOrganizationName() {
        return dao.getOrganizationName();
    }

    @Override
    synchronized public String getOrganizationAddress() {
        return dao.getOrganizationAddress();
    }

    @Override
    synchronized public String getOrganizationPhoneNumber() {
        return dao.getOrganizationPhoneNumber();
    }

    @Override
    synchronized public String getCodeOKPO() {
        return dao.getCodeOKPO();
    }

    @Override
    synchronized public List<String> getBusBrandList() {
        return dao.getBusBrandList();
    }

    @Override
    synchronized public List<String> getBusLicensePlateList() {
        return dao.getBusLicensePlateList();
    }

    @Override
    synchronized public List<String> getBusDriverFullNameList() {
        return dao.getBusDriverFullNameList();
    }

    @Override
    synchronized public List<String> getBusConductorFullNameList() {
        return dao.getBusConductorFullNameList();
    }

    @Override
    synchronized public List<String> getRouteNameList() {
        return dao.getRouteNameList();
    }

    @Override
    synchronized public void setNextListNumber(Integer nextListNumber) {
        dao.setNextListNumber(nextListNumber);
    }

    @Override
    synchronized public void setOrganizationName(String organizationName) {
        dao.offerOrganizationName(organizationName);
    }

    @Override
    synchronized public void setOrganizationAddress(String organizationAddress) {
        dao.offerOrganizationAddress(organizationAddress);
    }

    @Override
    synchronized public void setOrganizationPhoneNumber(String organizationPhoneNumber) {
        dao.offerOrganizationPhoneNumber(organizationPhoneNumber);
    }

    @Override
    synchronized public void setCodeOKPO(String codeOKPO) {
        dao.offerCodeOKPO(codeOKPO);
    }

    @Override
    synchronized public void addBusBrand(String newBusBrand) {
        dao.offerBusBrand(newBusBrand);
    }

    @Override
    synchronized public void deleteBusBrand(String busBrandToBeDeleted) {
        dao.deleteBusBrand(busBrandToBeDeleted);
    }

    @Override
    synchronized public void addBusLicensePlate(String newBusLicensePlate) {
        dao.offerBusLicensePlate(newBusLicensePlate);
    }

    @Override
    synchronized public void deleteBusLicensePlate(String busLicensePlateToBeDeleted) {
        dao.deleteBusLicensePlate(busLicensePlateToBeDeleted);
    }

    @Override
    synchronized public void addBusDriverFullName(String newBusDriverFullName) {
        dao.offerBusDriverFullName(newBusDriverFullName);
    }

    @Override
    synchronized public void deleteBusDriverFullName(String busDriverFullNameToBeDeleted) {
        dao.deleteBusDriverFullName(busDriverFullNameToBeDeleted);
    }

    @Override
    synchronized public void addBusConductorFullName(String newBusConductorFullName) {
        dao.offerBusConductorFullName(newBusConductorFullName);
    }

    @Override
    synchronized public void deleteBusConductorFullName(String busConductorFullNameToBeDeleted) {
        dao.deleteBusConductorFullName(busConductorFullNameToBeDeleted);
    }

    @Override
    synchronized public void addRouteName(String newRouteName) {
        dao.offerRouteName(newRouteName);
    }

    @Override
    synchronized public void deleteRouteName(String routeNameToBeDeleted) {
        dao.deleteRouteName(routeNameToBeDeleted);
    }
}
