package radio.ui;

import javax.swing.*;
import java.awt.*;

public class ThemeWindow implements ApplicationWindow {

    private MainController controller;
    private JPanel containerPanel;

    private ApplicationWindow menuView;
    private CalendarPanel calendarView;
    private ThemeListPanel allThemesView;

    ThemeWindow(MainController controller) {
        this.controller = controller;
        ThemesController plController = new ThemesController(controller);

        containerPanel = new JPanel(new BorderLayout());

        this.menuView = new MenuPanel(this.controller);
        this.calendarView = new CalendarPanel(new PlanningCalendarController(controller));
        this.allThemesView = new ThemeListPanel(plController);

        this.controller.addObserver(calendarView);
        this.controller.addObserver(allThemesView);

        initUI();
    }

    private void initUI() {
        containerPanel.add(this.menuView.getPanelHandler(), BorderLayout.NORTH);
        containerPanel.add(this.calendarView.getPanelHandler(), BorderLayout.CENTER);
        containerPanel.add(this.allThemesView.getPanelHandler(), BorderLayout.EAST);
    }

    @Override
    public JPanel getPanelHandler() {
        return containerPanel;
    }

    public void willShow() {
        this.calendarView.willShow();
        this.allThemesView.willShow();
    }
}
