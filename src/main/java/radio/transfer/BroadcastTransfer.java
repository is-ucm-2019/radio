package radio.transfer;

import radio.core.Broadcast;
import radio.util.BroadcastTime;

import java.time.LocalDateTime;

public final class BroadcastTransfer {
    public final ProgramTransfer parent;
    public final BroadcastTime schedule;

    public BroadcastTransfer(ProgramTransfer parent, LocalDateTime start, LocalDateTime end) {
        this.parent = parent;
        this.schedule = new BroadcastTime(start, end);
    }

    public BroadcastTransfer(ProgramTransfer parent, BroadcastTime time) {
        this.parent = parent;
        this.schedule = time;
    }

    public BroadcastTransfer(ProgramTransfer parent, Broadcast br) {
        this.parent = parent;
        this.schedule = br.getSchedule();
    }

    @Override
    public String toString() {
        return String.format("BroadcastTransfer{parent=%s, start=%s, end=%s}", parent.title, this.schedule.getStart(), this.schedule.getEnd());
    }
}

