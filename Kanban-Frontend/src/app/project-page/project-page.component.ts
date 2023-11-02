import { Component, OnInit } from '@angular/core';
import { ServiceKanbanServiceService } from '../service.kanban-service.service';
import { Project } from '../Project';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { Column } from '../Column';
import { Task } from '../Task';

@Component({
  selector: 'app-project-page',
  templateUrl: './project-page.component.html',
  styleUrls: ['./project-page.component.css']
})
export class ProjectPageComponent implements OnInit {

  constructor(private service:ServiceKanbanServiceService) {
    console.log(service.userProject)
     }

  ngOnInit(): void {
  }
  getCurrentProject(){
    return this.service.userProject[0];
  }

  drop(event: CdkDragDrop<Column>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data.taskList, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data.taskList,
        event.container.data.taskList,
        event.previousIndex,
        event.currentIndex,
      );

      console.log(event.previousContainer.data.columnName);
      console.log(event.container.data.columnName);
      // console.log(event.currentIndex);
      console.log(event.container.data.taskList[event.currentIndex].taskName);

      this.service.swap(event.container.data.taskList[event.currentIndex].taskName,event.previousContainer.data.columnName,event.container.data.columnName);
    }
  }

}
