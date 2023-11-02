import { Injectable } from '@angular/core';
import { User } from './User';
import { HttpClient } from '@angular/common/http';
import { Project } from './Project';

@Injectable({
  providedIn: 'root'
})
export class ServiceKanbanServiceService {

  constructor(private httpclient:HttpClient) {
    this.user=new User("a@gmail.com","ayush","bansal");
    this.user.projectlist=[1,2,3]
    console.log(this.user)

    this.user.projectlist.forEach(element => {
      httpclient.get<Project>("http://localhost:8083/api/v3/getProject/"+element).subscribe(x=>this.userProject.push(x))
    });
   
  }

  isLoogedin:boolean=false;
  user!:User;
  userProject:Project[]=[];
  
  swap(taskName:string,sourceCol:string,destCol:string){
    let url1="http://localhost:8083/api/v3/swapTask/1/"+taskName+"/"+sourceCol+"/"+destCol;
    this.httpclient.post(url1,"").subscribe(x=>{});
  }

}


