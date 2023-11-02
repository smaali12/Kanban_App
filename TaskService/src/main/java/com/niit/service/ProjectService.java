package com.niit.service;

import com.niit.model.Column;
import com.niit.model.Project;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProjectService {
    Project addProject(Project project);
    List<Project> getAllProjects();
    Project getProjectById(int projId);
    Project addColumnToProject(int projId, Column column);
    Project deleteColumnToProject(int projId, String columnName);
    Project updateProject( int projectId1,  String projectName,  String description, List<String> membersToAdd);
    boolean deleteProject(int projectId);
}
