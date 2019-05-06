package radio.ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import radio.actions.UpdateProgramList;
import radio.transfer.ProgramTransfer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

public class ProgramListPanel implements ApplicationWindow, Observer {

    private JPanel background;
    // FIXME(borja): This list should probably contain JPanel
    private JList<String> programListPanel;
    private JButton newProgramButton;
    private JTextField searchField;
    private JButton searchButton;
    private JScrollPane listPane;
    private JPanel searchPane;

    private DefaultListModel<String> listModel;
    private PlanningController controller;

    private JPopupMenu programPopup;

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
        if (arg instanceof UpdateProgramList) {
            listModel.clear();
            UpdateProgramList showProgramsAction = (UpdateProgramList) arg;
            int i = 0;
            for (ProgramTransfer p : showProgramsAction.list) {
                listModel.insertElementAt(p.title, i);
                i++;
            }
        }
    }

    private void createUIComponents() {
        this.programPopup = new JPopupMenu();
        ProgramListPopup popupAction = new ProgramListPopup();

        JMenu broadcastMenu = new JMenu("Emisiones");
        programPopup.add(new JMenuItem("Editar Programa"));
        programPopup.add(new JMenuItem("Eliminar Programa"));
        programPopup.addSeparator();

        JMenuItem addBroadcast = new JMenuItem("Añadir Emisión");
        addBroadcast.addActionListener(popupAction.innerAction(controller));
        broadcastMenu.add(addBroadcast);
        broadcastMenu.add(new JMenuItem("Ver Emisiones"));
        programPopup.add(broadcastMenu);

        this.listModel = new DefaultListModel<>();
        programListPanel = new JList<>(listModel);
        programListPanel.addMouseListener(popupAction);
    }

    private class ProgramListPopup extends MouseAdapter {
        private String itemSelected = null;

        public void mouseClicked(MouseEvent e) {
            JList owner = (JList) e.getSource();
            if (e.getClickCount() == 2) {
                int index = owner.locationToIndex(e.getPoint());
                if (index >= 0) {
                    Object o = owner.getModel().getElementAt(index);
                    System.out.println("Double clicked on " + o.toString());
                }
            }
        }

        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }

        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }

        private void maybeShowPopup(MouseEvent e) {
            JList owner = programListPanel;
            boolean isTrigger = e.isPopupTrigger();
            boolean selectedItem = !owner.isSelectionEmpty();
            boolean bound = owner.locationToIndex(e.getPoint()) == owner.getSelectedIndex();
            if (isTrigger && selectedItem && bound) {
                int index = owner.locationToIndex(e.getPoint());
                itemSelected = (String) owner.getModel().getElementAt(index);
                programPopup.show(owner, e.getX(), e.getY());
            }
        }

        // Need to get the original object we clicked on
        private ActionListener innerAction(PlanningController controller) {
            return (e -> {
                assert itemSelected != null;

                NewBroadcastDialog dialog = new NewBroadcastDialog(controller, itemSelected);
                dialog.pack();
                dialog.setVisible(true);
            });
        }
    }
}
