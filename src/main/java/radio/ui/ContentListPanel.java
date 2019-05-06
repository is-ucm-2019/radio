package radio.ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;

public class ContentListPanel implements ApplicationWindow {
    private JPanel background;
    private JTextField searchField;
    private JButton searchButton;
    private JButton newButton;
    private JScrollPane scrollPane;
    private JPanel menuPane;
    private JLabel viewTitle;
    private JPanel searchPane;

    private String labelTitle;

    ContentListPanel(String labelTitle) {
        this.labelTitle = labelTitle;
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
        createUIComponents();
        background = new JPanel();
        background.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        scrollPane = new JScrollPane();
        background.add(scrollPane, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        menuPane = new JPanel();
        menuPane.setLayout(new BorderLayout(0, 0));
        background.add(menuPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        menuPane.add(viewTitle, BorderLayout.WEST);
        searchPane = new JPanel();
        searchPane.setLayout(new BorderLayout(0, 0));
        menuPane.add(searchPane, BorderLayout.CENTER);
        searchField = new JTextField();
        searchPane.add(searchField, BorderLayout.CENTER);
        searchButton = new JButton();
        searchButton.setText("Go");
        searchPane.add(searchButton, BorderLayout.EAST);
        newButton = new JButton();
        newButton.setText("+");
        menuPane.add(newButton, BorderLayout.EAST);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return background;
    }

    @Override
    public JPanel getPanelHandler() {
        return (JPanel) $$$getRootComponent$$$();
    }

    private void createUIComponents() {
        viewTitle = new JLabel();
        viewTitle.setText(this.labelTitle);
    }
}
