import { Column } from "./Column";

export class Project{
    projectId!:number;
    projectName!:string;
    projectDesc!:string;
    members!:string[];
    columnList!:Column[];

    constructor(projectId:number,projectName:string,projectDesc:string,members:string[],columnList:Column[]){
        this.projectId=projectId;
        this.projectName=projectName;
        this.projectDesc=projectDesc;
        this.members=members;
        this.columnList=columnList;
    }
}
