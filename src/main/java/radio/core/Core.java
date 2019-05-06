package radio.core;

import radio.actions.UpdateBroadcastCalendar;
import radio.actions.UpdateCalendarWeek;
import radio.actions.UpdateProgramList;
import radio.core.users.User;
import radio.dao.BroadcastDAO;
import radio.dao.ProgramDAO;
import radio.transfer.BroadcastTransfer;
import radio.transfer.ProgramTransfer;

import java.time.LocalDate;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

public class Core extends Observable {
    private Optional<User> currentUser = Optional.empty();
    private ProgramDAO programDAO;
    private BroadcastDAO broadcastDAO;

    public Core() {
        programDAO = new ProgramDAO();
        broadcastDAO = new BroadcastDAO();
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
        this.broadcastDAO.persist(tr);
        this.setChanged();
        this.notifyObservers(new UpdateBroadcastCalendar(tr));
    }
}
