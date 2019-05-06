package radio.dao;

import radio.core.Program;
import radio.transfer.ProgramTransfer;

import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ProgramDAO implements AppDAO<ProgramTransfer> {
    private TreeMap<String, Program> db;

    public ProgramDAO() {
        this.db = new TreeMap<>();
    }

    @Override
    public boolean exists(ProgramTransfer el) {
        return this.db.containsKey(el.title);
    }

    @Override
    public void persist(ProgramTransfer el) {
        Program p = new Program(el.title, el.description, el.color);
        this.db.put(el.title, p);
    }

    @Override
    public void delete(ProgramTransfer el) {
        this.db.remove(el.title);
    }

    @Override
    public List<ProgramTransfer> loadAll() {
        return this.db.values().stream().map(ProgramTransfer::new).collect(Collectors.toList());
    }

    public Optional<ProgramTransfer> findProgram(String programName) {
        if (this.db.containsKey(programName)) {
            return Optional.of(new ProgramTransfer(db.get(programName)));
        } else {
            return Optional.empty();
        }
    }
}
