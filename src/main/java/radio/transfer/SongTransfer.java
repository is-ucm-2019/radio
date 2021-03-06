package radio.transfer;

import radio.core.Song;
import radio.core.SongLicense;
import radio.util.SongDuration;

public class SongTransfer {
    public final String title;
    public final String album;
    public final String author;
    public final int year;
    public final SongDuration duration;
    public final SongLicense license;

    public SongTransfer(String title, String author, String album, int year, SongDuration duration, SongLicense license) {
        this.title = title;
        this.album = album;
        this.author = author;
        this.year = year;
        this.duration = duration;
        this.license = license;
    }

    public SongTransfer(Song s) {
        this.title = s.getTitle();
        this.album = s.getAlbum();
        this.author = s.getAuthor();
        this.year = s.getYear();
        this.duration = s.getRunningTime();
        this.license = s.getLicense();
    }

    public String toString() {
        return String.format("[%s] %s — %s — %s (%s) (%s)",
                duration.toString(),
                title,
                author,
                album,
                year,
                license.toString());
    }
}
