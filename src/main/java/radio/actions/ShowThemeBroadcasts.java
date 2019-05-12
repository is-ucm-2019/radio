package radio.actions;

import radio.transfer.BroadcastTransfer;

import java.util.List;

public class ShowThemeBroadcasts {
    public final List<BroadcastTransfer> list;

    public ShowThemeBroadcasts(List<BroadcastTransfer> list) {
        this.list = list;
    }
}
