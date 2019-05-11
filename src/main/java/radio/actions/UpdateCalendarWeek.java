package radio.actions;

import radio.transfer.BroadcastTransfer;
import radio.transfer.ProgramTransfer;

import java.util.List;

public final class UpdateCalendarWeek {
    public final List<BroadcastTransfer> list;

    public UpdateCalendarWeek(List<BroadcastTransfer> l) {
        this.list = l;
    }
}
