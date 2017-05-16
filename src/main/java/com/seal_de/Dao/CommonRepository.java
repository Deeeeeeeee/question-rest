package com.seal_de.Dao;

/**
 * Created by sealde on 5/16/17.
 */
public interface CommonRepository<E> {
    void clear();
    void saveOrUpdate(E element);
}
