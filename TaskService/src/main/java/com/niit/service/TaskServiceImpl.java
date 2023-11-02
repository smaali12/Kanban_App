package com.niit.service;

import com.niit.exception.TaskAlreadyExistException;
import com.niit.exception.TaskNotFoundException;
import com.niit.model.Column;
import com.niit.model.Project;
import com.niit.model.Task;
import com.niit.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    ColumnServiceImpl columnService;


    @Override
    public Project newTask(Task task, String colName, int projId) {
//        System.out.println(task.getAssignee());
        Project project=taskRepository.findById(projId).get();
        Column column=new Column();
        for (Column column1:project.getColumnList()
             ) {
            if(column1.getColumnName().equals(colName))
            column=column1;
        }
//        System.out.println(column);
//        System.out.println(getAllUncompletedTaskInUserHand(column,task.getAssignee()));
        if(getAllUncompletedTaskInUserHand(column,task.getAssignee()).size()<column.getTaskLimit()) {
            int index = columnService.findColumnIndex(colName, projId);
            project.getColumnList().get(index).getTaskList().add(task);
            return taskRepository.save(project);
        }
        else {
//            System.out.println(getAllUncompletedTask(projId));
            return null;
        }
    }

    @Override
    public Project deleteTask(String taskName, int projId) {
        Project project=taskRepository.findById(projId).get();
        int colIndex=0;
        int taskIndex=0;
        for (Column col:project.getColumnList()
             ) {
            taskIndex=0;
            boolean flag=false;
            List<Task> taskList=col.getTaskList();
            for (Task t:taskList
                 ) {
                if(t.getTaskName().equals(taskName)){
                    flag=true;
                    break;
                }
                taskIndex++;
            }
            if(flag){
                break;
            }
            colIndex++;
        }
        project.getColumnList().get(colIndex).getTaskList().remove(taskIndex);
        return taskRepository.save(project);
    }

    @Override
    public List<Task> getAllTask(int projId) {
        List<Task> tasksList=new ArrayList<>();
        Project project=taskRepository.findById(projId).get();
        for (Column col:project.getColumnList()) {
            List<Task> taskList=col.getTaskList();
            for (Task t:taskList) {
                tasksList.add(t);
                }
            }
        return tasksList;
    }

    @Override
    public List<Task> getAllUncompletedTask(int projId) {
        List<Task> tasksList=new ArrayList<>();
        Project project=taskRepository.findById(projId).get();
        for (Column col:project.getColumnList()) {
            List<Task> taskList=col.getTaskList();
            for (Task t:taskList) {
                if(!t.getTaskStatus().equals("Completed")) {
                    tasksList.add(t);
                }
            }
        }
        return tasksList;
    }

    @Override
    public List<Task> getAllUncompletedTaskInUserHand(Column column,String emailId) {
        List<Task> tasksList=new ArrayList<>();
        List<Task> taskListInColumn=column.getTaskList();
        for (Task t:taskListInColumn) {
            if(t.getAssignee().equals(emailId)){
                tasksList.add(t);
            }
        }
//        System.out.println(tasksList.size());
        return tasksList;
    }

//    public boolean isLimitFull(String email,String columnName,int projectId,String taskName){
//        Project project=taskRepository.findById(projectId).get();
//        int index1=columnService.findColumnIndex(sourceCol,projId);
//    }

    @Override
    public Project updateTask(int projId, String taskName,String sourceCol, String destCol) {
        Project project=taskRepository.findById(projId).get();
        int index1=columnService.findColumnIndex(sourceCol,projId);
        int index2=columnService.findColumnIndex(destCol,projId);
        Column column =project.getColumnList().get(index2);
//        System.out.println(column);

        List<Task> taskInCol=project.getColumnList().get(index1).getTaskList();
//        System.out.println(taskInCol);
        Iterator<Task> iterator=taskInCol.iterator();
        Task currentTask=new Task();
        while (iterator.hasNext()){
            Task t=iterator.next();
//            System.out.println(t.getTaskName());
//            System.out.println(taskName);
            if(t.getTaskName().equals(taskName)){
                currentTask=t;
                break;
            }
        }
//        System.out.println(currentTask);
//        System.out.println(getAllUncompletedTaskInUserHand(column,currentTask.getAssignee()).size());
//        System.out.println(column.getTaskLimit());

        if(getAllUncompletedTaskInUserHand(column,currentTask.getAssignee()).size()<column.getTaskLimit()){
            deleteTask(currentTask.getTaskName(),projId);
            currentTask.setTaskStatus(destCol);
            return newTask(currentTask,destCol,projId);
        }
        else {
            return null;
        }
    }
}
