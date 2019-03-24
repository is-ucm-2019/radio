package radio.ui;

import radio.core.Core;

import java.util.Observer;

public class MainController {
    private MainWindow frame;
    Core core;

    public MainController(Core core) {
        this.core = core;
    }

    public void addView(MainWindow frame) {
        this.frame = frame;
    }

    void loginEvent(String _username, String _password) {
        frame.switchCards(MainWindow.Panels.LANDING);
    }

    void logout() {
        frame.switchCards(MainWindow.Panels.LOGIN);
    }

    void swapWindow(MainWindow.Panels panel) {
        frame.switchCards(panel);
    }

    void quitEvent() {
        frame.quit();
    }

    void addObserver(Observer o) {
        this.core.dispatchObserver(o);
    }
}
