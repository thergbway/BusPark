public class ClientServiceImpl implements ClientServiceInterface {
    private DAO dao;

    public ClientServiceImpl(DAO dao) {
        this.dao = dao;
    }

    @Override
    synchronized public AutoCompletionDataSnapshot getAutoCompletionDataSnapshot() {
        return null;
    }

    @Override
    synchronized public void offerTypedDataToSave(DataTypedOnTheForm data) {
    }
}