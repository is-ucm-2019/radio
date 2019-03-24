package radio.ui;

import java.awt.*;

public class PlanningController {
    private MainController parentController;

    PlanningController(MainController cont) {
        this.parentController = cont;
    }

    void addProgramEvent(String name, String description, Color color) {
        this.parentController.core.addProgram(name, description, color);
    }
}
