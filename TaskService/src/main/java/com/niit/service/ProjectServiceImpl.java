package com.niit.service;

import com.niit.model.Column;
import com.niit.model.Project;
import com.niit.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Project addProject(Project project) {
        project.getColumnList().add(new Column("To do",1));
        project.getColumnList().add(new Column("In progress",1));
        project.getColumnList().add(new Column("Completed",10));
        return taskRepository.insert(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return taskRepository.findAll();
    }

    @Override
    public Project getProjectById(int projId) {
        return taskRepository.findById(projId).get();
    }

    @Override
    public Project addColumnToProject(int projId, Column column) {
        Project project=taskRepository.findById(projId).get();
        int index=project.getColumnList().size();
        project.getColumnList().add(index-1,column);

        return taskRepository.save(project);
    }

    @Override
    public Project deleteColumnToProject(int projId, String columnName) {
        Project project=taskRepository.findById(projId).get();
        List<Column>columnList=project.getColumnList();
        int index=0;
        for (Column col:columnList
             ) {
            if(col.getColumnName().equals(columnName)){
                break;
            }
            index++;
        }
        project.getColumnList().remove(index);
        return taskRepository.save(project);
    }

    @Override
    public Project updateProject(int projectId1, String projectName, String description, List<String> membersToAdd) {
        Project project=taskRepository.findById(projectId1).get();
        System.out.println(project);
        project.setProjectName(projectName);
        project.setProjectDesc(description);
        List<String> members=project.getMembers();
        for (String member:membersToAdd
             ) {
            members.add(member);
        }
        project.setMembers(members);
        System.out.println(project);
        return taskRepository.save(project);
    }

    @Override
    public boolean deleteProject(int projectId) {
        Project project=taskRepository.findById(projectId).get();
        taskRepository.delete(project);
        return true;
    }


}
