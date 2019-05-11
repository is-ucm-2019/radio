package radio.core;

import java.io.Serializable;

public enum SongLicense implements Serializable {
    PUBLIC_DOMAIN,
    COPYRIGHTED;

    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase().replace('_', ' ');
    }
}
