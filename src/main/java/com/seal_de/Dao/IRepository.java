package com.seal_de.Dao;

import java.io.Serializable;

/**
 * Created by sealde on 4/26/17.
 */
public interface IRepository<E> extends CommonRepository<E> {
    E getById(Serializable id);
}
