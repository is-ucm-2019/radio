package radio.ui;

import radio.transfer.ProgramTransfer;

import java.awt.*;

class PlanningController {

    private MainController parentController;

    PlanningController(MainController cont) {
        this.parentController = cont;
    }

    void addProgramEvent(String name, String description, Color color) {
        ProgramTransfer tr = new ProgramTransfer(name, description, color);
        if (this.parentController.core.programExists(tr)) {
            this.parentController.showToUser("Program already exists! Pick another name");
        } else {
            this.parentController.core.addProgram(tr);
        }
    }
}
