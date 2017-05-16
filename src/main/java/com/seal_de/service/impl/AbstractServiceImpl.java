package com.seal_de.service.impl;

import com.seal_de.Dao.IRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by sealde on 5/4/17.
 */
@Transactional
public abstract class AbstractServiceImpl<K extends IRepository & JpaRepository, E> {
    protected K repository;

    public boolean save(E element){
        repository.saveOrUpdate(element);
        return true;
    }

    public boolean save(List<E> list, int index) {
        ListIterator<E> it = list.listIterator();
        while(it.hasNext()) {
            repository.saveOrUpdate(it.next());
        }
        return true;
    }

    public boolean saveAfterClear(E element){
        repository.clear();
        repository.saveOrUpdate(element);
        return true;
    }

    public void delete(E element) {
        repository.delete(element);
    }

    public void deleteAfterClear(E element) {
        repository.clear();
        repository.delete(element);
    }

    public E getById(Serializable id) {
        return (E) repository.getById(id);
    }
}
