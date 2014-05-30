public interface DAO {
    String getOrganizationName();

    String getCodeOKPO();

    String getOrganizationAddress();

    String getOrganizationPhoneNumber();

    String getNextListNumber();

    void offerOrganizationName(String organizationName);

    void offerCodeOKPO(String codeOKPO);

    void offerOrganizationAddress(String organizationAddress);

    void offerOrganizationPhoneNumber(String organizationPhoneNumber);

    void offerListNumber(Integer listNumber);
}
