package dao.impl;

import dao.entity.GenericDAO2;

import java.util.List;

/**
 * Created by alpo123 on 9/2/16.
 */
public class AbstractDAO<T> implements GenericDAO2<T> {
    @Override
    public void delete(int id) {

    }

    @Override
    public List<T> getAll() {
        return null;
    }

    @Override
    public T getById(int id) {
        return null;
    }

    @Override
    public int insert(T t) {
        return 0;
    }

    @Override
    public void update(T t) {

    }
}
