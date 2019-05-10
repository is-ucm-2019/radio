package radio.ui.musiclibrary;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import radio.actions.ShowSongList;
import radio.actions.UpdateSongList;
import radio.transfer.SongTransfer;
import radio.ui.ApplicationWindow;
import radio.ui.MusicLibraryController;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class SongListPanel implements ApplicationWindow, Observer {
    private JTextField searchField;
    private JButton addSong;
    private JList<String> songList;
    private JPanel background;

    private DefaultListModel<String> listModel;
    private MusicLibraryController controller;

    SongListPanel(MusicLibraryController controller) {
        this.controller = controller;
        $$$setupUI$$$();
        addSong.addActionListener(e -> {
            NewSongDialog dialog = new NewSongDialog(this.controller);
            dialog.pack();
            dialog.willShow();
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
        background.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        background.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, BorderLayout.NORTH);
        searchField = new JTextField();
        panel2.add(searchField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        addSong = new JButton();
        addSong.setText("+");
        panel2.add(addSong, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Buscar");
        panel2.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel3, BorderLayout.CENTER);
        final JScrollPane scrollPane1 = new JScrollPane();
        panel3.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane1.setViewportView(songList);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return background;
    }

    private void createUIComponents() {
        this.listModel = new DefaultListModel<>();
        this.songList = new JList<>(listModel);
        songList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @Override
    public JPanel getPanelHandler() {
        return (JPanel) $$$getRootComponent$$$();
    }

    @Override
    public void willShow() {
        // Populate list when coming into view
        this.controller.getAllSongs();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!getPanelHandler().isShowing()) {
            return;
        }

        if (arg instanceof ShowSongList) {
            populateSongList((ShowSongList) arg);
        } else if (arg instanceof UpdateSongList) {
            updateSongList((UpdateSongList) arg);
        }

    }

    private void populateSongList(ShowSongList msg) {
        this.listModel.clear();
        for (SongTransfer s : msg.list) {
            listModel.addElement(s.toString());
        }
    }

    // TODO(borja): Keep model sorted by name
    private void updateSongList(UpdateSongList msg) {
        listModel.addElement(msg.tr.toString());
    }
}
