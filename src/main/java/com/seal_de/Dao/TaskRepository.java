package com.seal_de.Dao;

import com.seal_de.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sealde on 4/24/17.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, String>, IRepository<Task>{
    List<Task> findByUserId(String userId);

    @Query("select t from Task t where t.status = ? order by t.createTime asc")
    List<Task> findByStatus(Integer status);

    Task getByStatus(Integer status);
    Task getByAuditorIdAndStatus(String auditorId, Integer status);
    List<Task> findByAuditorId(String auditorId);
}
