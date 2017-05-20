package com.seal_de.service;

import com.seal_de.domain.Task;
import com.seal_de.model.TaskInfoModel;

import java.util.List;

/**
 * Created by sealde on 4/24/17.
 */
public interface TaskService extends IService<Task> {
    List<Task> findByUserId(String userId);
    Task getByStatus(Integer status);
    List<Task> findByStatus(Integer status);
    List<TaskInfoModel> taskToTaskInfoModel(List<Task> tasks);
    Task getByAuditorId(String auditorId);
    List<Task> findByAuditorId(String auditorId);
    Task getNotMakingTask(String userId);
}
