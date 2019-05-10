package radio.dao;

import radio.transfer.SongTransfer;

import java.util.ArrayList;
import java.util.List;

public class SongDAO implements AppDAO<SongTransfer> {

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
