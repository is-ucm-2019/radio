package radio.dao;

import radio.core.Broadcast;
import radio.transfer.BroadcastTransfer;
import radio.transfer.ProgramTransfer;
import radio.util.BroadcastTime;
import radio.util.TimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

public class BroadcastDAO implements AppDAO<BroadcastTransfer> {
    private TreeMap<String, TreeMap<LocalDateTime, Broadcast>> db = new TreeMap<>();

    @Override
    public boolean exists(BroadcastTransfer el) {
        return this.db.containsKey(el.parent.title);
    }

    @Override
    public void persist(BroadcastTransfer el) {
        Broadcast b = new Broadcast(el.parent.title, el.schedule);
        TreeMap<LocalDateTime, Broadcast> list;
        if (exists(el)) {
            list = db.get(el.parent.title);
        } else {
            list = new TreeMap<>();
        }

        list.put(el.schedule.getStart(), b);
        this.db.put(el.parent.title, list);
    }

    @Override
    public void delete(BroadcastTransfer el) {
        if (exists(el)) {
            db.get(el.parent.title).remove(el.schedule.getStart());
        }
    }

    @Override
    public List<BroadcastTransfer> loadAll() {
        throw new UnsupportedOperationException();
    }

    // TODO(borja): Might need it for showing all the broadcast under a program
    // public List<BroadcastTransfer> loadAll(ProgramTransfer p) {
    //     ArrayList<BroadcastTransfer> l = new ArrayList<>();
    //     if (this.db.containsKey(p.title)) {
    //         return this.db.get(p.title).values().stream().map(v -> {
    //             return new BroadcastTransfer(p, v.getSchedule());
    //         }).collect(Collectors.toList());
    //     }
    //
    //     return l;
    // }

    // FIXME(borja): Return real data
    public List<BroadcastTransfer> loadForWeek(ProgramTransfer p, LocalDate firstOfWeek) {
        ArrayList<BroadcastTransfer> l = new ArrayList<>();
        LocalDateTime currDay = firstOfWeek.atTime(0, 0);
        for (int i = 0; i < 7; i++) {
            LocalDateTime start = currDay.withHour(ThreadLocalRandom.current().nextInt(9, 17 + 1));
            LocalDateTime end = start.plusHours(ThreadLocalRandom.current().nextInt(1,4+1));
            l.add(new BroadcastTransfer(p, new BroadcastTime(start, end)));
            currDay = currDay.plusDays(1);
        }

        return l;

        // if (!this.db.containsKey(p.title)) {
        //     return l;
        // }
        //
        // LocalTime start = LocalTime.now().withHour(0).withMinute(0);
        // LocalDateTime end = firstOfWeek.plusDays(7);
        // TreeMap<LocalDateTime, Broadcast> it = this.db.get(p.title);
        // XXX: See
        // https://stackoverflow.com/questions/40689813/how-to-iterate-over-a-hashmap-starting-from-a-particular-key-value-in-java
        // for (Broadcast br : it.tailMap(LocalDateTime.of(firstOfWeek, start)).values()) {
        //     if (br.getSchedule().compareTo(end))
        // }
        //
        // return l;
    }
}
