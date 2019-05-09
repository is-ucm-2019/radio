package radio.core;

import radio.actions.*;
import radio.core.users.User;
import radio.dao.BroadcastDAO;
import radio.dao.ProgramDAO;
import radio.dao.ThemeDAO;
import radio.transfer.BroadcastTransfer;
import radio.transfer.ProgramTransfer;
import radio.transfer.ThemeTransfer;
import radio.util.ThemeSchedule;

import java.time.LocalDate;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.stream.Collectors;

public class Core extends Observable {
    private Optional<User> currentUser = Optional.empty();
    private ProgramDAO programDAO;
    private BroadcastDAO broadcastDAO;
    private ThemeDAO themeDAO;

    public Core() {
        programDAO = new ProgramDAO();
        broadcastDAO = new BroadcastDAO();
        themeDAO = new ThemeDAO();
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
        List<ProgramTransfer> programs = programDAO.loadAll();
        for (ProgramTransfer p : programs) {
            p.broadcasts = broadcastDAO.loadForWeek(p, start);
        }

        this.setChanged();
        this.notifyObservers(new UpdateCalendarWeek(programs));
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

    public void validateTheme(ThemeTransfer tr) {
        ThemeSchedule sched = tr.schedule;
        List<BroadcastTransfer> l = this.programDAO
                                        .loadAll()
                                        .stream()
                                        .flatMap(program ->
                                                    this.broadcastDAO.loadForRange(program, sched))
                                        .collect(Collectors.toList());

        if (l.isEmpty()) {
            System.out.println("No theme overlap!");
            this.setChanged();
            this.notifyObservers(new UpdateThemeList(tr));
        } else {
            System.out.println("Theme overlaps");
            this.setChanged();
            this.notifyObservers(new ChooseBroadcasts(tr, l));
        }
    }

    public void confirmTheme(ThemeTransfer tr, List<BroadcastTransfer> chosen) {
        for (BroadcastTransfer b : chosen) {
            // TODO(borja): Implement
            System.out.println("User chose " + b);
            // b.themes.add(tr);
            // this.broadcastDAO.update(b);
        }

        this.themeDAO.persist(tr);
    }
}
