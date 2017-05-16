package com.seal_de.service;

import java.io.Serializable;

/**
 * Created by sealde on 4/26/17.
 */
public interface IService<E> {
    boolean save(E element);
    boolean saveAfterClear(E element);
    E getById(Serializable id);
    void delete(E element);
    void deleteAfterClear(E element);
}
