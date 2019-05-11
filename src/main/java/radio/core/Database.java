package radio.core;

import radio.util.BroadcastTime;
import radio.util.PersistenceException;
import radio.util.ThemeSchedule;

import java.io.*;
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
// Attributes starting `transient` will not be serialized to disk.
public final class Database implements Serializable {
    // Fist, all the users in the system
    // Users have no further data
    private List<User> users;
    transient private Map<String, User> _userIndex;

    // A list of all programs
    // Programs have nested broadcasts
    // Broadcasts have nested themes
    // Program -> Broadcast
    private List<Program> programs;
    // Index for programs
    transient private Map<String, Program> _programIndex;
    // Index to quickly find some specific broadcast
    transient private Map<UUID, Broadcast> _broadcastIndex;
    // Reverse index of Broadcast -> Program
    transient private Map<UUID, String> _programForBroadcast;
    // Additional sorted map for broadcasts
    transient private TreeMap<LocalDateTime, UUID> _broadcastCalendar;

    // A list of all themes
    private List<Theme> themes;
    transient private Map<String, Theme> _themeIndex;

    // A list of all songs
    private List<Song> songs;
    // Reverse index of songs
    transient private Map<SongKey, Song> _songIndex;

    // Reversed of Broadcast -> Theme
    private Map<UUID, Set<String>> themeForBroadcast;

    static void toDisk(Database db, String path) throws PersistenceException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
            out.writeObject(db);
        } catch (IOException e) {
            e.printStackTrace();
            throw new PersistenceException("Couldn't persist database to file");
        }
    }

    static Optional<Database> fromDisk(String path) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
            return Optional.of((Database) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    // Flush all data, but skip indices (attributes marked transient)
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    // Read back in data, and autopopulate indices
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        // Update indices
        fillIndices();
    }

    Database() {
        users = new ArrayList<>();
        programs = new ArrayList<>();
        themes = new ArrayList<>();
        themeForBroadcast = new TreeMap<>();
        songs = new ArrayList<>();

        // Update indices
        fillIndices();
    }

    private void fillIndices() {
        // User index
        _userIndex = new HashMap<>();
        for (User u : users) {
            _userIndex.put(u.getKey(), u);
        }

        // Program and Broadcast indices
        _programIndex = new HashMap<>();
        _broadcastIndex = new TreeMap<>();
        _programForBroadcast = new TreeMap<>();
        _broadcastCalendar = new TreeMap<>();
        for (Program p : programs) {
            _programIndex.put(p.getKey(), p);
            for (Broadcast b : p.getBroadcasts()) {
                _broadcastIndex.put(b.getKey(), b);
                _programForBroadcast.put(b.getKey(), p.getKey());
                _broadcastCalendar.put(b.getSchedule().getStart(), b.getKey());
            }
        }

        // Theme indices
        _themeIndex = new TreeMap<>();
        for (Theme t : themes) {
            _themeIndex.put(t.getKey(), t);
        }

        _songIndex = new TreeMap<>();
        for (Song s : songs) {
            _songIndex.put(s.getKey(), s);
        }
    }

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

        themeForBroadcast.merge(broadcastKey, themes, (left, right) -> {
            left.addAll(right);
            return left;
        });
    }

    public boolean songExists(SongKey key) {
        return _songIndex.containsKey(key);
    }

    public void persistSong(Song s) {
        songs.add(s);
        _songIndex.put(s.getKey(), s);
    }

    public void removeSong(SongKey key) {
        Song s = _songIndex.get(key);
        _songIndex.remove(key);
        songs.remove(s);
    }

    public List<Song> getSongs() {
        return songs;
    }
}
