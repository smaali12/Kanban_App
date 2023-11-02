package com.niit.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

public class Task {

    private String taskName;
    private String description;
    private String deadLine;
    private String taskStatus;
    private String assignee;
    private int priority;

    public Task() {
    }

    public Task(String taskName, String description, String deadLine, String taskStatus, String assignee, int priority) {
        this.taskName = taskName;
        this.description = description;
        this.deadLine = deadLine;
        this.taskStatus = taskStatus;
        this.assignee = assignee;
        this.priority = priority;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                ", description='" + description + '\'' +
                ", deadLine='" + deadLine + '\'' +
                ", taskStatus=" + taskStatus +
                ", assignee='" + assignee + '\'' +
                ", priority=" + priority +
                '}';
    }
}