public class ClientServiceImpl implements ClientServiceInterface {
    private DAO dao;

    public ClientServiceImpl(DAO dao) {
        this.dao = dao;
    }

    @Override
    synchronized public AutoCompletionDataSnapshot getAutoCompletionDataSnapshot() {
        AutoCompletionDataSnapshot snapshot = new AutoCompletionDataSnapshot();

        snapshot.nextListNumber = Integer.parseInt(dao.getNextListNumber());
        snapshot.currentDate = dao.getCurrentDate();
        snapshot.organizationName = dao.getOrganizationName();
        snapshot.organizationAddress = dao.getOrganizationAddress();
        snapshot.organizationPhoneNumber = dao.getOrganizationPhoneNumber();
        snapshot.codeOKPO = dao.getCodeOKPO();
        snapshot.busBrandsList = dao.getBusBrandList();
        snapshot.busLicensePlatesList = dao.getBusLicensePlateList();
        snapshot.driverFullNamesList = dao.getBusDriverFullNameList();
        snapshot.conductorFullNamesList = dao.getBusConductorFullNameList();
        snapshot.routeNamesList = dao.getRouteNameList();

        return snapshot;
    }

    @Override
    synchronized public void offerTypedDataToSave(DataTypedOnTheForm data) {
        dao.offerListNumber(data.listNumber);
        dao.offerOrganizationName(data.organizationName);
        dao.offerOrganizationAddress(data.organizationAddress);
        dao.offerOrganizationPhoneNumber(data.organizationPhoneNumber);
        dao.offerCodeOKPO(data.codeOKPO);
        dao.offerBusBrand(data.busBrand);
        dao.offerBusLicensePlate(data.busLicensePlate);
        dao.offerBusDriverFullName(data.driverFullName);
        dao.offerBusConductorFullName(data.conductorFullName);
        dao.offerRouteName(data.routeName);
    }
}