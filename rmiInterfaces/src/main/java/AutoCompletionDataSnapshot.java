import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс, представляющий варианты для автозаполнения
 * всех элементов формы, сохраненные на сервере, и актуальные на момент создания экземпляра класса
 */
public class AutoCompletionDataSnapshot {
    //номер следующего путевого листа или -1, если сервер ничего не может предложить
    public int nextListNumber = -1;

    //текущая дата на сервере
    public Date currentDate = new Date();

    //название организации
    public String organizationName = "";

    //адрес организации
    public String organizationAddress = "";

    //номер телефона организации
    public String organizationPhoneNumber = "";

    //код ОКПО организации
    public String codeOKPO = "";

    //список марок автобусов
    public List<String> busBrandsList = new LinkedList<String>();

    //список номеров автобусов
    public List<String> busLicensePlatesList = new LinkedList<String>();

    //список ФИО водителей
    public List<String> driverFullNamesList = new LinkedList<String>();

    //список ФИО кондукторов
    public List<String> conductorFullNamesList = new LinkedList<String>();

    //список названий маршрутов
    public List<String> routeNamesList = new LinkedList<String>();
}
