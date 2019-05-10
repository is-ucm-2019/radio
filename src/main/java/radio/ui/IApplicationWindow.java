package radio.ui;

import javax.swing.*;

public interface IApplicationWindow {
    JPanel getPanelHandler();

    // Executed when window is about to be shown to the user
    default void willShow() {
    }

    default void show(String message) {
        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(getPanelHandler(), message));
    }

    default void showSync(String message) {
        JOptionPane.showMessageDialog(getPanelHandler(), message);
    }
}
