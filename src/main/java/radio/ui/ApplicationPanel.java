package radio.ui;

public enum ApplicationPanel {
    LOGIN,
    LANDING,
    PLANNING,
    EVENTS,
    TICKETS,
    ADVERTISERS,
    MUSIC_ARCHIVE,
    PLAYLISTS,
    THEMES;

    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase().replace('_', ' ');
    }
}
