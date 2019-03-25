package radio.ui;


import javax.swing.*;
import java.awt.*;

public class MainWindow {
    enum Panels {
        LOGIN,
        LANDING,
        PLANNING,
        EVENTS,
        TICKETS,
        ADVERTISERS,
        MUSIC_ARCHIVE,
        PLAYLISTS,
        THEMES
    }

    private JFrame holderFrame;
    private JPanel switcherPanel;
    private CardLayout switcherLayout;

    private String frameTitle;
    private MainController controller;

    private Panels activePanel;
    private ApplicationWindow loginWindow;
    private ApplicationWindow landingWindow;
    private ApplicationWindow planningWindow;
    private ApplicationWindow eventsWindow;

    public MainWindow(MainController controller) {
        frameTitle = "Radio Enterprises Inc.";
        this.switcherLayout = new CardLayout();
        this.holderFrame = new JFrame();
        this.switcherPanel = new JPanel(switcherLayout);

        this.controller = controller;
        this.activePanel = Panels.LOGIN;
        this.loginWindow = new LoginWindow(controller);
        this.landingWindow = new LandingWindow(controller);
        this.planningWindow = new PlanningWindow(controller);
        this.eventsWindow = new EventsWindow(controller);

        initUI();
    }

    void show(String message) {
        javax.swing.SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(holderFrame, message));
    }

    void switchCards(Panels toShow) {
        if (toShow == Panels.LANDING || toShow == Panels.LOGIN) {
            switchCards(toShow, this.frameTitle);
        } else {
            switchCards(toShow, toShow.name());
        }

    }

    void switchCards(Panels toShow, String description) {
        this.holderFrame.setTitle(description);
        this.activePanel = toShow;
        this.switcherLayout.show(switcherPanel, toShow.name());
    }

    void quit() {
        if(JOptionPane.showConfirmDialog(null, "Really exit?", "Exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            // Shut down controller
            System.exit(0);
        }
    }

    private void initUI() {
        holderFrame.setTitle(this.frameTitle);
        holderFrame.setLayout(new BorderLayout());

        switcherPanel.add(this.loginWindow.getPanelHandler(), Panels.LOGIN.name());
        switcherPanel.add(this.landingWindow.getPanelHandler(), Panels.LANDING.name());
        switcherPanel.add(this.planningWindow.getPanelHandler(), Panels.PLANNING.name());
        switcherPanel.add(this.eventsWindow.getPanelHandler(), Panels.EVENTS.name());
        holderFrame.add(switcherPanel, BorderLayout.CENTER);
        holderFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void enable() {
        holderFrame.pack();
        holderFrame.setExtendedState(Frame.NORMAL);
        holderFrame.setVisible(true);
    }
}
