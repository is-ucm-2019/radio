package radio.transfer;

import radio.core.Program;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public final class ProgramTransfer {
    public final String title;
    public final String description;
    public final Color color;
    public List<BroadcastTransfer> broadcasts = new ArrayList<>();

    public ProgramTransfer(String title, String description, Color color) {
        this.title = title;
        this.description = description;
        this.color = color;
    }

    public ProgramTransfer(Program from) {
        this.title = from.getTitle();
        this.description = from.getDescription();
        this.color = from.getColor();
    }
}
