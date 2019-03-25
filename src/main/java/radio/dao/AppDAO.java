package radio.dao;

import java.util.List;

public interface AppDAO<T extends Comparable<T> > {
    boolean exists(T el);
    void persist(T el);
    List<T> loadAll();
}
