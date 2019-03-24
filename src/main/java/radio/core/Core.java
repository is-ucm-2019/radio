package radio.core;

import radio.actions.ShowProgramsAction;
import radio.core.users.User;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

public class Core extends Observable {
    private Optional<User> currentUser = Optional.empty();

    private Stock stock;

    public Core() {
        stock = new Stock();
    }

    public void dispatchObserver(Observer obs) {
        this.addObserver(obs);
    }

    public void dispatchObservers(Observer[] obs) {
        for (Observer o: obs) {
            this.addObserver(o);
        }
    }

    // TODO(borja): Perform user validation
    public void login(String username, String password) {
        currentUser = Optional.of(new User(username, password));
    }

    public void logout() {
        this.currentUser = Optional.empty();
    }

    public void addProgram(String title, String description, Color color) {
        Program p = new Program(title, description, color);
        this.stock.addProgram(p);
        ShowProgramsAction action = new ShowProgramsAction();
        action.list = this.stock.programAsList();

        this.setChanged();
        this.notifyObservers(action);
    }
}
