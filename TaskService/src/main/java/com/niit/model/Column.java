package com.niit.model;

import java.util.ArrayList;
import java.util.List;

public class Column {
    private String columnName;
    private int taskLimit;
    private List<Task> taskList;

    public Column() {
    }

    public Column(String columnName,int taskLimit) {
        this.columnName = columnName;
        this.taskLimit=taskLimit;
        this.taskList=new ArrayList<>();
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public int getTaskLimit() {
        return taskLimit;
    }

    public void setTaskLimit(int taskLimit) {
        this.taskLimit = taskLimit;
    }

    @Override
    public String toString() {
        return "Column{" +
                "columnName='" + columnName + '\'' +
                ", taskLimit=" + taskLimit +
                ", taskList=" + taskList +
                '}';
    }
}
