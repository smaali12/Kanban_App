package com.niit.service;

import com.niit.model.Column;
import com.niit.model.Project;

public interface ColumnService {
    Column addColumn(String columnName,int taskLimit);

    int findColumnIndex(String colName,int projId);
    Project updateColumn(int taskLimit,String columnName,int projectId);
}
