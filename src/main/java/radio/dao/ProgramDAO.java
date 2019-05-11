package radio.dao;

import radio.core.Database;
import radio.core.Program;
import radio.transfer.ProgramTransfer;

import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ProgramDAO implements IAppDAO<ProgramTransfer> {
    private Database database;

    public ProgramDAO(Database db) {
        this.database = db;
    }

    @Override
    public boolean exists(ProgramTransfer el) {
        return database.programExists(el.title);
    }

    @Override
    public void persist(ProgramTransfer el) {
        Program p = new Program(el.title, el.description, el.color);
        database.persistProgram(p);
    }

    @Override
    public void delete(ProgramTransfer el) {
        database.removeProgram(el.title);
    }

    @Override
    public List<ProgramTransfer> loadAll() {
        return database.getPrograms().stream().map(ProgramTransfer::new).collect(Collectors.toList());
    }

    public Optional<ProgramTransfer> findProgram(String programName) {
        return database.getProgram(programName).map(ProgramTransfer::new);
    }
}
