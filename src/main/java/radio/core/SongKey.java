package radio.core;

import java.io.Serializable;

public class SongKey implements Serializable, Comparable<SongKey> {
    private String title;
    private String author;
    private String album;
    private int year;

    public SongKey(String title, String author, String album, int year) {
        this.title = title;
        this.author = author;
        this.album = album;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getAlbum() {
        return album;
    }

    public int getYear() {
        return year;
    }

    @Override
    public int compareTo(SongKey o) {
        boolean equalTitle = (title.toLowerCase().compareTo(o.title.toLowerCase())) == 0;
        boolean equalAuthor = (author.toLowerCase().compareTo(o.author.toLowerCase())) == 0;
        boolean sameYear = year == o.year;
        return (equalTitle && equalAuthor && sameYear) ? 0 : 1;
    }
}
