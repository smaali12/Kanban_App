package com.niit.controller;

import com.niit.exception.TaskAlreadyExistException;
import com.niit.exception.TaskNotFoundException;
import com.niit.model.Column;
import com.niit.model.Project;
import com.niit.model.Task;
import com.niit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("api/v3/")
public class TaskController {
    @Autowired
    private TaskService taskService;
    private ResponseEntity responseEntity;

    @Autowired
    private SequenceGeneratorService service;
    @Autowired
    private ProjectServiceImpl projectService;
    @Autowired
    private ColumnServiceImpl columnService;
    @Autowired
    private TaskServiceImpl taskServiceImpl;

    @PostMapping("/newProject")
    public ResponseEntity<?> createProject(@RequestBody Project project){
            project.setProjectId(service.getSequenceNumber(project.SEQUENCE_NAME));
            projectService.addProject(project);
            return new ResponseEntity(project, HttpStatus.CREATED);
    }

    @GetMapping("/getAllProjects")
    public ResponseEntity<?> getAllProject() {
        return new ResponseEntity( projectService.getAllProjects(),HttpStatus.OK);
    }

    @GetMapping("/getProject/{projectId}")
    public ResponseEntity<?> getProject(@PathVariable int projectId) {
        return new ResponseEntity( projectService.getProjectById(projectId),HttpStatus.OK);
    }

    @PostMapping("/addColumn/{projectId}/{taskLimit}")
    public ResponseEntity<?>  addColumn(@RequestBody String columnName,@PathVariable int projectId,@PathVariable int taskLimit){
        Column column=columnService.addColumn(columnName,taskLimit);
        return new ResponseEntity( projectService.addColumnToProject(projectId,column),HttpStatus.OK);
    }

    @DeleteMapping("/deleteColumn/{projectId}/{columnName}")
    public ResponseEntity<?>  deleteColumn(@PathVariable int projectId,@PathVariable String columnName){
        return new ResponseEntity( projectService.deleteColumnToProject(projectId,columnName),HttpStatus.OK);
    }

    @PostMapping("/addTask/{projectId}/{colName}")
    public ResponseEntity<?>  addTask(@RequestBody Task task,@PathVariable int projectId,@PathVariable String colName){
        return new ResponseEntity( taskService.newTask(task,colName,projectId),HttpStatus.OK);
    }

    @DeleteMapping("/deleteTask/{projectId}/{taskName}")
    public ResponseEntity<?>  deleteTask(@PathVariable int projectId,@PathVariable String taskName){
        return new ResponseEntity(taskService.deleteTask(taskName,projectId),HttpStatus.OK);
    }

    @GetMapping("/getAllTask/{projectId}")
    public ResponseEntity<?> getAllTask(@PathVariable int projectId) {
        return new ResponseEntity(taskService.getAllTask(projectId).size(),HttpStatus.OK);
    }

    @PostMapping("/swapTask/{projectId}/{taskName}/{sourceCol}/{destCol}")
    public ResponseEntity<?> updateTask(@PathVariable String taskName,@PathVariable int projectId,@PathVariable String sourceCol,@PathVariable String destCol){
        return new ResponseEntity( taskService.updateTask(projectId,taskName,sourceCol,destCol),HttpStatus.OK);
    }

    @PostMapping("updateProject/{projectId1}/{projectName}/{description}")
    public ResponseEntity<?> updateProject(@PathVariable int projectId1, @PathVariable String projectName, @PathVariable String description, @RequestBody List<String> membersToAdd){
        System.out.println("-----------------------------------------------------------------------------");
        return new ResponseEntity( projectService.updateProject(projectId1,projectName,description,membersToAdd),HttpStatus.OK);
    }

    @DeleteMapping("deleteProject/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable int projectId){
        return new ResponseEntity( projectService.deleteProject(projectId),HttpStatus.OK);
    }

    @PutMapping("updateTaskLimit/{taskLimit}/{columnName}/{projectId}")
    public ResponseEntity updateTaskLimit(@PathVariable int taskLimit,@PathVariable String columnName,@PathVariable int projectId){
        return new ResponseEntity<>(columnService.updateColumn(taskLimit,columnName,projectId),HttpStatus.OK);
    }

}
