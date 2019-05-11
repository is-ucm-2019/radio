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

    public void validSong(SongTransfer chosen, Consumer<String> success, Consumer<String> failure) {
        if (this.controller.core.songExists(chosen)) {
            failure.accept("Chosen song already exists, please choose again");
            return;
        }

        int songPrice = chosen.license.getPrice();
        boolean needsPayment = songPrice != 0;

        if (needsPayment && !this.controller.core.hasBankingInfo()) {
            failure.accept("The selected song has a copyright license, but no banking info is present");
            return;
        }

        this.controller.core.saveSong(chosen);
        success.accept(needsPayment ? String.format("Song saved. %d€ have been charged to your account", songPrice) : "Song saved succesfully");
    }

    public void addObserver(Observer o) {
        this.controller.addObserver(o);
    }
}
