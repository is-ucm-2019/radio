package radio.core;

public enum SongLicense {
    PUBLIC_DOMAIN,
    COPYRIGHTED;

    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase().replace('_', ' ');
    }
}
