package radio.core;

public class PublicDomainLicense extends SongLicense {
    @Override
    public int getPrice() {
        return 0;
    }

    public String toString() {
        return "Public Domain";
    }
}
