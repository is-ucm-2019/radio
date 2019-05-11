package radio.dao;

import radio.core.Database;
import radio.core.Playlist;
import radio.transfer.PlaylistTransfer;

import java.util.List;
import java.util.stream.Collectors;

public class PlaylistDAO implements IAppDAO<PlaylistTransfer> {
    private Database database;

    public PlaylistDAO(Database db) {
        this.database = db;
    }

    @Override
    public boolean exists(PlaylistTransfer el) {
        return database.playlistExists(el.title);
    }

    @Override
    public void persist(PlaylistTransfer el) {
        database.persistPlaylist(new Playlist(el));
    }

    @Override
    public void delete(PlaylistTransfer el) {
        database.removePlaylist(el.title);
    }

    @Override
    public List<PlaylistTransfer> loadAll() {
        return database.getPlaylists().stream().map(PlaylistTransfer::new).collect(Collectors.toList());
    }
}
