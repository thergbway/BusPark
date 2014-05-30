import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public interface ClientServiceInterface extends Remote{
    /**
     * Возвращает актуальный на момент запроса список вариантов для автозаполнения
     * @return список вариантов для автозаполнения
     */
    AutoCompletionDataSnapshot getAutoCompletionDataSnapshot() throws RemoteException;

    /**
     * Отправляет на сервер данные введенные на форме для того, чтобы
     * эти данные были обработаны и сохранены, если нужно.
     * @param data данные
     */
    void offerTypedDataToSave(DataTypedOnTheForm data) throws RemoteException;
}
