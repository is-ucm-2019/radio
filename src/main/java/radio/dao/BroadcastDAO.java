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

    // FIXME(borja): Could build other structure to check overlaps faster
    public boolean overlaps(BroadcastTransfer el) {
        for (Map.Entry<String, TreeMap<LocalDateTime, Broadcast>> entry : db.entrySet()) {
            for(Broadcast b : entry.getValue().values()) {
                if (el.schedule.overlaps(b.getSchedule())) {
                    return true;
                }
            }
        }

        return false;
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

    public List<BroadcastTransfer> loadRandomForWeek(ProgramTransfer p, LocalDate firstOfWeek) {
        ArrayList<BroadcastTransfer> l = new ArrayList<>();
        LocalDateTime currDay = firstOfWeek.atTime(0, 0);
        for (int i = 0; i < 7; i++) {
            LocalDateTime start = currDay.withHour(ThreadLocalRandom.current().nextInt(9, 17 + 1));
            LocalDateTime end = start.plusHours(ThreadLocalRandom.current().nextInt(1,4+1));
            l.add(new BroadcastTransfer(p, new BroadcastTime(start, end)));
            currDay = currDay.plusDays(1);
        }

        return l;
    }

    public List<BroadcastTransfer> loadForWeek(ProgramTransfer p, LocalDate firstOfWeek) {
        ArrayList<BroadcastTransfer> l = new ArrayList<>();
        if (!this.db.containsKey(p.title)) {
            return l;
        }

        LocalDateTime startKey = LocalDateTime.of(firstOfWeek, LocalTime.of(0, 0));
        LocalDateTime endKey = LocalDateTime.of(firstOfWeek.plusDays(7), LocalTime.of(23,59));
        TreeMap<LocalDateTime, Broadcast> it = this.db.get(p.title);
        for (Broadcast br : it.tailMap(startKey).values()) {
            if (!br.getSchedule().getEnd().isAfter(endKey)) {
                l.add(new BroadcastTransfer(p, br));
            }
        }

        return l;
    }
}
