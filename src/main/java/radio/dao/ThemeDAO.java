package radio.dao;

import radio.core.Theme;
import radio.transfer.ThemeTransfer;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class ThemeDAO implements AppDAO<ThemeTransfer> {
    private HashSet<String> ids = new HashSet<>();
    private TreeSet<Theme> db = new TreeSet<>();

    @Override
    public boolean exists(ThemeTransfer el) {
        return this.ids.contains(el.name);
    }

    private Theme forTransfer(ThemeTransfer el) {
        return el.color.map(color -> new Theme(el.name, el.description, el.schedule, color))
                       .orElseGet(() -> new Theme(el.name, el.description, el.schedule));
    }

    @Override
    public void persist(ThemeTransfer el) {
        Theme t = forTransfer(el);
        ids.add(el.name);
        db.add(t);
    }

    @Override
    public void delete(ThemeTransfer el) {
        if(exists(el)) {
            db.remove(forTransfer(el));
        }
    }

    // TODO(borja): Implement
    @Override
    public List<ThemeTransfer> loadAll() {
        throw new UnsupportedOperationException();
    }

    // TODO(borja): Implement
    public List<ThemeTransfer> loadForWeek(LocalDate firstOfWeek) {
        throw new UnsupportedOperationException();
    }
}
