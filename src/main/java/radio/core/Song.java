package radio.core;

import radio.transfer.SongTransfer;
import radio.util.SongDuration;

public class Song extends PersistentObject<SongKey> {
    private SongKey key;
    private SongDuration runningTime;
    private SongLicense license;

    public Song(String title, String author, String album, int year, SongDuration runningTime, SongLicense license) {
        this.key = new SongKey(title, author, album, year);
        this.runningTime = runningTime;
        this.license = license;
    }

    public Song(SongTransfer tr) {
        this.key = new SongKey(tr.title, tr.author, tr.album, tr.year);
        this.runningTime = tr.duration;
        this.license = tr.license;
    }

    @Override
    public SongKey getKey() {
        return key;
    }

    public String getTitle() {
        return key.getTitle();
    }

    public String getAuthor() {
        return key.getAuthor();
    }

    public String getAlbum() {
        return key.getAlbum();
    }

    public int getYear() {
        return key.getYear();
    }

    public SongDuration getRunningTime() {
        return runningTime;
    }

    public SongLicense getLicense() {
        return license;
    }
}
