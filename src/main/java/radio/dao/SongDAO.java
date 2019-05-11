package radio.dao;

import radio.core.Database;
import radio.transfer.SongTransfer;

import java.util.ArrayList;
import java.util.List;

public class SongDAO implements IAppDAO<SongTransfer> {
    private Database database;

    public SongDAO(Database db) {
        this.database = db;
    }

    @Override
    public boolean exists(SongTransfer el) {
        return false;
    }

    @Override
    public void persist(SongTransfer el) {

    }

    @Override
    public void delete(SongTransfer el) {

    }

    @Override
    public List<SongTransfer> loadAll() {
        return new ArrayList<>();
    }
}
