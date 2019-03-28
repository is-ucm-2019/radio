package radio.ui;

import radio.core.Core;
import radio.util.TimeUtil;

import java.time.LocalDate;
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

    // FIXME(borja): Validate combo
    void loginEvent(String username, String password) {
        core.login(username, password);
        frame.switchCards(ApplicationPanel.LANDING);
    }

    void logout() {
        core.logout();
        frame.switchCards(ApplicationPanel.LOGIN);
    }

    void swapWindow(ApplicationPanel panel) {
        if (panel == ApplicationPanel.PLANNING) {
            this.core.loadPlanningInfo(TimeUtil.firstDayOfWeekFrom(LocalDate.now()));
        }

        frame.switchCards(panel);
    }

    void quitEvent() {
        frame.quit();
    }

    void addObserver(Observer o) {
        this.core.dispatchObserver(o);
    }

    void showToUser(String message) {
        this.frame.show(message);
    }
}
