package radio.actions;

import radio.transfer.ProgramTransfer;

import java.util.List;

public final class UpdateCalendarWeek {
    public final List<ProgramTransfer> list;

    public UpdateCalendarWeek(List<ProgramTransfer> l) {
        this.list = l;
    }
}
