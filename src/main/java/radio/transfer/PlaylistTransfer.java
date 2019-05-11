package radio.transfer;

import radio.core.Playlist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlaylistTransfer {
    public final String title;
    public final String description;
    public final List<SongTransfer> songs;

    public PlaylistTransfer(Playlist p) {
        this.title = p.getTitle();
        this.description = p.getDescription();
        this.songs = p.getSongs().stream().map(SongTransfer::new).collect(Collectors.toList());
    }

    public PlaylistTransfer(String title, String description) {
        this.title = title;
        this.description = description;
        this.songs = new ArrayList<>();
    }

    public String toString() {
        return title;
    }
}
