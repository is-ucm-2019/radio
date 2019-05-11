package radio.dao;

import radio.core.Broadcast;
import radio.core.Database;
import radio.core.Program;
import radio.transfer.BroadcastTransfer;
import radio.transfer.ProgramTransfer;
import radio.transfer.ThemeTransfer;
import radio.util.BroadcastTime;
import radio.util.ThemeSchedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class BroadcastDAO implements IAppDAO<BroadcastTransfer> {
    private Database database;

    public BroadcastDAO(Database db) {
        this.database = db;
    }

    @Override
    public boolean exists(BroadcastTransfer el) {
        return database.programExists(el.parent.title);
    }

    public boolean overlaps(BroadcastTransfer el) {
        return database.overlapSchedule(el.schedule);
    }

    @Override
    public void persist(BroadcastTransfer el) {
        database.persistBroadcast(el.parent.title, new Broadcast(el));
    }

    @Override
    public void delete(BroadcastTransfer el) {
        database.removeBroacast(el.key);
    }

    @Override
    // Careful, very expensive
    public List<BroadcastTransfer> loadAll() {
        return database.getPrograms()
                .stream()
                .flatMap(p -> p.getBroadcasts()
                               .stream()
                               .map(b -> new BroadcastTransfer(new ProgramTransfer(p), b)))
                .collect(Collectors.toList());
    }

    public List<BroadcastTransfer> loadAll(String programName) {
        Optional<Program> pOpt = database.getProgram(programName);
        if (!pOpt.isPresent()) {
            return new ArrayList<>();
        }

        Program p = pOpt.get();
        return p.getBroadcasts()
                .stream()
                .map(b -> new BroadcastTransfer(new ProgramTransfer(p), b))
                .collect(Collectors.toList());
    }


    public List<BroadcastTransfer> loadRandomForWeek(ProgramTransfer p, LocalDate firstOfWeek) {
        ArrayList<BroadcastTransfer> l = new ArrayList<>();
        LocalDateTime currDay = firstOfWeek.atTime(0, 0);
        for (int i = 0; i < 7; i++) {
            LocalDateTime start = currDay.withHour(ThreadLocalRandom.current().nextInt(9, 17 + 1));
            LocalDateTime end = start.plusHours(ThreadLocalRandom.current().nextInt(1, 4 + 1));
            l.add(new BroadcastTransfer(p, new BroadcastTime(start, end)));
            currDay = currDay.plusDays(1);
        }

        return l;
    }

    public List<BroadcastTransfer> loadForWeek(LocalDate firstOfWeek) {
        return database.forWeek(firstOfWeek)
                       .stream()
                       .map(br ->
                               new BroadcastTransfer(new ProgramTransfer(database.getProgram(br)), br))
                       .collect(Collectors.toList());
    }

    public List<BroadcastTransfer> loadForRange(ThemeSchedule sched) {
        return database.forSchedule(sched)
                        .stream()
                        .map(br ->
                                new BroadcastTransfer(new ProgramTransfer(database.getProgram(br)), br))
                        .collect(Collectors.toList());
    }

    public void addTheme(BroadcastTransfer br, ThemeTransfer tr) {
        database.addTheme(br.key, tr.name);
    }
}
