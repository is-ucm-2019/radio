package radio.ui;

import javax.swing.*;
import java.awt.*;

public class EventsWindow implements IApplicationWindow {

    private MainController controller;
    private JPanel containerPanel;

    private IApplicationWindow menuView;
    private IApplicationWindow calendarView;
    private IApplicationWindow allEventsView;

    EventsWindow(MainController controller) {
        this.controller = controller;
        containerPanel = new JPanel(new BorderLayout());

        this.menuView = new MenuPanel(this.controller);
        this.calendarView = new CalendarPanel(new EventsCalendarController(controller));
        this.allEventsView = new EventListPanel(this.controller);

        initUI();
    }

    private void initUI() {
        containerPanel.add(this.menuView.getPanelHandler(), BorderLayout.NORTH);
        containerPanel.add(this.calendarView.getPanelHandler(), BorderLayout.CENTER);
        containerPanel.add(this.allEventsView.getPanelHandler(), BorderLayout.EAST);
    }

    @Override
    public JPanel getPanelHandler() {
        return containerPanel;
    }
}
