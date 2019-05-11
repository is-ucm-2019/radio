package radio.core;

import radio.actions.*;
import radio.dao.*;
import radio.transfer.BroadcastTransfer;
import radio.transfer.ProgramTransfer;
import radio.transfer.SongTransfer;
import radio.transfer.ThemeTransfer;

import java.time.LocalDate;
import java.util.*;

public class Core extends Observable {
    private Optional<User> currentUser = Optional.empty();
    private Database db;
    private ProgramDAO programDAO;
    private BroadcastDAO broadcastDAO;
    private ThemeDAO themeDAO;
    private SongDAO songDAO;

    public Core() {
        db = new Database();
        programDAO = new ProgramDAO(db);
        broadcastDAO = new BroadcastDAO(db);
        themeDAO = new ThemeDAO(db);
        songDAO = new SongDAO(db);
    }

    public void dispatchObserver(Observer obs) {
        this.addObserver(obs);
    }

    // TODO(borja): Perform user validation
    public void login(String username, String password) {
        currentUser = Optional.of(new User(username, password));
    }

    public void logout() {
        this.currentUser = Optional.empty();
    }

    // Loads all programs and broadcasts for the week starting on `start`
    public void loadPlanningInfo(LocalDate start) {
        this.setChanged();
        this.notifyObservers(new UpdateCalendarWeek(broadcastDAO.loadForWeek(start)));
    }

    public boolean programExists(ProgramTransfer tr) {
        return this.programDAO.exists(tr);
    }

    public Optional<ProgramTransfer> programFromName(String name) {
        return this.programDAO.findProgram(name);
    }

    // TODO(borja): Don't update with the entire list, only the last added
    // TODO(borja): Show the user window to select advertisers
    public void addProgram(ProgramTransfer tr) {
        this.programDAO.persist(tr);
        this.setChanged();
        this.notifyObservers(new UpdateProgramList(this.programDAO.loadAll()));
    }

    public void addBroadcast(BroadcastTransfer tr) {
        if (this.broadcastDAO.overlaps(tr)) {
            this.setChanged();
            this.notifyObservers(new BroadcastOverlapError(tr.parent.title));
        } else {
            this.broadcastDAO.persist(tr);
            this.setChanged();
            this.notifyObservers(new UpdateBroadcastCalendar(tr));
        }
    }

    public void allThemes() {
        this.setChanged();
        this.notifyObservers(new ShowThemeList(this.themeDAO.loadAll()));
    }

    public boolean themeExists(ThemeTransfer tr) {
        return this.themeDAO.exists(tr);
    }

    public void validateTheme(ThemeTransfer tr) {
        List<BroadcastTransfer> l = this.broadcastDAO.loadForRange(tr.schedule);

        if (l.isEmpty()) {
            themeDAO.persist(tr);
            this.setChanged();
            this.notifyObservers(new UpdateThemeList(tr));
        } else {
            this.setChanged();
            this.notifyObservers(new ChooseBroadcasts(tr, l));
        }
    }

    public void confirmTheme(ThemeTransfer tr, List<BroadcastTransfer> chosen) {
        themeDAO.persist(tr);
        for (BroadcastTransfer b : chosen) {
            broadcastDAO.addTheme(b, tr);
        }

        this.setChanged();
        this.notifyObservers(new UpdateThemeList(tr));
    }

    public boolean songExists(SongTransfer transfer) {
        return songDAO.exists(transfer);
    }

    public void saveSong(SongTransfer transfer) {
        songDAO.persist(transfer);
        this.setChanged();
        this.notifyObservers(new UpdateSongList(transfer));
    }

    public void loadSongInfo() {
        this.setChanged();
        this.notifyObservers(new ShowSongList(this.songDAO.loadAll()));
    }

    public void searchSongDetails(String title, String author, String album, int year) {
        List<SongTransfer> matches = SongService.findMatches(title, author, album, year);
        if (matches.isEmpty()) {
            System.out.println("No songs found");
            this.setChanged();
            this.notifyObservers(new EmptySongMatchError());
        } else {
            this.setChanged();
            this.notifyObservers(new ShowSongMatch(matches));
        }
    }
}
