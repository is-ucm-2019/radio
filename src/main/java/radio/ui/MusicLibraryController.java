package radio.ui;

import radio.transfer.SongTransfer;

import java.util.Observer;
import java.util.function.Consumer;

public class MusicLibraryController {
    private MainController controller;

    public MusicLibraryController(MainController cont) {
        this.controller = cont;
    }

    public void getAllSongs() {
        this.controller.core.loadSongInfo();
    }

    public void searchSongDetails(String title, String author, String album, int year) {
        this.controller.core.searchSongDetails(title, author, album, year);
    }

    // TODO(borja): License dialog
    public void validSong(SongTransfer chosen, Consumer<String> success, Consumer<String> failure) {
        if (this.controller.core.songExists(chosen)) {
            failure.accept("Chosen song already exists, please choose again");
            return;
        }

        this.controller.core.saveSong(chosen);
        success.accept("Song saved succesfully");
    }

    public void addObserver(Observer o) {
        this.controller.addObserver(o);
    }
}
