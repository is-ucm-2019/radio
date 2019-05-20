package radio.dao;

import radio.core.Ad;
import radio.core.Database;
import radio.transfer.AdTransfer;
import radio.transfer.AdvertiserTransfer;

import java.util.List;
import java.util.stream.Collectors;

public class AdDAO implements IAppDAO<AdTransfer> {
    Database database;

    public AdDAO(Database db) {
        this.database = db;
    }

    @Override
    public boolean exists(AdTransfer el) {
        return database.advertExists(el.parent.name, el.name);
    }

    @Override
    public void persist(AdTransfer el) {
        database.persistAdvert(el.parent.name, new Ad(el));
    }

    @Override
    public void delete(AdTransfer el) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<AdTransfer> loadAll() {
        throw new UnsupportedOperationException();
    }

    public List<AdTransfer> loadFor(AdvertiserTransfer parent) {
        return database.forAdvertiser(parent.name)
                .stream()
                .map(ad -> new AdTransfer(parent, ad))
                .collect(Collectors.toList());
    }
}
