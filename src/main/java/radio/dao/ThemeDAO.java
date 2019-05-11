package radio.dao;

import radio.core.Database;
import radio.core.Theme;
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
}
