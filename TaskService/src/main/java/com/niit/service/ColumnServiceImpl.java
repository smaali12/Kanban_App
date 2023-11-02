package com.niit.service;

import com.niit.model.Column;
import com.niit.model.Project;
import com.niit.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColumnServiceImpl implements ColumnService{
    @Autowired
    TaskRepository taskRepository;

    @Override
    public Column addColumn(String columnName,int taskLimit) {
        return new Column(columnName,taskLimit);
    }

    @Override
    public int findColumnIndex(String colName,int projId) {
        Project project=taskRepository.findById(projId).get();
        List<Column> columnList=project.getColumnList();
        int index=0;
        for (Column col:columnList
        ) {
            if(col.getColumnName().equals(colName)){
                break;
            }
            index++;
        }
        return index;
    }

    public Project updateColumn(int taskLimit,String columnName,int projectId){
        Project project=taskRepository.findById(projectId).get();
        int index = findColumnIndex(columnName, projectId);
        project.getColumnList().get(index).setTaskLimit(taskLimit);
        return taskRepository.save(project);
    }
}
