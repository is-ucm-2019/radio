package radio.dao;

import java.util.List;

interface IAppDAO<T> {
    boolean exists(T el);

    void persist(T el);

    void delete(T el);

    List<T> loadAll();
}
