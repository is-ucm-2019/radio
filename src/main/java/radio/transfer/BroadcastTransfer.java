package radio.transfer;

import radio.core.Broadcast;
import radio.util.BroadcastTime;

import java.time.LocalDateTime;
import java.util.UUID;

// TODO(borja): Add themes
public final class BroadcastTransfer {
    public final UUID key;
    public final ProgramTransfer parent;
    public final BroadcastTime schedule;

    public BroadcastTransfer(ProgramTransfer parent, LocalDateTime start, LocalDateTime end) {
        this.key = UUID.randomUUID();
        this.parent = parent;
        this.schedule = new BroadcastTime(start, end);
    }

    public BroadcastTransfer(ProgramTransfer parent, BroadcastTime time) {
        this.key = UUID.randomUUID();
        this.parent = parent;
        this.schedule = time;
    }

    public BroadcastTransfer(ProgramTransfer parent, Broadcast br) {
        this.key = br.getKey();
        this.parent = parent;
        this.schedule = br.getSchedule();
    }

    @Override
    public String toString() {
        return String.format("Program: %s [%s - %s]", parent.title, this.schedule.getStart(), this.schedule.getEnd());
    }
}

