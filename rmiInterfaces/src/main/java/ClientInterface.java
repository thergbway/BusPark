import java.util.Date;
import java.util.List;

public interface ClientInterface {
    /**
     * Возвращает актуальный на момент запроса список вариантов для автозаполнения
     * @return список вариантов для автозаполнения
     */
    AutoCompletionDataSnapshot getAutoCompletionDataSnapshot();

    /**
     * Отправляет на сервер данные введенные на форме для того, чтобы
     * эти данные были обработаны и сохранены, если нужно.
     * @param data данные
     */
    void offerTypedDataToSave(DataTypedOnTheForm data);
}
