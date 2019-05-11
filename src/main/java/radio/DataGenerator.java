package radio;

import radio.core.*;
import radio.util.BroadcastTime;
import radio.util.SongDuration;
import radio.util.ThemeSchedule;
import radio.util.TimeUtil;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class DataGenerator {
    static void fillData(Database db) {
        fillBankingInfo(db);
        fillUsers(db);
        fillPrograms(db);
        fillThemes(db);
        fillSongs(db);
    }

    private static void fillBankingInfo(Database db) {
        BankingInfo info = new BankingInfo("Radio Enterprises Inc.",
                "0000 0000 0000 0000",
                123,
                LocalDate.of(2020, 4, 1));
        db.setBankingInfo(info);
    }

    private static void fillUsers(Database db) {
        for (UserPermission perm : UserPermission.values()) {
            String name = perm.toString();
            db.persistUser(new User(name, name, name, String.format("%s@example.org", name), "+1 555-555", perm));
        }
    }

    private static void fillPrograms(Database db) {
        List<String> programNames = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String name = String.format("Program %d", i);
            String description = String.format("Description for %s", name);
            programNames.add(name);
            db.persistProgram(new Program(name, description, Color.ORANGE));
        }

        LocalDateTime currDay = TimeUtil.firstDayOfWeekFrom(LocalDate.now()).atTime(0, 0);
        for (String name : programNames) {
            for (int i = 0; i < 7; i++) {
                LocalDateTime start = currDay.withHour(ThreadLocalRandom.current().nextInt(9, 17 + 1));
                LocalDateTime end = start.plusHours(ThreadLocalRandom.current().nextInt(1, 4 + 1));
                Broadcast b = new Broadcast(new BroadcastTime(start, end));
                db.persistBroadcast(name, b);
                currDay = currDay.plusDays(1);
            }
        }
    }

    private static void fillThemes(Database db) {
        for (int i = 0; i < 5; i++) {
            ThemeSchedule sched = new ThemeSchedule(LocalDate.of(2019, 6 + i, 1),
                                                    LocalDate.of(2019, 7 + i, 1));

            String name = String.format("Theme %s", i);
            String descr = String.format("Description for %s", name);
            db.persistTheme(new Theme(name, descr, sched));
        }
    }

    private static void fillSongs(Database db) {
        for (int i = 0; i < 20; i++) {
            String title = String.format("Song %s", i);
            String author = String.format("Author %s", i);
            String album = String.format("Album %s", i);
            int year = 1999 + i;
            SongDuration duration = new SongDuration(4, 20);
            db.persistSong(new Song(title, author, album, year, duration, new PublicDomainLicense()));
        }
    }
}
