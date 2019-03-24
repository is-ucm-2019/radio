package radio.ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import radio.actions.ShowProgramsAction;
import radio.core.Program;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class ProgramListPanel implements ApplicationWindow, Observer {

    private JPanel background;
    private JList programListPanel;
    private JButton newProgramButton;
    private JTextField searchField;
    private JButton searchButton;
    private JScrollPane listPane;
    private JPanel searchPane;

    private DefaultListModel<String> listModel;
    private PlanningController controller;

    ProgramListPanel(PlanningController cont) {
        this.controller = cont;
        $$$setupUI$$$();
        newProgramButton.addActionListener(e -> {
            NewProgramDialog dialog = new NewProgramDialog(controller);
            dialog.pack();
            dialog.setVisible(true);
        });
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
        background.setLayout(new BorderLayout(0, 0));
        listPane = new JScrollPane();
        background.add(listPane, BorderLayout.CENTER);
        listPane.setViewportView(programListPanel);
        searchPane = new JPanel();
        searchPane.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        background.add(searchPane, BorderLayout.NORTH);
        newProgramButton = new JButton();
        newProgramButton.setText("+");
        searchPane.add(newProgramButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchField = new JTextField();
        searchPane.add(searchField, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        searchButton = new JButton();
        searchButton.setText("Search");
        searchPane.add(searchButton, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        searchPane.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
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

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ShowProgramsAction) {
            listModel.clear();
            ShowProgramsAction showProgramsAction = (ShowProgramsAction) arg;
            int i = 0;
            for (Program p : showProgramsAction.list) {
                listModel.insertElementAt(p.title, i);
                i++;
            }
        }
    }

    private void createUIComponents() {
        this.listModel = new DefaultListModel<>();
        programListPanel = new JList(listModel);
    }
}
