package radio.actions;

import radio.transfer.BroadcastTransfer;
import radio.transfer.ThemeTransfer;

import java.util.List;

public class ChooseBroadcasts {
    public final ThemeTransfer tr;
    public final List<BroadcastTransfer> includedBroadcasts;

    public ChooseBroadcasts(ThemeTransfer tr, List<BroadcastTransfer> includedBroadcasts) {
        this.tr = tr;
        this.includedBroadcasts = includedBroadcasts;
    }
}
