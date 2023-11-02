package com.niit.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Project {
    @Transient
    public static final String SEQUENCE_NAME="project_sequence";
    @Id
    private int projectId;
    private String projectName;
    private String projectDesc;
    private List<String> members;
    private List<Column> columnList;

    public Project() {
    }

    public Project(String projectName, String projectDesc, List<String> members) {
        this.projectName = projectName;
        this.projectDesc = projectDesc;
        this.members = members;
        this.columnList=new ArrayList<>();
//        this.columnList.add(new Column("To do"));
//        this.columnList.add(new Column("In progress"));
//        this.columnList.add(new Column("Completed"));
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectDesc='" + projectDesc + '\'' +
                ", members=" + members +
                ", columnList=" + columnList +
                '}';
    }
}
