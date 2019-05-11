package radio.core;

import java.util.concurrent.ThreadLocalRandom;

public class CopyrightLicense extends SongLicense {
    @Override
    public int getPrice() {
        return ThreadLocalRandom.current().nextInt(0, 20);
    }

    public String toString() {
        return "© Foo Corp";
    }
}
