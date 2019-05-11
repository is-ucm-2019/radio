package radio.dao;

import radio.core.CopyrightLicense;
import radio.core.PublicDomainLicense;
import radio.core.SongLicense;
import radio.transfer.SongTransfer;
import radio.util.SongDuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

public class SongService {

    private static List<Function<String, String>> songF = Arrays.asList(
            (e -> e + " (radio edit"),
            (e -> e + " (feat T. Pain)"),
            (e -> e + " (remix)"),
            (e -> e + " (live)"),
            (e -> e)
    );

    private static List<Function<String, String>> albumF = Arrays.asList(
            (a -> "The Very best of " + a),
            (a -> a + ": The Best Hits"),
            (a -> a + " (Live Version)"),
            (a -> a + ": Live from Famous Auditorium"),
            (a -> a)
    );

    // Will create a list of songs at random
    public static List<SongTransfer> findMatches(String title, String author, String album, int year) {
        ArrayList<SongTransfer> matches = new ArrayList<>();

        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int matchSize = rand.nextInt(1, 10);
        if (matchSize % 5 == 0) {
            return matches;
        }

        for (int i = 0; i <= matchSize; i++) {
            SongDuration runningTime = new SongDuration(rand.nextInt(0, 6),
                    rand.nextInt(1, 59));

            String matchTitle = songF.get(rand.nextInt(songF.size())).apply(title);
            String matchAlbum = albumF.get(rand.nextInt(albumF.size())).apply(album);
            SongLicense license = rand.nextBoolean() ? new CopyrightLicense() : new PublicDomainLicense();
            matches.add(new SongTransfer(matchTitle, author, matchAlbum, year, runningTime, license));
        }

        return matches;
    }
}
