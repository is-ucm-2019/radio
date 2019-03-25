package radio.ui;

import javax.swing.*;

public interface ApplicationWindow {
    JPanel getPanelHandler();
    default void show(String message) {
        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(getPanelHandler(), message));
    }
}
