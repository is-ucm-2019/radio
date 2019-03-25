package radio.dao;

import radio.core.Program;
import radio.transfer.ProgramTransfer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ProgramDAO implements AppDAO<ProgramTransfer> {
    private TreeSet<String> ids;
    private TreeSet<Program> db;

    public ProgramDAO() {
        this.ids = new TreeSet<>();
        this.db = new TreeSet<>(Comparator.comparing(Program::getTitle));
    }

    @Override
    public boolean exists(ProgramTransfer el) {
        return this.ids.contains(el.title);
    }

    @Override
    public void persist(ProgramTransfer el) {
        Program p = new Program(el.title, el.description, el.color);
        this.ids.add(p.getTitle());
        this.db.add(p);
    }

    @Override
    public List<ProgramTransfer> loadAll() {
        return new ArrayList<>(this.db).stream().map(e -> {
            return new ProgramTransfer(e.getTitle(), e.getDescription(), e.getColor());
        }).collect(Collectors.toList());
    }
}
