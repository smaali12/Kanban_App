import { Component, OnInit } from '@angular/core';
import { ServiceKanbanServiceService } from '../service.kanban-service.service';
import { Project } from '../Project';

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})
export class BodyComponent implements OnInit {

  constructor(private service:ServiceKanbanServiceService) {let date:Date=new Date();
    this.pList=service.userProject;
    console.log(this.pList)
    console.log(date)  
  }

  ngOnInit(): void {
  }
theme:string="pink"
panelOpenState = false;
pList!:Project[];
}
