package radio.dao;

import radio.core.Database;
import radio.transfer.AdTransfer;
import radio.transfer.AdvertiserTransfer;

import java.util.ArrayList;
import java.util.List;

public class AdDAO implements IAppDAO<AdTransfer> {
    Database database;

    public AdDAO(Database db) {
        this.database = db;
    }

    @Override
    public boolean exists(AdTransfer el) {
        return false;
    }

    @Override
    public void persist(AdTransfer el) {

    }

    @Override
    public void delete(AdTransfer el) {

    }

    @Override
    public List<AdTransfer> loadAll() {
        return new ArrayList<>();
    }

    public List<AdTransfer> loadFor(AdvertiserTransfer parent) {
        return new ArrayList<>();
    }
}
