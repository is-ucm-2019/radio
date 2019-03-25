package radio.transfer;

import java.awt.*;

public final class ProgramTransfer implements Comparable<ProgramTransfer> {
    public final String title;
    public final String description;
    public final Color color;

    public ProgramTransfer(String title, String description, Color color) {
        this.title = title;
        this.description = description;
        this.color = color;
    }

    @Override
    public int compareTo(ProgramTransfer that) {
        int titleEqual = this.title.toLowerCase().compareTo(that.title.toLowerCase());
        if (titleEqual == 0) {
            return this.description.toLowerCase().compareTo(that.description.toLowerCase());
        }
        return titleEqual;
    }
}
