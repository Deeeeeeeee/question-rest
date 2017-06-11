package com.seal_de.Dao.impl;

import com.seal_de.domain.Task;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by sealde on 5/16/17.
 */
public class TaskRepositoryImpl extends CommonRepositoryImpl<Task> {
    @PersistenceContext
    private EntityManager entityManager;

    Task getByStatus(Integer status){
        Query query = entityManager.createQuery("select t from Task t where t.status = ? " +
                "order by t.createTime asc")
                .setParameter(1, status)
                .setMaxResults(1);
        if(query.getResultList().size() == 0)
            return null;
        else
            return (Task) query.getSingleResult();
    }
}
