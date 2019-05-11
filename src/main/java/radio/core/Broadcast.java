package radio.core;

import radio.transfer.BroadcastTransfer;
import radio.util.BroadcastTime;

import java.util.UUID;

// TODO(borja): Add themes
public class Broadcast extends PersistentObject<UUID> implements Comparable<Broadcast> {
    private UUID key;
    private BroadcastTime schedule;

    public Broadcast(BroadcastTime sched) {
        this.key = UUID.randomUUID();
        this.schedule = sched;
    }

    public Broadcast(BroadcastTransfer tr) {
        this.key = tr.key;
        this.schedule = tr.schedule;
    }

    public BroadcastTime getSchedule() {
        return schedule;
    }

    @Override
    UUID getKey() {
        return key;
    }

    @Override
    public int compareTo(Broadcast o) {
        return schedule.compareTo(o.schedule);
    }
}
