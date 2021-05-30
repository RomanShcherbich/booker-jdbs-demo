package booker.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {

    public T create(T t) throws SQLException;
    public List<T> read();
    public T update(T t);
    public boolean delete(Object id) throws SQLException;
    public T get(Object id) throws SQLException;

}
