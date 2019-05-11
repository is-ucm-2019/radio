package radio.core;

import radio.transfer.PlaylistTransfer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Playlist extends PersistentObject<String> {
    private String title;
    private String description;
    private List<Song> songs = new ArrayList<>();

    public Playlist(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Playlist(PlaylistTransfer tr) {
        this.title = tr.title;
        this.description = tr.description;
        this.songs = tr.songs.stream().map(Song::new).collect(Collectors.toList());
    }

    @Override
    public String getKey() {
        return title;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addSong(Song s) {
        this.songs.add(s);
    }
}
