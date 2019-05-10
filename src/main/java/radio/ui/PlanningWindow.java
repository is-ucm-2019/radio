package radio.ui;

import javax.swing.*;
import java.awt.*;

public class PlanningWindow implements IApplicationWindow {

    private MainController controller;
    private JPanel containerPanel;

    private IApplicationWindow menuView;
    private CalendarPanel calendarView;
    private ProgramListPanel allProgramsView;

    PlanningWindow(MainController controller) {
        this.controller = controller;
        PlanningController plController = new PlanningController(controller);

        containerPanel = new JPanel(new BorderLayout());

        this.menuView = new MenuPanel(this.controller);
        this.calendarView = new CalendarPanel(new PlanningCalendarController(controller));
        this.allProgramsView = new ProgramListPanel(plController);

        this.controller.addObserver(calendarView);
        this.controller.addObserver(allProgramsView);

        initUI();
    }

    private void initUI() {
        containerPanel.add(this.menuView.getPanelHandler(), BorderLayout.NORTH);
        containerPanel.add(this.calendarView.getPanelHandler(), BorderLayout.CENTER);
        containerPanel.add(this.allProgramsView.getPanelHandler(), BorderLayout.EAST);
    }

    @Override
    public JPanel getPanelHandler() {
        return containerPanel;
    }

    @Override
    public void willShow() {
        this.calendarView.willShow();
        this.allProgramsView.willShow();
    }
}
