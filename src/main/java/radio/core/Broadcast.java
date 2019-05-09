package radio.core;

import radio.transfer.BroadcastTransfer;
import radio.util.BroadcastTime;

// TODO(borja): Add themes
public class Broadcast {
    private String parentTitle;
    private BroadcastTime schedule;

    public Broadcast(String parenTitle, BroadcastTime sched) {
        this.parentTitle = parenTitle;
        this.schedule = sched;
    }

    public Broadcast(BroadcastTransfer tr) {
        this.parentTitle = tr.parent.title;
        this.schedule = tr.schedule;
    }

    public BroadcastTime getSchedule() {
        return schedule;
    }

    public String getParentTitle() {
        return parentTitle;
    }
}
