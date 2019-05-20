package radio.dao;

import radio.core.Advertiser;
import radio.core.Database;
import radio.transfer.AdvertiserTransfer;

import java.util.List;
import java.util.stream.Collectors;

public class AdvertiserDAO implements IAppDAO<AdvertiserTransfer> {
    private Database database;

    public AdvertiserDAO(Database db) {
        this.database = db;
    }

    @Override
    public boolean exists(AdvertiserTransfer el) {
        return database.advertiserExists(el.name);
    }

    @Override
    public void persist(AdvertiserTransfer el) {
        database.persistAdvertiser(new Advertiser(el));
    }

    @Override
    public void delete(AdvertiserTransfer el) {
        database.removeAdvertiser(el.name);
    }

    @Override
    public List<AdvertiserTransfer> loadAll() {
        return database.getAdvertisers()
                .stream()
                .map(AdvertiserTransfer::new)
                .collect(Collectors.toList());
    }
}
