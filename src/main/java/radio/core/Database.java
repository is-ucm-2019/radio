package radio.core;

import radio.util.BroadcastTime;
import radio.util.ThemeSchedule;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

// This class is a singleton, and the single source of truth of the system
// Contains all the data in the system, that will be accessed through the
// DAOs.
//
// All data objects must be serializable, as this is how the database will
// save the contents to disk.
//
// Attributes starting with _ should not be serialized to disk.
public final class Database {
    // Fist, all the users in the system
    // Users have no further data
    private List<User> users;
    private Map<String, User> _userIndex;

    // A list of all programs
    // Programs have nested broadcasts
    // Broadcasts have nested themes
    // Program -> Broadcast
    private List<Program> programs;
    // Index for programs
    private Map<String, Program> _programIndex;
    // Index to quickly find some specific broadcast
    private Map<UUID, Broadcast> _broadcastIndex;
    // Reverse index of Broadcast -> Program
    private Map<UUID, String> _programForBroadcast;
    // Additional sorted map for broadcasts
    private TreeMap<LocalDateTime, UUID> _broadcastCalendar;

    // A list of all themes
    private List<Theme> themes;
    private Map<String, Theme> _themeIndex;

    // Reversed of Broadcast -> Theme
    private Map<UUID, Set<String>> _themeForBroadcast;

    Database() {
        users = new ArrayList<>();
        _userIndex = new HashMap<>();

        programs = new ArrayList<>();
        _programIndex = new HashMap<>();
        _broadcastIndex = new TreeMap<>();
        _programForBroadcast = new TreeMap<>();
        _broadcastCalendar = new TreeMap<>();

        themes = new ArrayList<>();
        _themeIndex = new TreeMap<>();
        _themeForBroadcast = new TreeMap<>();
    }

    Database(Path path) { }

    public boolean programExists(String key) {
        return this._programIndex.containsKey(key);
    }

    public void persistProgram(Program p) {
        this.programs.add(p);
        this._programIndex.put(p.getKey(), p);
    }

    public void removeProgram(String key) {
        Program p = _programIndex.get(key);
        programs.remove(p);
        _programIndex.remove(key);
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public Optional<Program> getProgram(String key) {
        return Optional.ofNullable(_programIndex.get(key));
    }

    public Program getProgram(Broadcast b) {
        return _programIndex.get(_programForBroadcast.get(b.getKey()));
    }

    public void persistBroadcast(String programTitle, Broadcast b) {
        Program p = _programIndex.get(programTitle);
        p.addBroadcast(b);
        _broadcastIndex.put(b.getKey(), b);
        _programForBroadcast.put(b.getKey(), p.getKey());
        _broadcastCalendar.put(b.getSchedule().getStart(), b.getKey());
    }

    public void removeBroacast(UUID key) {
        Broadcast b = _broadcastIndex.get(key);
        Program p =_programIndex.get(_programForBroadcast.get(b));
        p.removeBroadcast(b);
        _programForBroadcast.remove(key);
        _broadcastIndex.remove(key);
        _broadcastCalendar.remove(b.getSchedule().getStart());
    }

    public List<Broadcast> forWeek(LocalDate firstOfWeek) {
        LocalDateTime startKey = LocalDateTime.of(firstOfWeek, LocalTime.of(0, 0));
        LocalDateTime endKey = LocalDateTime.of(firstOfWeek.plusDays(7), LocalTime.of(23, 59));
        return forRange(startKey, endKey);
    }

    public List<Broadcast> forSchedule(ThemeSchedule sched) {
        LocalDateTime startKey = LocalDateTime.of(sched.getStart(), LocalTime.of(0,0));
        LocalDateTime endKey = LocalDateTime.of(sched.getEnd(), LocalTime.of(23, 59));
        return forRange(startKey, endKey);
    }

    public boolean overlapSchedule(BroadcastTime time) {
        LocalDateTime timeStart = time.getStart();
        LocalDateTime startKey = timeStart.withHour(0).withMinute(0);
        LocalDateTime endKey = timeStart.withHour(23).withMinute(59);
        for (Broadcast b : forRange(startKey, endKey)) {
            if (b.getSchedule().overlaps(time)) {
                return true;
            }
        }
        return false;
    }

    private List<Broadcast> forRange(LocalDateTime start, LocalDateTime end) {
        ArrayList<Broadcast> l = new ArrayList<>();
        for (UUID id : _broadcastCalendar.tailMap(start).values()) {
            Broadcast b = _broadcastIndex.get(id);
            // Break when we're over the range
            if (b.getSchedule().getStart().isAfter(end)) {
                break;
            }

            if (!b.getSchedule().getEnd().isAfter(end)) {
                l.add(b);
            }
        }

        return l;
    }

    public void persistTheme(Theme t) {
        themes.add(t);
        _themeIndex.put(t.getKey(), t);
    }

    public void removeTheme(String title) {
        Theme t = _themeIndex.get(title);
        themes.remove(t);
        _themeIndex.remove(title);
    }

    public boolean themeExists(String title) {
        return _themeIndex.containsKey(title);
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public void addTheme(UUID broadcastKey, String themeName) {
        Set<String> themes = new TreeSet<>();
        themes.add(themeName);

        _themeForBroadcast.merge(broadcastKey, themes, (left, right) -> {
            left.addAll(right);
            return left;
        });
    }
}
