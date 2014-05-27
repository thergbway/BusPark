/**
 * Класс, представляющий собой данные, которые были введены на форме.
 * Нужен для отправки на сервер
 */
public class DataTypedOnTheForm {
    //номер введенного путевого листа
    public final int listNumber;

    //название организации
    public final String organizationName;

    //адрес организации
    public final String organizationAddress;

    //номер телефона организации
    public final String organizationPhoneNumber;

    //код ОКПО организации
    public final String codeOKPO;

    //марка автобусов
    public final String busBrand;

    //номер автобусов
    public final String busLicensePlate;

    //ФИО водителя
    public final String driverFullName;

    //ФИО кондуктора
    public final String conductorFullName;

    //название маршрута
    public final String routeName;

    public DataTypedOnTheForm(int listNumber, String organizationName, String organizationAddress,
                              String organizationPhoneNumber, String codeOKPO, String busBrand,
                              String busLicensePlate, String driverFullName,
                              String conductorFullName, String routeName) {
        this.listNumber = listNumber;
        this.organizationName = organizationName;
        this.organizationAddress = organizationAddress;
        this.organizationPhoneNumber = organizationPhoneNumber;
        this.codeOKPO = codeOKPO;
        this.busBrand = busBrand;
        this.busLicensePlate = busLicensePlate;
        this.driverFullName = driverFullName;
        this.conductorFullName = conductorFullName;
        this.routeName = routeName;
    }
}
