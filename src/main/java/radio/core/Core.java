package radio.core;

import radio.actions.UpdateProgramList;
import radio.core.users.User;
import radio.dao.ProgramDAO;
import radio.transfer.ProgramTransfer;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

public class Core extends Observable {
    private Optional<User> currentUser = Optional.empty();
    private ProgramDAO programDAO;

    public Core() {
        programDAO = new ProgramDAO();
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

    public boolean programExists(ProgramTransfer tr) {
        return this.programDAO.exists(tr);
    }

    // TODO(borja): Don't update with the entire list, only the last added
    // TODO(borja): Show the user window to select advertisers
    public void addProgram(ProgramTransfer tr) {
        this.programDAO.persist(tr);
        this.setChanged();
        this.notifyObservers(new UpdateProgramList(this.programDAO.loadAll()));
    }
}
