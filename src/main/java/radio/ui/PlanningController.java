package radio.ui;

import radio.transfer.BroadcastTransfer;
import radio.transfer.ProgramTransfer;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Optional;

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

    // FIXME(borja): Need to check if schedule is taken
    void addBroadcastEvent(String name, LocalDateTime start, LocalDateTime end) {
        Optional<ProgramTransfer> pt = this.parentController.core.programFromName(name);
        if (!pt.isPresent()) {
            this.parentController.showToUser("Invalid broadcast, program not found!");
            return;
        }

        BroadcastTransfer transfer = new BroadcastTransfer(pt.get(), start, end);
        this.parentController.core.addBroadcast(transfer);
    }
}
