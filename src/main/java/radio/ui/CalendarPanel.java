package radio.ui;

import radio.actions.BroadcastOverlapError;
import radio.actions.UpdateBroadcastCalendar;
import radio.actions.UpdateCalendarWeek;
import radio.transfer.BroadcastTransfer;
import radio.transfer.ProgramTransfer;
import radio.util.TimeUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

public class CalendarPanel implements IApplicationWindow, Observer {

    private TableColumnModel columnHeaderModel;
    private LocalDate firstOfWeek;

    private JPanel calendarPanel;
    private JTable calendarTable;
    private JButton prevWeek;
    private JButton nextWeek;
    private JLabel currentWeekLabel;
    private JPanel buttonsPanel;
    private JScrollPane holderPane;
    private JButton todayButton;

    private ICalendarController controller;

    CalendarPanel(ICalendarController controller) {
        $$$setupUI$$$();

        this.controller = controller;

        firstOfWeek = TimeUtil.firstDayOfWeekFrom(LocalDate.now());

        // One column per day, plus timetable
        // One for for every hour of the day, plus "all-day"
        calendarTable.setModel(new DefaultTableModel(26, 8));
        calendarTable.setDefaultRenderer(Object.class, new CalendarCellRenderer());
        columnHeaderModel = calendarTable.getTableHeader().getColumnModel();
        calendarTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        calendarTable.setAutoCreateColumnsFromModel(false);

        setupCalendar();
        prevWeek.addActionListener(e -> {
            firstOfWeek = firstOfWeek.minus(1, ChronoUnit.WEEKS);
            SwingUtilities.invokeLater(() -> controller.getEventsForWeekStartingAt(firstOfWeek));
        });

        nextWeek.addActionListener(e -> {
            firstOfWeek = firstOfWeek.plus(1, ChronoUnit.WEEKS);
            SwingUtilities.invokeLater(() -> controller.getEventsForWeekStartingAt(firstOfWeek));
        });

        todayButton.addActionListener(e -> {
            firstOfWeek = TimeUtil.firstDayOfWeekFrom(LocalDate.now());
            SwingUtilities.invokeLater(() -> controller.getEventsForWeekStartingAt(firstOfWeek));
        });
    }

    public void willShow() {
        SwingUtilities.invokeLater(() -> controller.getEventsForWeekStartingAt(firstOfWeek));
    }

    private void clearCalendar() {
        DefaultTableModel model = (DefaultTableModel) calendarTable.getModel();
        model.setRowCount(0);
    }

    private void setupCalendar() {
        DefaultTableModel tableModel = (DefaultTableModel) calendarTable.getModel();
        tableModel.setColumnCount(8);
        tableModel.setRowCount(25);

        TableColumn tc = columnHeaderModel.getColumn(0);
        tc.setHeaderValue("Horario");
        tableModel.setValueAt("all-day", 0, 0);
        for (int i = 1; i < tableModel.getRowCount(); i++) {
            tableModel.setValueAt(String.format("%02d", (i - 1) % 24) + ":00", i, 0);
        }

        updateCalendar();
    }

    private void updateCalendar() {
        Locale locale = TimeUtil.locale;
        int year = firstOfWeek.getYear();
        String month = firstOfWeek.getMonth().getDisplayName(TextStyle.FULL, locale);
        currentWeekLabel.setText(month + " " + year);

        TableColumn tc;
        int dayOfMonth;
        String dayOfWeek;
        LocalDate current = firstOfWeek;
        for (int i = 1; i < 8; i++) {
            dayOfMonth = current.getDayOfMonth();
            dayOfWeek = current.getDayOfWeek().getDisplayName(TextStyle.SHORT, locale);
            tc = columnHeaderModel.getColumn(i);
            tc.setHeaderValue(dayOfWeek + " " + dayOfMonth);

            current = current.plusDays(1);
        }

        // Only repaint header, we didnt' change anything else
        calendarTable.getTableHeader().repaint();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        calendarPanel = new JPanel();
        calendarPanel.setLayout(new BorderLayout(0, 0));
        holderPane = new JScrollPane();
        calendarPanel.add(holderPane, BorderLayout.CENTER);
        calendarTable = new JTable();
        calendarTable.setEnabled(false);
        calendarTable.setFillsViewportHeight(false);
        holderPane.setViewportView(calendarTable);
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BorderLayout(0, 0));
        calendarPanel.add(buttonsPanel, BorderLayout.NORTH);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        buttonsPanel.add(panel1, BorderLayout.CENTER);
        currentWeekLabel = new JLabel();
        currentWeekLabel.setHorizontalAlignment(2);
        currentWeekLabel.setText("Label");
        currentWeekLabel.setToolTipText("Current Month");
        panel1.add(currentWeekLabel, BorderLayout.CENTER);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.add(panel2, BorderLayout.EAST);
        prevWeek = new JButton();
        prevWeek.setText("←");
        prevWeek.setToolTipText("Go to previous week");
        panel2.add(prevWeek);
        todayButton = new JButton();
        todayButton.setText("Today");
        todayButton.setToolTipText("Go to current week");
        panel2.add(todayButton);
        nextWeek = new JButton();
        nextWeek.setText("→");
        nextWeek.setToolTipText("Go to next week");
        panel2.add(nextWeek);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return calendarPanel;
    }

    @Override
    public JPanel getPanelHandler() {
        return (JPanel) this.$$$getRootComponent$$$();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!getPanelHandler().isShowing()) {
            return;
        }

        if (arg instanceof UpdateCalendarWeek) {
            showProgramsForWeek(((UpdateCalendarWeek) arg).list);
        } else if (arg instanceof UpdateBroadcastCalendar) {
            putBroadcastOnCalendar(((UpdateBroadcastCalendar) arg).tr);
        } else if (arg instanceof BroadcastOverlapError) {
            showOverlapError(((BroadcastOverlapError) arg).errorMessage);
        }
    }

    private void showProgramsForWeek(List<BroadcastTransfer> broadcasts) {
        clearCalendar();
        setupCalendar();

        DefaultTableModel tableModel = (DefaultTableModel) calendarTable.getModel();
        for (BroadcastTransfer tr : broadcasts) {
            putBroadcastOnCalendarInternal(tableModel, tr);
        }
    }

    private void putBroadcastOnCalendar(BroadcastTransfer tr) {
        DefaultTableModel tableModel = (DefaultTableModel) calendarTable.getModel();
        putBroadcastOnCalendarInternal(tableModel, tr);
    }

    private void putBroadcastOnCalendarInternal(DefaultTableModel tableModel, BroadcastTransfer tr) {
        int column = tr.schedule.getDayOfWeekNumeric();
        // +1 for index offset
        int rowStart = tr.schedule.getStartNumeric() + 1;
        int rowEnd = tr.schedule.getEndNumeric() + 1;

        for (int i = rowStart; i < rowEnd; i++) {
            tableModel.setValueAt(tr, i, column);
        }
    }

    private void showOverlapError(String errorMessage) {
        show("Error: couldn't create broacast. Selected slot for " + errorMessage + " broadcast is already full");
    }
}
