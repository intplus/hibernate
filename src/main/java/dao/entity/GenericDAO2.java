package dao.entity;

import java.util.List;

public interface GenericDAO2<T> {
    int insert(T t);
    void update(T t);
    List<T> getAll();
    T getById(int id);
    void delete(int id);
}
