package radio.ui;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MainWindow {
    private JFrame holderFrame;
    private JPanel switcherPanel;
    private CardLayout switcherLayout;

    private String frameTitle;
    private MainController controller;

    public MainWindow(MainController controller) {
        frameTitle = "Radio Enterprises Inc.";

        this.switcherLayout = new CardLayout();
        this.holderFrame = new JFrame();
        this.switcherPanel = new JPanel(switcherLayout);

        this.controller = controller;

        initUI();
    }

    void show(String message) {
        javax.swing.SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(holderFrame, message));
    }

    void switchCards(ApplicationPanel toShow) {
        if (toShow == ApplicationPanel.LANDING || toShow == ApplicationPanel.LOGIN) {
            switchCards(toShow, this.frameTitle);
        } else {
            switchCards(toShow, toShow.name());
        }

    }

    private void switchCards(ApplicationPanel toShow, String description) {
        this.holderFrame.setTitle(description);
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

        for(Map.Entry<ApplicationPanel, ApplicationWindow> entry : controller.getViewMap().entrySet()) {
            switcherPanel.add(entry.getValue().getPanelHandler(), entry.getKey().name());
        }

        holderFrame.add(switcherPanel, BorderLayout.CENTER);
        holderFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        switchCards(ApplicationPanel.LOGIN);
    }

    public void enable() {
        holderFrame.pack();
        holderFrame.setExtendedState(Frame.NORMAL);
        holderFrame.setVisible(true);
    }
}
