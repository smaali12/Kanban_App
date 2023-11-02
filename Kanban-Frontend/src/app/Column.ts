import { Task } from "./Task";

export class Column{
    columnName!:string;
    taskList!:Task[];
    
    constructor(columnName:string,taskList:Task[]){
        this.columnName=columnName;
        this.taskList=taskList;
    }
}