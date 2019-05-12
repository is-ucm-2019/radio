package radio.dao;

import radio.core.Broadcast;
import radio.core.Database;
import radio.core.Program;
import radio.core.Theme;
import radio.transfer.BroadcastTransfer;
import radio.transfer.ProgramTransfer;
import radio.transfer.ThemeTransfer;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ThemeDAO implements IAppDAO<ThemeTransfer> {
    private Database database;

    public ThemeDAO(Database db) {
        this.database = db;
    }

    @Override
    public boolean exists(ThemeTransfer el) {
        return database.themeExists(el.name);
    }

    @Override
    public void persist(ThemeTransfer el) {
        database.persistTheme(new Theme(el));
    }

    @Override
    public void delete(ThemeTransfer el) {
        database.removeTheme(el.name);
    }

    @Override
    public List<ThemeTransfer> loadAll() {
        return database.getThemes()
                       .stream()
                        .map(ThemeTransfer::new).collect(Collectors.toList());
    }

    // TODO(borja): Implement
    public List<ThemeTransfer> loadForWeek(LocalDate firstOfWeek) {
        throw new UnsupportedOperationException();
    }

    public List<BroadcastTransfer> loadForTheme(ThemeTransfer tr) {
        List<Broadcast> brs = database.forTheme(tr.name);
        return brs.stream().map(broadcast -> {
            Program p = database.getProgram(broadcast);
            return new BroadcastTransfer(new ProgramTransfer(p), broadcast);
        }).collect(Collectors.toList());
    }
}
