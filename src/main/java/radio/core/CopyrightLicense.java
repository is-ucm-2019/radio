package radio.core;

import java.util.concurrent.ThreadLocalRandom;

public class CopyrightLicense extends SongLicense {
    private int price;
    private String licenseHolder;

    public CopyrightLicense() {
        price = ThreadLocalRandom.current().nextInt(0, 20);
        licenseHolder = randomName();
    }

    @Override
    public int getPrice() {
        return price;
    }

    public String toString() {
        return String.format("© %s", licenseHolder);
    }

    private String randomName() {
        String[] names = {
                "Kemado Records Inc.",
                "Mexican Summer",
                "Hardly Art",
                "Sony Music",
                "Drag City Inc.",
                "Sub Pop Records",
                "Rough Trade Records Inc.",
                "Warp Records",
                "Erased Tapes Records Ltd.",
                "4AD Ltd."
        };

        return names[ThreadLocalRandom.current().nextInt(0, names.length - 1)];
    }
}
