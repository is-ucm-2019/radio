package radio.dao;

import radio.core.Database;
import radio.transfer.AdvertiserTransfer;

import java.util.ArrayList;
import java.util.List;

public class AdvertiserDAO implements IAppDAO<AdvertiserTransfer> {
    private Database database;

    public AdvertiserDAO(Database db) {
        this.database = db;
    }

    @Override
    public boolean exists(AdvertiserTransfer el) {
        return false;
    }

    @Override
    public void persist(AdvertiserTransfer el) {

    }

    @Override
    public void delete(AdvertiserTransfer el) {

    }

    @Override
    public List<AdvertiserTransfer> loadAll() {
        return new ArrayList<>();
    }
}
