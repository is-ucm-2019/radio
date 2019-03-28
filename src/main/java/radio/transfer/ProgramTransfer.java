package radio.transfer;

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
}
