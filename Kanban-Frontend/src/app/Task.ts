export class Task{
    taskName!:string;
    description!:string;
    deadLine!:string;
    taskStatus!:string;
    assignee!:string;
    priority!:number;

    constructor(taskName:string,description:string,deadLine:string,taskStatus:string,assignee:string,priority:number){
        this.taskName=taskName;
        this.description=description;
        this.deadLine=deadLine;
        this.taskStatus=taskStatus;
        this.assignee=assignee;
        this.priority=priority;
    }
}
