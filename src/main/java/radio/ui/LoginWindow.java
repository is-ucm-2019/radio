package radio.ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginWindow implements IApplicationWindow {
    private JPanel background;
    private JButton loginButton;
    private JButton quitButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPanel buttonPanel;

    private MainController controller;

    public LoginWindow(MainController controller) {
        this.controller = controller;

        Action enterCb = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.loginEvent(usernameField.getText(), String.valueOf(passwordField.getPassword()));
                usernameField.setText(null);
                passwordField.setText(null);
            }
        };

        loginButton.addActionListener(enterCb);
        usernameField.addActionListener(enterCb);
        passwordField.addActionListener(enterCb);
        quitButton.addActionListener(_e -> controller.quitEvent());
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        background = new JPanel();
        background.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        background.setBackground(new Color(-1));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        background.add(buttonPanel, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        loginButton = new JButton();
        loginButton.setText("Log In");
        loginButton.setToolTipText("Log In");
        buttonPanel.add(loginButton);
        quitButton = new JButton();
        quitButton.setText("Quit");
        quitButton.setToolTipText("Quit Application");
        buttonPanel.add(quitButton);
        usernameField = new JTextField();
        usernameField.setToolTipText("Username");
        background.add(usernameField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        passwordField = new JPasswordField();
        background.add(passwordField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        usernameLabel = new JLabel();
        usernameLabel.setHorizontalAlignment(0);
        usernameLabel.setHorizontalTextPosition(0);
        usernameLabel.setText("Username");
        usernameLabel.setToolTipText("Password");
        background.add(usernameLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        background.add(passwordLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return background;
    }

    @Override
    public JPanel getPanelHandler() {
        return (JPanel) this.$$$getRootComponent$$$();
    }
}
