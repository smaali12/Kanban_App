package com.niit.service;

import com.niit.exception.TaskAlreadyExistException;
import com.niit.exception.TaskNotFoundException;
import com.niit.model.Column;
import com.niit.model.Project;
import com.niit.model.Task;

import java.util.List;


public interface TaskService {


    Project newTask(Task task, String colName, int projId) ;
    Project deleteTask(String taskName,int projId);
    List<Task> getAllTask(int projId);
    List<Task> getAllUncompletedTask(int projId);
    List<Task> getAllUncompletedTaskInUserHand(Column column, String emailId);
    Project updateTask(int projId,String taskName,String sourceCol,String destCol);
}
