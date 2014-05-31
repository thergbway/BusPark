import java.util.Date;
import java.util.List;

public interface DAO {
    Date getCurrentDate();

    String getOrganizationName();

    String getCodeOKPO();

    String getOrganizationAddress();

    String getOrganizationPhoneNumber();

    String getNextListNumber();

    String getNextListNumberWithoutIncrementing();

    List<String> getBusBrandList();

    List<String> getBusLicensePlateList();

    List<String> getBusDriverFullNameList();

    List<String> getBusConductorFullNameList();

    List<String> getRouteNameList();

    void offerOrganizationName(String organizationName);

    void offerCodeOKPO(String codeOKPO);

    void offerOrganizationAddress(String organizationAddress);

    void offerOrganizationPhoneNumber(String organizationPhoneNumber);

    void offerListNumber(Integer listNumber);

    void setNextListNumber(Integer nextListNumber);

    void offerBusBrand(String busBrand);

    void offerBusLicensePlate(String busLicensePlate);

    void offerBusDriverFullName(String busDriverFullName);

    void offerBusConductorFullName(String busConductorFullName);

    void offerRouteName(String routeName);

    void deleteBusBrand(String busBrand);

    void deleteBusLicensePlate(String busLicensePlate);

    void deleteBusDriverFullName(String busDriverFullName);

    void deleteBusConductorFullName(String busConductorFullName);

    void deleteRouteName(String routeName);
}
