package radio.core;

import radio.util.BroadcastTime;

import java.time.LocalDateTime;

public class Broadcast {
    private String parentTitle;
    private BroadcastTime schedule;

    public Broadcast(String parenTitle, BroadcastTime sched) {
        this.parentTitle = parenTitle;
        this.schedule = sched;
    }

    public BroadcastTime getSchedule() {
        return schedule;
    }
}
