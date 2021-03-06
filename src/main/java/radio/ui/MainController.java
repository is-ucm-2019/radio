package radio.ui;

import radio.core.Core;
import radio.ui.adverts.AdvertisingWindow;
import radio.ui.musiclibrary.MusicLibraryWindow;
import radio.ui.settings.SettingsDialog;

import java.util.HashMap;
import java.util.Map;
import java.util.Observer;
import java.util.Optional;

public class MainController {
    private MainWindow frame;
    private HashMap<ApplicationPanel, IApplicationWindow> viewMap;

    Core core;

    public MainController(Core core) {
        this.core = core;
        viewMap = null;
    }

    Map<ApplicationPanel, IApplicationWindow> getViewMap() {
        // Lazy load viewMap, allows controller to be instantiated before
        // binding it to the views
        if (viewMap == null) {
            viewMap = new HashMap<>();
            viewMap.put(ApplicationPanel.LOGIN, new LoginWindow(this));
            viewMap.put(ApplicationPanel.LANDING, new LandingWindow(this));
            viewMap.put(ApplicationPanel.PLANNING, new PlanningWindow(this));
            viewMap.put(ApplicationPanel.EVENTS, new EventsWindow(this));
            viewMap.put(ApplicationPanel.THEMES, new ThemeWindow(this));
            viewMap.put(ApplicationPanel.PLAYLISTS, new PlaylistWindow(this));
            viewMap.put(ApplicationPanel.MUSIC_ARCHIVE, new MusicLibraryWindow(this));
            viewMap.put(ApplicationPanel.ADVERTISERS, new AdvertisingWindow(this));
        }

        return viewMap;
    }

    public void addView(MainWindow frame) {
        this.frame = frame;
    }

    void showSettings() {
        SettingsDialog dialog = new SettingsDialog(this);
        dialog.willShow();
        dialog.pack();
        dialog.setVisible(true);
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
        getWindow(panel).ifPresent(IApplicationWindow::willShow);
        frame.switchCards(panel);
    }

    private Optional<IApplicationWindow> getWindow(ApplicationPanel panel) {
        return Optional.ofNullable(viewMap.get(panel));
    }

    void quitEvent() {
        core.quit();
        frame.quit();
    }

    public void addObserver(Observer o) {
        this.core.dispatchObserver(o);
    }

    public void removeObserver(Observer o) {
        this.core.removeObserver(o);
    }

    void showToUser(String message) {
        this.frame.show(message);
    }
}
