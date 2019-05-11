package radio.dao;

import radio.core.Database;
import radio.core.Song;
import radio.core.SongKey;
import radio.transfer.SongTransfer;

import java.util.List;
import java.util.stream.Collectors;

public class SongDAO implements IAppDAO<SongTransfer> {
    private Database database;

    public SongDAO(Database db) {
        this.database = db;
    }

    @Override
    public boolean exists(SongTransfer el) {
        SongKey key = new SongKey(el.title, el.author, el.album, el.year);
        return database.songExists(key);
    }

    @Override
    public void persist(SongTransfer el) {
        database.persistSong(new Song(el));
    }

    @Override
    public void delete(SongTransfer el) {
        database.removeSong(new Song(el).getKey());
    }

    @Override
    public List<SongTransfer> loadAll() {
        return database.getSongs().stream().map(SongTransfer::new).collect(Collectors.toList());
    }
}
