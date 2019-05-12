package radio.transfer;

import radio.core.Program;

import java.awt.Color;

public final class ProgramTransfer {
    public final String title;
    public final String description;
    public final Color color;

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
