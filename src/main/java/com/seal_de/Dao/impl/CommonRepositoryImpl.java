package com.seal_de.Dao.impl;

import com.seal_de.Dao.CommonRepository;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by sealde on 5/16/17.
 */
public class CommonRepositoryImpl<E> implements CommonRepository<E> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void clear() {
        entityManager.clear();
    }

    @Override
    public void saveOrUpdate(E element) {
        if(isNew(element))
            entityManager.persist(element);
        else
            entityManager.merge(element);
    }

    private boolean isNew(E element) {
        boolean isNew = false;
        try {
            Object id = getId(element);
            isNew = id == null ? true : false;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return isNew;
    }

    private Object getId(E element) throws InvocationTargetException, IllegalAccessException {
        Object id = null;
        Class clazz = element.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if(method.isAnnotationPresent(Id.class)) {
                id = method.invoke(element);
            }
        }
        return id;
    }
}
